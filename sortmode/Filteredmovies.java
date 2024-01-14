package sortmode;
import io.Movie;
import io.Filters;
import java.util.ArrayList;

public final class Filteredmovies {
    private Filters filters;
    private ArrayList<Movie> movies;

    private ArrayList<Movie> filteredmovies = new ArrayList<>();

    public Filteredmovies(final Filters filters, final ArrayList<Movie> movies) {
        this.filters = filters;
        this.movies = movies;
    }

    /**
     *
     */
    //metodă pentru sortare care stabilește ordinea
    //și realizează sortarea pentru un câmp movies ce
    //va fi obținut cu getter ulterior
    public void sort() {
        int orderrating = 0, orderduration = 0;
        if (filters.getSort().getRating() != null) {
            if (filters.getSort().getRating().equals("decreasing")) {
                orderrating = -1;
            } else if (filters.getSort().getRating().equals("increasing")) {
                orderrating = 1;
            }
        }
        if (filters.getSort().getDuration() != null) {
            if (filters.getSort().getDuration().equals("decreasing")) {
                orderduration = -1;
            } else if (filters.getSort().getDuration().equals("increasing")) {
                orderduration = 1;
            }
        }
        if (orderrating == 0 && orderduration != 0) {
            movies.sort(new Sortbyduration(orderduration));
        } else if (orderrating != 0 && orderduration == 0) {
            movies.sort(new Sortbyrating(orderrating));
        } else {
            movies.sort(new Sortbyboth(orderrating, orderduration));
        }
    }

    /**
     *
     */
    //filtrăm cu ajutorul unei variabile check
    //dacă filmul nu conține un actor sau un gen
    //dintre cele cerute check creste, deci dacă
    //rămâne nul filmul trece cu succes de filtrare
    public void filter() {
        int i, j, check;
        for (i = 0; i < movies.size(); i++) {
            check = 0;
            if (filters.getContains().getActors() != null) {
                for (j = 0; j < filters.getContains().getActors().size(); j++) {
                    if (!movies.get(i).getActors().
                            contains(filters.getContains().getActors().get(j))) {
                        check++;
                        break;
                    }
                }
            }
            if (filters.getContains().getGenre() != null) {
                for (j = 0; j < filters.getContains().getGenre().size(); j++) {
                    if (!movies.get(i).getGenres().
                            contains(filters.getContains().getGenre().get(j))) {
                        check++;
                        break;
                    }
                }
            }
            if (check == 0) {
                filteredmovies.add(movies.get(i));
            }
        }
    }

    public ArrayList<Movie> getFilteredmovies() {
        return filteredmovies;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}
