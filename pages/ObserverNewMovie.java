package pages;

import io.*;

public class ObserverNewMovie extends Observer {
    private Input input;
    private Movie movie;
    private int find = 0;

    private Notification notification = new Notification();

    public ObserverNewMovie(final MovieSubject moviesubject) {
        input = moviesubject.getInput();
        movie = moviesubject.getMovie();
    }
    //adaugă direct în input filmul
    /**
     *
     */
    public void update() {
        int sw;
        for (Movie movies : input.getMovies()) {
            if (movies.getName().equals(movie.getName())) {
                find = 1;
                break;
            }
        }
        if (find == 0) {
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
                    notification.setMessage("ADD");
                    notification.setMovieName(movie.getName());
                    user.getNotifications().add(notification);
                }
            }
            input.getMovies().add(movie);
        }
    }

    /**
     *
     * @return
     */
    public boolean geterror() {
        if (find == 0) {
            return true;
        } else {
            return false;
        }
    }

}
