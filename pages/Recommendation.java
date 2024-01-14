package pages;

import io.Input;
import io.Movie;
import io.Users;

import java.util.ArrayList;

public class Recommendation {
    private ArrayList<Ranking> rankedgenres = new ArrayList<>();
    private Ranking newrg = new Ranking();
    private Input input;
    private Users currentuser;
    private int sw = 0;
    private String name;

    public Recommendation(final Input input, final Users currentuser) {
        this.input = input;
        this.currentuser = currentuser;
    }

    /**
     *
     */
    // selectează genurile eligibile
    public void addgenres() {
        for (Movie movie: currentuser.getLikedMovies()) {
            for (String genre : movie.getGenres()) {
                sw = 0;
                for (Ranking rankedg : rankedgenres) {
                    if (rankedg.getGenre().equals(genre)) {
                        sw = 1;
                        rankedg.setNumberoflikes(rankedg.getNumberoflikes() + 1);
                    }
                }
                if (sw == 0) {
                    newrg = new Ranking();
                    newrg.setNumberoflikes(1);
                    newrg.setGenre(genre);
                    rankedgenres.add(newrg);
                }
            }
        }
    }

    /**
     *
     * @return
     */
    //le sortează apoi adaugă și sortează filmele ce aparțin acestor genuri
    public String sortandputmovies() {
        sw = 0;
        addgenres();
        rankedgenres.sort(new Sortbylikes());
        for (Ranking rankedg : rankedgenres) {
            for (Movie movie : input.getMovies()) {
                if (movie.getGenres().contains(rankedg.getGenre())) {
                    rankedg.getMovieswithgenre().add(movie);
                }
            }
        }
        for (Ranking rankedg : rankedgenres) {
            rankedg.getMovieswithgenre().sort(new Sortmoviesbylikes());
        }
        for (Ranking rankedg : rankedgenres) {

            for (Movie movie : rankedg.getMovieswithgenre()) {

                for (Movie watchedmovie : currentuser.getWatchedMovies()) {
                    if (!watchedmovie.getName().equals(movie.getName())
                           && !movie.getCountriesBanned().
                            contains(currentuser.getCredentials().getCountry())
                            && sw == 0) {
                        name = movie.getName();
                        sw = 1;
                    }
                }
            }
        }
        return name;
    }

}
