package com.roningrum.weseemovie.data;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShow implements Parcelable {
    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
    private String name;
    private String genre;
    private String duration;
    private String synopsis;
    private String creator;
    private int PhotoBanner;
    private int Poster;

    public TVShow(String name, String genre, String duration, String synopsis, String creator, int photoBanner, int poster) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.synopsis = synopsis;
        this.creator = creator;
        PhotoBanner = photoBanner;
        Poster = poster;
    }

    private TVShow(Parcel in) {
        name = in.readString();
        genre = in.readString();
        duration = in.readString();
        synopsis = in.readString();
        creator = in.readString();
        PhotoBanner = in.readInt();
        Poster = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(genre);
        dest.writeString(duration);
        dest.writeString(synopsis);
        dest.writeString(creator);
        dest.writeInt(PhotoBanner);
        dest.writeInt(Poster);
    }
}