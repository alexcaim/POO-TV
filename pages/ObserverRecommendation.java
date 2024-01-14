package pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.AllTypesOfPrint;
import io.Input;
import io.Notification;
import io.Users;

public class ObserverRecommendation extends Observer {
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayNode output;
    private Input input;
    private Users currentuser;
    private AllTypesOfPrint all = new AllTypesOfPrint();

    public ObserverRecommendation(final Input input,
                                  final ArrayNode output, final Users currentuser) {
        this.input = input;
        this.output = output;
        this.currentuser = currentuser;
    }

    /**
     *
     */
    public void update() {
        Recommendation recommendation = new Recommendation(input, currentuser);
        String name;
        name = recommendation.sortandputmovies();
        Notification notification = new Notification();
        if (name != null) {
            notification.setMovieName(name);
        } else {
            notification.setMovieName("No recommendation");
        }
        notification.setMessage("Recommendation");
        currentuser.getNotifications().add(notification);
        ObjectNode nod = mapper.createObjectNode();
        ObjectNode nodnul = null;
        nod.put("error", nodnul);
        nod.put("currentMoviesList", nodnul);
        ObjectNode credentials = mapper.createObjectNode();
        ObjectNode nodcredentials = mapper.createObjectNode();
        credentials.put("name", currentuser.getCredentials().getName());
        credentials.put("password", currentuser.getCredentials().getPassword());
        credentials.put("accountType", currentuser.getCredentials().getAccountType());
        credentials.put("country", currentuser.getCredentials().getCountry());
        credentials.put("balance", String.valueOf(currentuser.getCredentials().getBalance()));
        nodcredentials.put("credentials", credentials);
        nodcredentials.put("tokensCount", currentuser.getTokensCount());
        nodcredentials.put("numFreePremiumMovies", currentuser.getNumFreePremiumMovies());
        ArrayNode movieswithinteraction = mapper.createArrayNode();
        for (int i = 0; i < currentuser.getPurchasedMovies().size(); i++) {
            movieswithinteraction.add(all.printJustAMovie(currentuser.getPurchasedMovies().get(i)));
        }
        nodcredentials.put("purchasedMovies", movieswithinteraction);
        movieswithinteraction = mapper.createArrayNode();
        for (int i = 0; i < currentuser.getWatchedMovies().size(); i++) {
            movieswithinteraction.add(all.printJustAMovie(currentuser.getWatchedMovies().get(i)));
        }
        nodcredentials.put("watchedMovies", movieswithinteraction);
        movieswithinteraction = mapper.createArrayNode();
        for (int i = 0; i < currentuser.getLikedMovies().size(); i++) {
            movieswithinteraction.add(all.printJustAMovie(currentuser.getLikedMovies().get(i)));
        }
        nodcredentials.put("likedMovies", movieswithinteraction);
        movieswithinteraction = mapper.createArrayNode();
        for (int i = 0; i < currentuser.getRatedMovies().size(); i++) {
            movieswithinteraction.add(all.printJustAMovie(currentuser.getRatedMovies().get(i)));
        }
        nodcredentials.put("ratedMovies", movieswithinteraction);
        ArrayNode notifications = mapper.createArrayNode();
        for (int i = 0; i < currentuser.getNotifications().size(); i++) {
            ObjectNode nodfornotify = mapper.createObjectNode();
            nodfornotify.put("movieName", currentuser.getNotifications().get(i).getMovieName());
            nodfornotify.put("message", currentuser.getNotifications().get(i).getMessage());
            notifications.add(nodfornotify);
        }
        nodcredentials.put("notifications", notifications);
        nod.put("currentUser", nodcredentials);
        output.add(nod);
    }

    /**
     *
     * @return
     */
    public boolean geterror() {
        return true;
    }

    /**
     *
     * @return
     */
    public ArrayNode getOutput() {
        return output;
    }
}
