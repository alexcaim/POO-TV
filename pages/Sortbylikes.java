package pages;

import java.util.Comparator;

/**
 *
 */
public class Sortbylikes implements Comparator<Ranking> {
    public int compare(final Ranking a, final Ranking b) {
        if (a.getNumberoflikes() > b.getNumberoflikes()) {
            return -1;
        } else if (a.getNumberoflikes() == b.getNumberoflikes()) {
            return a.getGenre().compareTo(b.getGenre());
        } else {
            return 1;
        }
    }
}
