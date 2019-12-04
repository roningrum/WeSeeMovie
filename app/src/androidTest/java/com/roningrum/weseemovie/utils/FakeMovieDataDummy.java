package com.roningrum.weseemovie.utils;

import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.locale.entity.TVShow;

import java.util.ArrayList;

public class FakeMovieDataDummy {
    public static ArrayList<Movie> generateDummyMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(
                330457,
                "/ww5aGS5H2tUtckxFFNOJE2790S7.jpg",
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "/mbm8k3GFhXS0ROd9AD1gqYbIFbM.jpg",
                "Frozen II",
                7.1));
        return movies;
    }

    public static Movie getMovieDetail() {
        return new Movie(
                330457,
                "/ww5aGS5H2tUtckxFFNOJE2790S7.jpg",
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "/mbm8k3GFhXS0ROd9AD1gqYbIFbM.jpg",
                "Frozen II",
                104,
                "2019-11-20",
                7.1);
    }

    public static ArrayList<TVShow> generateDummyTVShow() {
        ArrayList<TVShow> tvShows = new ArrayList<>();
        tvShows.add(new TVShow(
                82856,
                "The Mandalorian",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                "2019-11-12",
                7.7));
        return tvShows;
    }

    public static TVShow getTvShowsDetail() {
        return new TVShow(
                82856,
                "The Mandalorian",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                "2019-11-12",
                "1",
                7.7
        );
    }

}
