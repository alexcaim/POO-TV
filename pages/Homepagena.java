package pages;

import io.Movie;

import java.util.ArrayList;

public final class Homepagena extends Pages {
    private int numberofvisits = 0;
    private final String name = "Homepagena";
    private ArrayList<Movie> moviesavaibleonpage;

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
