package pages;
import io.*;
import sortmode.Filteredmovies;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public final class Onpage implements visitor, Linksbetweenvisitors {
    private Input input;
    private int indexaction, i, error = 1;
    private int currentuser;
    private ArrayNode output;
    private AllTypesOfPrint all = new AllTypesOfPrint();
    private ArrayList<Movie> moviesafterfilter = new ArrayList<>();
    private ArrayList<Movie> moviesavaible = new ArrayList<>();
    private ArrayList<Movie> movieclone;

    public Onpage(final Input input) {
        this.input = input;
    }

    /**
     *
     * @param page
     */
    public void visit(final Homepagena page) {
        moviesavaible = new ArrayList<>();
    }

    /**
     *
     * @param page
     */
    public void visit(final Homepagea page) {
        error = 1;
        if (input.getActions().get(indexaction).getType().equals("on page")) {
            error = 0;
            output = all.printUser(output, input.getUsers().get(0), 0);
        }

    }

    /**
     *
     * @param page
     */
    public void visit(final Login page) {
        if (input.getActions().get(indexaction).getType().equals("on page")) {
            error = 0;
            if (input.getActions().get(indexaction).getFeature().equals("login")) {
                for (i = 0; i < input.getUsers().size(); i++) {
                    if (input.getUsers().get(i).getCredentials().
                            getName().equals(input.getActions().
                            get(indexaction).getCredentials().getName())
                            && input.getUsers().get(i).getCredentials().getPassword().
                            equals(input.getActions().get(indexaction).
                                    getCredentials().getPassword())) {
                        error = 1;
                        currentuser = i;
                        for (int j = 0; j < input.getMovies().size(); j++) {
                            if (!input.getMovies().get(j).getCountriesBanned().contains(
                                    input.getUsers().get(currentuser).
                                            getCredentials().getCountry())) {
                                moviesavaible.add(input.getMovies().get(j));
                            }
                        }
                        break;
                    }
                }
            }
            output = all.printUser(output, input.getUsers().get(currentuser), error);
        }
    }

    /**
     *
     * @param page
     */
    public void visit(final Register page) {
        if (input.getActions().get(indexaction).getType().equals("on page")) {
            error = 1;
            if (input.getActions().get(indexaction).getFeature().equals("register")) {
                for (i = 0; i < input.getUsers().size(); i++) {
                    if (input.getUsers().get(i).getCredentials().
                            getName().equals(input.getActions().
                            get(indexaction).getCredentials().getName())) {
                        error = 0;
                    }
                }
            }
            if (error == 1) {
                Users newuser = new Users();
                newuser.setCredentials(input.getActions().
                        get(indexaction).getCredentials());
                input.getUsers().add(newuser);
                currentuser = input.getUsers().size() - 1;
                for (i = 0; i < input.getMovies().size(); i++) {
                    if (!input.getMovies().get(i).getCountriesBanned().contains(
                            input.getUsers().get(currentuser).
                                    getCredentials().getCountry())) {
                        moviesavaible.add(input.getMovies().get(i));
                    }
                }
            }
            output = all.printUser(output, input.getUsers().get(currentuser), error);
        }
    }

    /**
     *
     * @param page
     */
    public void visit(final Movies page) {
        moviesafterfilter = new ArrayList<>();
        if (page.getNumberofvisits() == 0) {
            output = all.printMovies(output, input.getUsers().get(currentuser),
                    moviesavaible, 1);
        }
        if (input.getActions().get(indexaction).getType().equals("on page")) {
            if (input.getActions().get(indexaction).getFeature().equals("search")) {
                for (i = 0; i < moviesavaible.size(); i++) {
                    if (moviesavaible.get(i).getName().startsWith(input.getActions().
                            get(indexaction).getStartsWith())) {
                        moviesafterfilter.add(moviesavaible.get(i));
                    }
                }
                output = all.printMovies(output, input.getUsers().
                        get(currentuser), moviesafterfilter, 1);
            } else if (input.getActions().get(indexaction).getFeature().equals("filter")) {
                movieclone = new ArrayList<>(moviesavaible);
                Filteredmovies object = new Filteredmovies(input.getActions().
                        get(indexaction).getFilters(), movieclone);
                if (input.getActions().get(indexaction).getFilters().getSort() != null) {
                    object.sort();
                    movieclone = object.getMovies();
                }
                if (input.getActions().get(indexaction).getFilters().getContains() != null) {
                    object.filter();
                    moviesavaible = object.getFilteredmovies();
                    output = all.printMovies(output, input.getUsers().
                            get(currentuser), moviesavaible, 1);
                } else {
                    moviesavaible = movieclone;
                    output = all.printMovies(output, input.getUsers().
                            get(currentuser), moviesavaible, 1);
                }
            } else {
                output = all.printUser(output, input.getUsers().
                        get(currentuser), 0);
            }
        }
    }

    /**
     *
     * @param page
     */
    public void visit(final Seedetails page) {
        if (input.getActions().get(indexaction).getType().equals("on page")) {
            int ok = 1;
            page.setCanwatch(0);
            page.setCanrate(0);
            page.setRated(0);
            page.setLiked(0);
            for (Movie movie : input.getUsers().get(currentuser).getPurchasedMovies()) {
                if (page.getMovie().getName().equals(movie.getName())) {
                    page.setCanwatch(1);
                }
            }
            for (Movie movie : input.getUsers().get(currentuser).getWatchedMovies()) {
                if (page.getMovie().getName().equals(movie.getName())) {
                    page.setCanrate(1);
                }
            }
            for (Movie movie : input.getUsers().get(currentuser).getRatedMovies()) {
                if (page.getMovie().getName().equals(movie.getName())) {
                    page.setRated(1);
                }
            }
            for (Movie movie : input.getUsers().get(currentuser).getLikedMovies()) {
                if (page.getMovie().getName().equals(movie.getName())) {
                    page.setLiked(1);
                }
            }
            if (input.getActions().get(indexaction).getFeature().equals("purchase")
                    &&  page.getCanwatch() == 0) {
                    if (input.getUsers().get(currentuser).getCredentials().
                            getAccountType().equals("premium")
                            && input.getUsers().get(currentuser).getNumFreePremiumMovies() != 0) {
                        input.getUsers().get(currentuser).setNumFreePremiumMovies(input.getUsers().
                                get(currentuser).getNumFreePremiumMovies() - 1);
                        input.getUsers().get(currentuser).getPurchasedMovies().add(page.getMovie());
                    } else if (input.getUsers().get(currentuser).getTokensCount() != 1) {
                        input.getUsers().get(currentuser).setTokensCount(input.
                                getUsers().get(currentuser).getTokensCount() - 2);
                        input.getUsers().get(currentuser).getPurchasedMovies().add(page.getMovie());
                    } else {
                        ok = 0;
                        output = all.printUser(output, input.getUsers().get(currentuser), 0);
                    }
                if (ok == 1) {
                    output = all.printMovie(output,
                            input.getUsers().get(currentuser), page.getMovie(), 1);
                }
            } else if (input.getActions().get(indexaction).getFeature().
                    equals("watch") && page.getCanwatch() == 1) {
                if (page.getCanrate() == 0) {
                    input.getUsers().get(currentuser).getWatchedMovies().add(page.getMovie());
                }
                output = all.printMovie(output, input.getUsers().
                        get(currentuser), page.getMovie(), 1);
            } else if (input.getActions().get(indexaction).getFeature().
                    equals("rate") && page.getCanrate() == 1) {
                int sw = 0;
                if (input.getActions().get(indexaction).getRate() > 0
                        && input.getActions().get(indexaction).getRate() < 6) {
                        for (Userandrate userandrate : page.getMovie().getUserandrates()) {
                            if (userandrate.getUser().getCredentials().getName().
                                    equals(input.getUsers().get(currentuser).
                                            getCredentials().getName())) {
                                page.getMovie().setSumofRatings(page.getMovie().
                                        getSumofRatings() - userandrate.getRate());
                                userandrate.setRate(input.getActions().get(indexaction).getRate());
                                page.getMovie().setNumRatings(page.getMovie().
                                        getNumRatings() - 1);
                                sw = 1;
                            }
                        }
                    if (sw == 0) {
                        Userandrate newuandr = new Userandrate();
                        newuandr.setUser(input.getUsers().get(currentuser));
                        newuandr.setRate(input.getActions().get(indexaction).getRate());
                        page.getMovie().getUserandrates().add(newuandr);
                    }
                    page.getMovie().setSumofRatings(page.getMovie().
                            getSumofRatings() + input.getActions().get(indexaction).getRate());
                    page.getMovie().setNumRatings(page.getMovie().getNumRatings() + 1);
                    page.getMovie().setRating((double)page.getMovie().getSumofRatings()
                            / page.getMovie().getNumRatings());
                    if (page.getRated() == 0) {
                        input.getUsers().get(currentuser).getRatedMovies().add(page.getMovie());
                    }
                    output = all.printMovie(output, input.getUsers().
                            get(currentuser), page.getMovie(), 1);
                } else {
                    output = all.printUser(output, input.getUsers().get(currentuser), 0);
                }
            } else if (input.getActions().get(indexaction).
                             getFeature().equals("like") && page.getCanrate() == 1) {
                page.getMovie().setNumLikes(page.getMovie().getNumLikes() + 1);
                if (page.getLiked() == 0) {
                    input.getUsers().get(currentuser).getLikedMovies().add(page.getMovie());
                }
                output = all.printMovie(output, input.getUsers().
                        get(currentuser), page.getMovie(), 1);
            } else if (input.getActions().get(indexaction).getFeature().equals("subscribe")) {
                if (page.getMovie().getGenres().contains(input.getActions().
                        get(indexaction).getSubscribedGenre())
                && !input.getUsers().get(currentuser).getGenreSubscribe().
                        contains(input.getActions().get(indexaction).getSubscribedGenre())) {
                    input.getUsers().get(currentuser).getGenreSubscribe().
                            add(input.getActions().get(indexaction).getSubscribedGenre());
                }
                else {
                    output = all.printUser(output, input.getUsers().get(currentuser), 0);
                }
            } else {
                output = all.printUser(output, input.getUsers().get(currentuser), 0);
            }
        }
    }

    /**
     *
     * @param page
     */
    public void visit(final Upgrades page) {
        if (input.getActions().get(indexaction).getType().equals("on page")) {
            if (input.getActions().get(indexaction).getFeature().equals("buy tokens")) {
                if (input.getActions().get(indexaction).getCount() <= input.
                        getUsers().get(currentuser).getCredentials().getBalance()) {
                    input.getUsers().get(currentuser).setTokensCount(input.getUsers().
                            get(currentuser).getTokensCount()
                            + input.getActions().get(indexaction).getCount());
                    input.getUsers().get(currentuser).getCredentials().
                            setBalance(input.getUsers().
                                    get(currentuser).getCredentials().getBalance()
                            - input.getActions().get(indexaction).getCount());
                }
            } else if (input.getActions().get(indexaction).
                    getFeature().equals("buy premium account")) {
                if (input.getUsers().get(currentuser).
                        getCredentials().getAccountType().equals("standard")
                        && input.getUsers().get(currentuser).getTokensCount() >= 10) {
                    input.getUsers().get(currentuser).
                            getCredentials().setAccountType("premium");
                    input.getUsers().get(currentuser).
                            setTokensCount(input.getUsers().get(currentuser).getTokensCount() - 10);
                }
            } else {
                output = all.printMovies(output, input.getUsers().get(currentuser),
                        moviesavaible, 0);
            }
        }
    }

    public int getError() {
        return error;
    }

    public void setError(final int error) {
        this.error = error;
    }

    public Input getInput() {
        return input;
    }

    public void setIndexaction(final int indexaction) {
        this.indexaction = indexaction;
    }

    public int getCurrentuser() {
        return currentuser;
    }

    public ArrayList<Movie> getMoviesavaible() {
        return moviesavaible;
    }

    public void setMoviesavaible(final ArrayList<Movie> moviesavaible) {
        this.moviesavaible = moviesavaible;
    }

    public ArrayNode getOutput() {
        return output;
    }

    public void setOutput(final ArrayNode output) {
        this.output = output;
    }

    public void setInput(final Input input) {
        this.input = input;
    }
}
