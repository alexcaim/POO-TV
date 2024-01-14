package pages;

import io.Movie;

import java.util.ArrayList;

public class Pages {
    private String name;
    private ArrayList<Movie> moviesavaibleonpage;

    /**
     *
     * @param v
     */
    public void accept(final visitor v) {
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public ArrayList<Movie> getMoviesavaibleonpage() {
        return moviesavaibleonpage;
    }

    /**
     *
     * @param moviesavaibleonpage
     */
    public void setMoviesavaibleonpage(final ArrayList<Movie> moviesavaibleonpage) {
        this.moviesavaibleonpage = moviesavaibleonpage;
    }

}
