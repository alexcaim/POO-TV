package sortmode;
import io.Movie;
import java.util.Comparator;

public final class Sortbyboth implements Comparator<Movie> {
    private int orderrating, orderduration;

    /**
     *
     * @param orderrating
     * @param orderduration
     */
    public Sortbyboth(final int orderrating, final int orderduration) {
        this.orderduration = orderduration;
        this.orderrating = orderrating;
    }

    /**
     *
     * @param a the first object to be compared.
     * @param b the second object to be compared.
     * @return
     */
    public int compare(final Movie a, final Movie b) {
        if (a.getDuration() > b.getDuration()) {
            return orderduration;
        } else if (a.getDuration() == b.getDuration()) {
            if (a.getRating() > b.getRating()) {
                return orderrating;
            } else if (a.getRating() == b.getRating()) {
                return 0;
            } else {
                return -orderrating;
            }
        } else return -orderduration;
    }
}
