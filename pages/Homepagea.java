package pages;

import io.Movie;

import java.util.ArrayList;

public final class Homepagea extends Pages {

    private int numberofvisits = 0;

    private ArrayList<Movie> moviesavaibleonpage;

    private final String name = "Homepagea";

    /**
     *
     * @param v
     */
    public void accept(final visitor v) {
        v.visit(this);
        numberofvisits++;
    }

    public String getName() {
        return name;
    }
    public ArrayList<Movie> getMoviesavaibleonpage() {
        return moviesavaibleonpage;
    }

    public void setMoviesavaibleonpage(final ArrayList<Movie> moviesavaibleonpage) {
        this.moviesavaibleonpage = moviesavaibleonpage;
    }
}
