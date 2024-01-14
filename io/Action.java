package io;
// clasa în ale cărei câmpuri care se memorează comenzile primite din input


public final class Action {
    private final String type = null;
    private final String page = null;

    private final String objectType = null;

    private final String feature = null;

    private final Credentials credentials = null;

    private final String startsWith = null;

    private final Filters filters = null;

    private final String movie = null;

    private String subscribedGenre;

    private Movie addedMovie;

    private String deletedMovie;


    private int rate;

    private int count;

    public int getRate() {
        return rate;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getFeature() {
        return feature;
    }


    public String getType() {
        return type;
    }


    public String getPage() {
        return page;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public Filters getFilters() {
        return filters;
    }

    public String getMovie() {
        return movie;
    }

    public int getCount() {
        return count;
    }
    public String getSubscribedGenre() {
        return subscribedGenre;
    }
    public Movie getAddedMovie() {
        return addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

}
