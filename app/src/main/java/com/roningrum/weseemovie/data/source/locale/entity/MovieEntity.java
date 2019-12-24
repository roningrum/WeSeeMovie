package com.roningrum.weseemovie.data.source.locale.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Movies")
public class MovieEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "backdrop_path")
    private String backdrop_path;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "poster_path")
    private String poster_path;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "runtime")
    private int runtime;

    @ColumnInfo(name = "release_date")
    private String release_date;

    @ColumnInfo(name = "vote_average")
    private double vote_average;

    @ColumnInfo(name = "favorite")
    private boolean favorite = false;


    public MovieEntity(int id, String backdrop_path, String overview, String poster_path, String title, int runtime, String release_date, double vote_average, Boolean favorite) {
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.poster_path = poster_path;
        this.title = title;
        this.runtime = runtime;
        this.release_date = release_date;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
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
