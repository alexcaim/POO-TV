package pages;
import io.Movie;

import java.util.ArrayList;

public final class Seedetails extends Pages {
    private Movie movie;
    private int numberofvisits = 0;
    private int canwatch;
    private int canrate;

    private int liked;

    private int rated;
    private final String name = "Seedetails";
    private ArrayList<Movie> moviesavaibleonpage;


    /**
     *
     * @param movie
     * @param canwatch
     * @param canrate
     */
    public Seedetails(final Movie movie, final int canwatch, final int canrate) {
        this.movie = movie;
        this.canwatch = canwatch;
        this.canrate = canrate;
    }

    /**
     *
     * @param v
     */
    public void accept(final visitor v) {
        v.visit(this);
        numberofvisits++;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }
    public ArrayList<Movie> getMoviesavaibleonpage() {
        return moviesavaibleonpage;
    }

    public void setMoviesavaibleonpage(final ArrayList<Movie> moviesavaibleonpage) {
        this.moviesavaibleonpage = moviesavaibleonpage;
    }
    public int getCanwatch() {
        return canwatch;
    }

    public void setCanwatch(final int canwatch) {
        this.canwatch = canwatch;
    }

    public int getCanrate() {
        return canrate;
    }

    public void setCanrate(final int canrate) {
        this.canrate = canrate;
    }

    public String getName() {
        return name;
    }
    public int getRated() {
        return rated;
    }

    public void setRated(final int rated) {
        this.rated = rated;
    }
    public int getLiked() {
        return liked;
    }

    public void setLiked(final int liked) {
        this.liked = liked;
    }

}
