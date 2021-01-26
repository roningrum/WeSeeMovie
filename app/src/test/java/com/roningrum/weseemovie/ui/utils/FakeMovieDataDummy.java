package com.roningrum.weseemovie.ui.utils;

import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.model.Movie;
import com.roningrum.weseemovie.model.TVShow;

import java.util.ArrayList;
import java.util.List;

public class FakeMovieDataDummy {
    public static List<Movie> generateDummyMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(
                330457,
                "/ww5aGS5H2tUtckxFFNOJE2790S7.jpg",
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "/mbm8k3GFhXS0ROd9AD1gqYbIFbM.jpg",
                "Frozen II",
                "2019-11-20",
                7.1));
        return movies;
    }

    public static List<TVShow> generateDummyTVShow() {
        List<TVShow> tvShows = new ArrayList<>();
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

    //Locale
    public static List<MovieEntity> generateDummyMovieLocals() {
        List<MovieEntity> movieEntities = new ArrayList<>();
        movieEntities.add(new MovieEntity(
                330457,
                "/ww5aGS5H2tUtckxFFNOJE2790S7.jpg",
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "/mbm8k3GFhXS0ROd9AD1gqYbIFbM.jpg",
                "Frozen II",
                "2019-11-20",
                7.1,
                null));
        return movieEntities;
    }

    public static List<TVShowEntity> generateDummyTVLocals() {
        List<TVShowEntity> tvShowEntities = new ArrayList<>();
        tvShowEntities.add(new TVShowEntity(
                82856,
                "The Mandalorian",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                "2019-11-12",
                7.7,
                null));
        return tvShowEntities;
    }

    public static MovieEntity getMovieDetails(MovieEntity movieEntity, boolean favorited) {
        MovieEntity movies;
        movies = movieEntity;
        movies.setFavorite(favorited);
        return movies;
    }

    public static TVShowEntity getTvShowDetails(TVShowEntity tvShowEntity, boolean favorited) {
        TVShowEntity tvShows;
        tvShows = tvShowEntity;
        tvShows.setFavorite(favorited);
        return tvShows;
    }



}
