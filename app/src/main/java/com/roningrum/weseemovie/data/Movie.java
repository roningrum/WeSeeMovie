package com.roningrum.weseemovie.data;

public class Movie {
    private String name;
    private String genre;
    private String duration;
    private String synopsis;
    private String creator;
    private int PhotoBanner;
    private int Poster;

    public Movie(String name, String genre, String duration, String synopsis, String creator, int photoBanner, int poster) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.synopsis = synopsis;
        this.creator = creator;
        PhotoBanner = photoBanner;
        Poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getPhotoBanner() {
        return PhotoBanner;
    }

    public void setPhotoBanner(int photoBanner) {
        PhotoBanner = photoBanner;
    }

    public int getPoster() {
        return Poster;
    }

    public void setPoster(int poster) {
        Poster = poster;
    }
}
