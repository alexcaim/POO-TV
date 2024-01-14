package sortmode;
import io.Movie;
import java.util.Comparator;

public final class Sortbyduration implements Comparator<Movie> {
    private int order;

    //order == 1 <=> ordonăm crescător
    //order == -1 <=> ordonăm descrescător
    /**
     *
     * @param order
     */
    public Sortbyduration(final int order) {
        this.order = order;
    }

    /**
     *
     * @param a the first object to be compared.
     * @param b the second object to be compared.
     * @return
     */
    public int compare(final Movie a, final Movie b) {
        if (a.getDuration() > b.getDuration()) {
            return order;
        } else if (a.getDuration() == b.getDuration()) {
            return 0;
        } else {
            return -order;
        }
    }
}
