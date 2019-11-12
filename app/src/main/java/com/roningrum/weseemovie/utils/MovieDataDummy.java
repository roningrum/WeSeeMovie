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
                "2h 15m",
                "Seasoned musician Jackson Maine discovers  and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\\'s career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons",
                "Bradley Cooper",
                R.drawable.banner_movie_astarisborn,
                R.drawable.poster_a_start_is_born
        ));
        movies.add(new Movie(
                "Alita: Battle Angel(2019)",
                "Action,Science Fiction,\\nThriller, Adventure",
                "2h 2m",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Robert Rodriguez",
                R.drawable.banner_movie_alita,
                R.drawable.poster_alita
        ));
        movies.add(new Movie(
                "Aquaman(2018)",
                "Action, Adventure, Fantasy",
                "2h 24m",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\'s half-human, half-Atlantean brother and true heir to the throne.",
                "James Wan",
                R.drawable.banner_movie_aquaman,
                R.drawable.poster_aquaman
        ));
        movies.add(new Movie(
                "Avengers: Infinity Wars(2018)",
                "Action, Adventure,\\nScience Fiction",
                "2h 29m",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Antonie Russo &amp; \\n Joe Russo",
                R.drawable.banner_movie_avengers,
                R.drawable.poster_infinity_war
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
