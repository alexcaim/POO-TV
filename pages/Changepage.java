package pages;
import io.Input;
import io.AllTypesOfPrint;
import io.Movie;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

//un visitor care are și links cu Onpage
//scopul acestui visitor este de a realiza trecerea
//de la o pagina la alta
//el primește acțiunea și dacă este cazul o eroare de
//la Onpage iar pagereturn devine pagina destinație
public final class Changepage implements visitor, Linksbetweenvisitors {
    private final Input input;
    private int indexaction = 0, error = 1, i = 0;
    private Pages pagereturn;

    private int currentuser;
    private ArrayNode output;
    private ArrayList<Movie> moviesavaible;
    private AllTypesOfPrint all = new AllTypesOfPrint();

    public Changepage(final Input input) {
        this.input = input;
    }

    /**
     *
     * @param page
     */
    public void visit(final Homepagena page) {
        if (input.getActions().get(indexaction).getType().equals("change page")) {
            if (input.getActions().get(indexaction).getPage().equals("login")) {
                pagereturn = new Login();
            } else if (input.getActions().get(indexaction).getPage().equals("register")) {
                pagereturn = new Register();
            } else {
                output = all.printUser(output, input.getUsers().get(0), 0);
            }
        }
    }

    /**
     *
     * @param page
     */
    public void visit(final Homepagea page) {
        error = 1;
        if (input.getActions().get(indexaction).getType().equals("change page")) {
            switch (input.getActions().get(indexaction).getPage()) {
                case "movies":
                    pagereturn = new Movies();
                    break;
                case "upgrades":
                    pagereturn = new Upgrades();
                    break;
                case "logout":
                    pagereturn = new Homepagena();
                    break;
                default:
                    if (!input.getActions().get(indexaction).
                            getPage().equals("homepage autentificat")) {
                        output = all.printUser(output, input.getUsers().get(0), 0);
                    }
                    pagereturn = page;
                break;
            }
        }
    }

    /**
     *
     * @param page
     */
    //verificăm dacă utilizatorul este eligibil
    //și îl autentificăm
    public void visit(final Login page) {
        if (error == 0) {
            pagereturn = new Homepagena();
        } else {
            pagereturn = new Homepagea();
        }
    }

    /**
     *
     * @param page
     */
    //verificăm dacă utilizatorul poate fi înregistrat
    //și îl autentificăm
    public void visit(final Register page) {
        if (error == 0) {
            pagereturn = new Homepagena();
        } else {
            pagereturn = new Homepagea();
        }
    }

    /**
     *
     * @param page
     */
    public void visit(final Movies page) {
        int sw = 0;
        if (input.getActions().get(indexaction).getType().equals("change page")) {

            switch (input.getActions().get(indexaction).getPage()) {
                case "homepage autentificat":
                    pagereturn = new Homepagea();
                    break;
                case "see details":
                    for (i = 0; i < moviesavaible.size(); i++) {
                        //verificăm dacă există filmul
                        if (input.getActions().get(indexaction).
                                getMovie().equals(moviesavaible.get(i).getName())) {
                            output = all.printMovie(output,
                                    input.getUsers().get(currentuser), moviesavaible.get(i), 1);
                            pagereturn = new Seedetails(moviesavaible.get(i), 0, 0);
                            sw = 1;
                        }
                    }
                    if (sw == 0) {
                        output = all.printUser(output, input.getUsers().get(0), 0);
                        pagereturn = page;
                        moviesavaible = new ArrayList<>();
                        //refacem array-ul de filme disponibile
                        for (i = 0; i < input.getMovies().size(); i++) {
                            if (!input.getMovies().get(i).getCountriesBanned().contains(
                                    input.getUsers().get(currentuser).
                                            getCredentials().getCountry())) {
                                moviesavaible.add(input.getMovies().get(i));
                            }
                        }
                    }
                    break;
                case "logout":
                    pagereturn = new Homepagena();
                    break;
                default:
                    if (!input.getActions().get(indexaction).getPage().equals("movies")) {
                        output = all.printUser(output, input.getUsers().get(0), 0);
                        pagereturn = page;
                    } else {
                        pagereturn = new Movies();
                    }
                    break;
            }
        }
    }

    /**
     *
     * @param page
     */
    public void visit(final Seedetails page) {
        if (input.getActions().get(indexaction).getType().equals("change page")) {
            switch (input.getActions().get(indexaction).getPage()) {
                case "homepage autentificat" :
                    pagereturn = new Homepagea();
                    break;
                case "movies" :
                    pagereturn = new Movies();
                    moviesavaible = new ArrayList<>();
                    for (i = 0; i < input.getMovies().size(); i++) {
                        if (!input.getMovies().get(i).getCountriesBanned().contains(
                                input.getUsers().get(currentuser).getCredentials().getCountry())) {
                            moviesavaible.add(input.getMovies().get(i));
                        }
                    }
                    break;
                case "upgrades" :
                    pagereturn = new Upgrades();
                    break;
                case "logout" :
                    pagereturn = new Homepagena();
                    break;
                default :
                    if (!input.getActions().get(indexaction).getPage().equals("see details")) {
                        output = all.printUser(output, input.getUsers().get(0), 0);
                    }
                    pagereturn = page;
                    break;
            }
        }
    }

    /**
     *
     * @param page
     */
    public void visit(final Upgrades page) {
        if (input.getActions().get(indexaction).getType().equals("change page")) {
            switch (input.getActions().get(indexaction).getPage()) {
                case "homepage autentificat" :
                    pagereturn = new Homepagea();
                    break;
                case "movies" :
                    pagereturn = new Movies();
                    break;
                case "logout" :
                    pagereturn = new Homepagena();
                    break;
                default :
                    if (!input.getActions().get(indexaction).getPage().equals("upgrades")) {
                        output = all.printUser(output, input.getUsers().get(0), 0);
                    }
                    pagereturn = page;
                    break;
            }
        }
    }

    @Override
    public Input getInput() {
        return input;
    }

    public void setIndexaction(final int indexaction) {
        this.indexaction = indexaction;
    }

    public Pages getPagereturn() {
        return pagereturn;
    }

    public int getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(final int currentuser) {
        this.currentuser = currentuser;
    }

    public ArrayList<Movie> getMoviesavaible() {
        return moviesavaible;
    }

    public ArrayNode getOutput() {
        return output;
    }

    public void setOutput(final ArrayNode output) {
        this.output = output;
    }

    public void setMoviesavaible(final ArrayList<Movie> moviesavaible) {
        this.moviesavaible = moviesavaible;
    }

    /**
     *
     * @param error
     */
    public void seterror(final int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }
    public void setPagereturn(final Pages pagereturn) {
        this.pagereturn = pagereturn;
    }
}
