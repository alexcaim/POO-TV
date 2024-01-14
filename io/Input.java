package io;

import java.util.ArrayList;

public final class Input {

    private ArrayList<Users> users;
    private ArrayList<Movie> movies;
    private ArrayList<Action> actions;

    public ArrayList<Users> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<Users> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }
}
