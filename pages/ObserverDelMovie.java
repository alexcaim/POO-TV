package pages;

import io.Input;
import io.Movie;
import io.Notification;
import io.Users;

public class ObserverDelMovie extends Observer {
    private Input input;
    private Movie movie;
    private int find = 0;

    private Notification notification = new Notification();

    public ObserverDelMovie(final MovieSubject moviesubject) {
        input = moviesubject.getInput();
        movie = moviesubject.getMovie();
    }
    //stergem filmul dacă există
    /**
     *
     */
    public void update() {
        int sw;
        if (find == 1) {
            for (Users user : input.getUsers()) {
                sw = 0;
                for (String gender : user.getGenreSubscribe()) {
                    if (movie.getGenres().contains(gender)) {
                        sw++;
                        break;
                    }
                }
                if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                    sw++;
                }
                if (sw == 2) {
                    notification.setMessage("DEL");
                    notification.setMovieName(movie.getName());
                    user.getNotifications().add(notification);
                }
            }
            for (sw = 0; sw < input.getMovies().size(); sw++) {
                if (input.getMovies().get(sw).getName().equals(movie.getName())) {
                    input.getMovies().remove(sw);
                    break;
                }
            }
        }
    }

    /**
     *
     * @return
     */
    public boolean geterror() {
        return true;
    }
}
