package pages;

import io.Movie;

import java.util.ArrayList;

public class Ranking {
    private String genre;
    private int numberoflikes;
    private ArrayList<Movie> movieswithgenre = new ArrayList<>();

    /**
     *
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     */
    public void setGenre(final String genre) {
        this.genre = genre;
    }

    /**
     *
     * @return
     */
    public int getNumberoflikes() {
        return numberoflikes;
    }

    /**
     *
     * @param numberoflikes
     */
    public void setNumberoflikes(final int numberoflikes) {
        this.numberoflikes = numberoflikes;
    }

    /**
     *
     * @return
     */
    public ArrayList<Movie> getMovieswithgenre() {
        return movieswithgenre;
    }
}
