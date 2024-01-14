package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.AllTypesOfPrint;
import io.Input;
import io.Movie;

import java.util.ArrayList;

public class MovieSubject {
    private ArrayList<Observer> observers = new ArrayList<>();
    private Movie movie;
    private ArrayNode output;
    private Input input;
    private AllTypesOfPrint all = new AllTypesOfPrint();
    public MovieSubject(final Input input, final Movie movie) {
        this.input = input;
        this.movie = movie;
    }

    /**
     *
     * @param observer
     */
    //adăugăm clasele care implementează observer
    //adică observerii de add și delete
    public void addobserver(final Observer observer) {
        observers.add(observer);
    }

    /**
     *
     */
    //trimite semnalul care le cere să-și dea update
    public void changemade() {
        notifyObservers();
    }
    /**
     *
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
            if (!observer.geterror()) {
                output = all.printUser(output, input.getUsers().get(0), 0);
            }
        }
    }

    /**
     *
     * @return
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     *
     * @param movie
     */
    public void setMovie(final Movie movie) {
        this.movie = movie;
    }

    /**
     *
     * @return
     */
    public Input getInput() {
        return input;
    }

    /**
     *
     * @param input
     */
    public void setInput(final Input input) {
        this.input = input;
    }

    /**
     *
     * @return
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     *
     * @param output
     */
    public void setOutput(final ArrayNode output) {
        this.output = output;
    }
}
