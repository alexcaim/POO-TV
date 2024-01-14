package io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
//vom folosi o instanță a acestei clase care ne va afișa
//toate tipurile de output

public final class AllTypesOfPrint {
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     *
     * @param output
     * @param user
     * @param error
     * @return
     */
    //această metodă este folosită mai ales în cazul existenței
    //unei erori
    public ArrayNode printUser(final ArrayNode output, final Users user, final int error) {
        ObjectNode nod = mapper.createObjectNode();
        ArrayNode currentMoviesList = mapper.createArrayNode();
        ObjectNode nodnul = null;
        if (error == 0) {
            nod.put("error", "Error");
            nod.put("currentMoviesList", currentMoviesList);
            nod.put("currentUser", nodnul);
            output.add(nod);
        } else {
            nod.put("error", nodnul);
            nod.put("currentMoviesList", currentMoviesList);
            ObjectNode credentials = mapper.createObjectNode();
            ObjectNode nodcredentials = mapper.createObjectNode();
            credentials.put("name", user.getCredentials().getName());
            credentials.put("password", user.getCredentials().getPassword());
            credentials.put("accountType", user.getCredentials().getAccountType());
            credentials.put("country", user.getCredentials().getCountry());
            credentials.put("balance", String.valueOf(user.getCredentials().getBalance()));
            nodcredentials.put("credentials", credentials);
            nodcredentials.put("tokensCount", user.getTokensCount());
            nodcredentials.put("numFreePremiumMovies", user.getNumFreePremiumMovies());
            ArrayNode movieswithinteraction = mapper.createArrayNode();
            for (int i = 0; i < user.getPurchasedMovies().size(); i++) {
                movieswithinteraction.add(printJustAMovie(user.getPurchasedMovies().get(i)));
            }
            nodcredentials.put("purchasedMovies", movieswithinteraction);
            movieswithinteraction = mapper.createArrayNode();
            for (int i = 0; i < user.getWatchedMovies().size(); i++) {
                movieswithinteraction.add(printJustAMovie(user.getWatchedMovies().get(i)));
            }
            nodcredentials.put("watchedMovies", movieswithinteraction);
            movieswithinteraction = mapper.createArrayNode();
            for (int i = 0; i < user.getLikedMovies().size(); i++) {
                movieswithinteraction.add(printJustAMovie(user.getLikedMovies().get(i)));
            }
            nodcredentials.put("likedMovies", movieswithinteraction);
            movieswithinteraction = mapper.createArrayNode();
            for (int i = 0; i < user.getRatedMovies().size(); i++) {
                movieswithinteraction.add(printJustAMovie(user.getRatedMovies().get(i)));
            }
            nodcredentials.put("ratedMovies", movieswithinteraction);
            ArrayNode notifications = mapper.createArrayNode();
            for (int i = 0; i < user.getNotifications().size(); i++) {
                ObjectNode nodfornotify = mapper.createObjectNode();
                nodfornotify.put("movieName", user.getNotifications().get(i).getMovieName());
                nodfornotify.put("message", user.getNotifications().get(i).getMessage());
                notifications.add(nodfornotify);
            }
            nodcredentials.put("notifications", notifications);
            nod.put("currentUser", nodcredentials);
            output.add(nod);
        }
        return output;
    }

