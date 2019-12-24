package com.roningrum.weseemovie.data.source.locale.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.roningrum.weseemovie.data.source.remote.response.Constant;

@Entity(tableName = "TvShows")
public class TVShowEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "poster_path")
    private String poster_path;

    @ColumnInfo(name = "backdrop_path")
    private String backdrop_path;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "first_air_date")
    private String first_air_date;

    @ColumnInfo(name = "number_of_seasons")
    private String number_of_seasons;

    @ColumnInfo(name = "vote_average")
    private double vote_average;

    @ColumnInfo(name = "favorite")
    private boolean favorite = false;


    public TVShowEntity(int id, String name, String poster_path, String backdrop_path, String overview, String first_air_date, String number_of_seasons, double vote_average, Boolean favorite) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.number_of_seasons = number_of_seasons;
        this.vote_average = vote_average;
        if (favorite != null) {
            this.favorite = favorite;
        }
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
        return Constant.IMAGE_URL + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return Constant.IMAGE_URL + backdrop_path;
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

    public String getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(String number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
