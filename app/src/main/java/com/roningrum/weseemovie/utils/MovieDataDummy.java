package com.roningrum.weseemovie.utils;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.Movie;

import java.util.ArrayList;

public class MovieDataDummy {
    public static ArrayList<Movie> generateDummyMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(
                "A Star is Born(2018)",
                "Drama,Romance, Music",
                "October 5th 2018",
                "Seasoned musician Jackson Maine discovers  and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\\'s career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons",
                "Bradley Cooper",
                "2h 15m",
                R.drawable.banner_movie_astarisborn,
                R.drawable.poster_a_start_is_born
        ));
        movies.add(new Movie(
                "Alita: Battle Angel(2019)",
                "Action,Science Fiction, \nThriller, Adventure",
                "2h 2m",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Robert Rodriguez",
                "January 31st 2019",
                R.drawable.banner_movie_alita,
                R.drawable.poster_alita
        ));
        return movies;
    }

    public static Movie getMovie() {
        for (int i = 0; i < generateDummyMovies().size(); i++) {
            Movie movie = generateDummyMovies().get(i);
            if (movie != null) {
                return movie;
            }
        }
        return null;
    }

//    public static ArrayList<Movie> generateDummyTvs() {
//        ArrayList<Movie> tvs = new ArrayList<>();
//        return tvs;
//    }
}
