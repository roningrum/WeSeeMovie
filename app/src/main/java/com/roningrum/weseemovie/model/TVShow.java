package com.roningrum.weseemovie.model;

public class TVShow {
    private int id;
    private String name;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private String first_air_date;
    private int number_of_seasons;
    private double vote_average;

    public TVShow() {
    }

    public TVShow(int id, String name, String poster_path, String backdrop_path, String overview, String first_air_date, double vote_average) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.vote_average = vote_average;
    }

    public TVShow(int id, String name, String poster_path, String backdrop_path, String overview, String first_air_date, int number_of_seasons, double vote_average) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.number_of_seasons = number_of_seasons;
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
}
