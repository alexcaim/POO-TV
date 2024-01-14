package pages;
import io.Input;
import io.Movie;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public interface Linksbetweenvisitors {
    /**
     *
     * @return
     */
    int getError();

    /**
     *
     * @return
     */
    Input getInput();

    /**
     *
     * @return
     */
    int getCurrentuser();

    /**
     *
     * @return
     */
    ArrayNode getOutput();

    /**
     *
     * @return
     */
    ArrayList<Movie> getMoviesavaible();
}
