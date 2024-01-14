package pages;

import io.Movie;

import java.util.ArrayList;

public final class Upgrades extends Pages {
    private int numberofvisits = 0;
    private final String name = "Upgrades";
    private ArrayList<Movie> moviesavaibleonpage;

    /**
     *
     * @param v
     */

    public void accept(final visitor v) {
        v.visit(this);
        numberofvisits++;
    }
    public ArrayList<Movie> getMoviesavaibleonpage() {
        return moviesavaibleonpage;
    }

    public void setMoviesavaibleonpage(final ArrayList<Movie> moviesavaibleonpage) {
        this.moviesavaibleonpage = moviesavaibleonpage;
    }
    public String getName() {
        return name;
    }

}