    /**
     *
     * @param output
     * @param user
     * @param movies
     * @param error
     * @return
     */
    public ArrayNode printMovies(final ArrayNode output, final Users user,
                                 final ArrayList<Movie> movies, final int error) {
        ObjectNode nod = mapper.createObjectNode();
        ArrayNode currentMoviesList = mapper.createArrayNode();
        ArrayNode notifications;
        ObjectNode nodnul = null;
        if (error == 0) {
            nod.put("error", "Error");
        } else {
            nod.put("error", nodnul);
            for (Movie movie : movies) {
                ObjectNode nodmovie = mapper.createObjectNode();
                ArrayNode arraygenres = mapper.createArrayNode();
                ArrayNode arrayactors = mapper.createArrayNode();
                ArrayNode arraycountries = mapper.createArrayNode();
                nodmovie.put("name", movie.getName());
                nodmovie.put("year", Integer.toString(movie.getYear()));
                nodmovie.put("duration", movie.getDuration());
                for (int j = 0; j < movie.getGenres().size(); j++) {
                    arraygenres.add(movie.getGenres().get(j));
                }
                for (int j = 0; j < movie.getActors().size(); j++) {
                    arrayactors.add(movie.getActors().get(j));
                }
                for (int j = 0; j < movie.getCountriesBanned().size(); j++) {
                    arraycountries.add(movie.getCountriesBanned().get(j));
                }
                nodmovie.put("genres", arraygenres);
                nodmovie.put("actors", arrayactors);
                nodmovie.put("countriesBanned", arraycountries);
                nodmovie.put("numLikes", movie.getNumLikes());
                nodmovie.put("rating", movie.getRating());
                nodmovie.put("numRatings", movie.getNumRatings());
                currentMoviesList.add(nodmovie);
            }
        }
        nod.put("currentMoviesList", currentMoviesList);
        ObjectNode credentials = mapper.createObjectNode();
        ObjectNode nodcredentials = mapper.createObjectNode();
        credentials.put("name", user.getCredentials().getName());
        credentials.put("password", user.getCredentials().getPassword());
        credentials.put("accountType", user.getCredentials().getAccountType());
        credentials.put("country", user.getCredentials().getCountry());
        credentials.put("balance", String.valueOf(user.getCredentials().getBalance()));
        nodcredentials.put("credentials", credentials);
        nodcredentials.put("tokensCount", user.getTokensCount());
        nodcredentials.put("numFreePremiumMovies", user.getNumFreePremiumMovies());
        ArrayNode movieswithinteraction = mapper.createArrayNode();
        for (int i = 0; i < user.getPurchasedMovies().size(); i++) {
            movieswithinteraction.add(printJustAMovie(user.getPurchasedMovies().get(i)));
        }
        nodcredentials.put("purchasedMovies", movieswithinteraction);
        movieswithinteraction = mapper.createArrayNode();
        for (int i = 0; i < user.getWatchedMovies().size(); i++) {
            movieswithinteraction.add(printJustAMovie(user.getWatchedMovies().get(i)));
        }
        nodcredentials.put("watchedMovies", movieswithinteraction);
        movieswithinteraction = mapper.createArrayNode();
        for (int i = 0; i < user.getLikedMovies().size(); i++) {
            movieswithinteraction.add(printJustAMovie(user.getLikedMovies().get(i)));
        }
        nodcredentials.put("likedMovies", movieswithinteraction);
        movieswithinteraction = mapper.createArrayNode();
        for (int i = 0; i < user.getRatedMovies().size(); i++) {
            movieswithinteraction.add(printJustAMovie(user.getRatedMovies().get(i)));
        }
        nodcredentials.put("ratedMovies", movieswithinteraction);
        notifications = mapper.createArrayNode();
        for (int i = 0; i < user.getNotifications().size(); i++) {
            ObjectNode nodfornotify = mapper.createObjectNode();
            nodfornotify.put("movieName", user.getNotifications().get(i).getMovieName());
            nodfornotify.put("message", user.getNotifications().get(i).getMessage());
            notifications.add(nodfornotify);
        }
        nodcredentials.put("notifications", notifications);
        nod.put("currentUser", nodcredentials);
        output.add(nod);
        return output;
    }

    /**
     *
     * @param output
     * @param user
     * @param movie
     * @param error
     * @return
     */
    //metodă pentru output urile corespunzătoare paginilor
    //Seedetails, unde ne dorim prezența unui singur film
    //în array-ul de filme
    public ArrayNode printMovie(final ArrayNode output, final Users user,
                                final Movie movie, final int error) {
        ArrayList<Movie> array = new ArrayList<>();
        array.add(movie);
        return printMovies(output, user, array, error);
    }

    /**
     *
     * @param movie
     * @return
     */
    public ObjectNode printJustAMovie(final Movie movie) {
        ObjectNode nodmovie = mapper.createObjectNode();
        ArrayNode arraygenres = mapper.createArrayNode();
        ArrayNode arrayactors = mapper.createArrayNode();
        ArrayNode arraycountries = mapper.createArrayNode();
        nodmovie.put("name", movie.getName());
        nodmovie.put("year", Integer.toString(movie.getYear()));
        nodmovie.put("duration", movie.getDuration());
        for (int j = 0; j < movie.getGenres().size(); j++) {
            arraygenres.add(movie.getGenres().get(j));
        }
        for (int j = 0; j < movie.getActors().size(); j++) {
            arrayactors.add(movie.getActors().get(j));
        }
        for (int j = 0; j < movie.getCountriesBanned().size(); j++) {
            arraycountries.add(movie.getCountriesBanned().get(j));
        }
        nodmovie.put("genres", arraygenres);
        nodmovie.put("actors", arrayactors);
        nodmovie.put("countriesBanned", arraycountries);
        nodmovie.put("numLikes", movie.getNumLikes());
        nodmovie.put("rating", movie.getRating());
        nodmovie.put("numRatings", movie.getNumRatings());
        return nodmovie;
    }
}
