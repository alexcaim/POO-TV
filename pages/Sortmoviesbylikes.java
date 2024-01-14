package pages;

import java.util.Comparator;
import io.Movie;

/**
 *
 */
public class Sortmoviesbylikes implements Comparator<Movie> {
    public int compare(final Movie a, final Movie b) {
        if (a.getNumLikes() > b.getNumLikes()) {
            return -1;
        } else if (a.getNumLikes() == b.getNumLikes()) {
            return 0;
        } else {
            return 1;
        }
    }
}
