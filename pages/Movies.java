package pages;

import io.Movie;

import java.util.ArrayList;

public final class Movies extends Pages {
    private int numberofvisits = 0;
    private ArrayList<Movie> moviesavaibleonpage;


    private final String name = "Movies";

    /**
     *
     * @param v
     */
    public void accept(final visitor v) {
        v.visit(this);
        numberofvisits++;
    }

    public int getNumberofvisits() {
        return numberofvisits;
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
