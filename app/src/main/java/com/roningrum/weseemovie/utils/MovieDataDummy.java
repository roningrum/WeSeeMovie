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
                "October 5th 2018",
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
        movies.add(new Movie(
                "Aquaman(2018)",
                "Action, Adventure, Fantasy",
                "2h 24m",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\\'s half-human, half-Atlantean brother and true heir to the throne.",
                "James Wan",
                "December 7th 2018",
                R.drawable.banner_movie_aquaman,
                R.drawable.poster_aquaman
        ));
        movies.add(new Movie(
                "Avengers: Infinity Wars(2018)",
                "Action, Adventure,\nScience Fiction",
                "2h 29m",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain",
                "Antonie Russo &\n Joe Russo",
                "April 23rd 2018",
                R.drawable.banner_movie_avengers,
                R.drawable.poster_infinity_war
        ));
        movies.add(new Movie(
                "Creed II(2018)",
                "Drama",
                "2h 10m",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family\\'s past, Adonis Creed is up against the challenge of his life..",
                "Steven Caple Jr",
                "November 14th 2018",
                R.drawable.banner_movie_creed,
                R.drawable.poster_creed
        ));
        movies.add(new Movie(
                "Fantastic Beasts: The Crimes of Grindelwald(2018)",
                "Adventure, Fantasy, Family",
                "2h 14m",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "David Yates",
                "November 16th 2018",
                R.drawable.banner_movie_fantasticbeast,
                R.drawable.poster_crimes
        ));
        movies.add(new Movie(
                "Glass(2019)",
                "Thriller, Drama,\nScience Fiction",
                "2h 9m",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "M. Night Shyamalan",
                "January 16th 2019",
                R.drawable.banner_movie_glass,
                R.drawable.poster_glass
        ));
        movies.add(new Movie(
                "Mortal Engines(2018)",
                "Adventure, Fantasy",
                "2h 9m",
                "Many thousands of years in the future Earth’s cities roam the globe on huge wheels devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever",
                "Christian Rivers",
                "December 14th 2018",
                R.drawable.banner_movies_mortal_engines,
                R.drawable.poster_mortal_engines
        ));
        movies.add(new Movie(
                "Robin Hood(2018)",
                "Adventure, Action, Thriller",
                "1h 56m",
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "Otto Bathrust",
                "November 21st 2018",
                R.drawable.banner_movie_robin_hood,
                R.drawable.poster_robin_hood
        ));
        movies.add(new Movie(
                "Serenity(2019)",
                "Thriller, Mystery",
                "1h 46m",
                "Baker Dill is a fishing boat captain leading tours off a tranquil, tropical enclave called Plymouth Island. His quiet life is shattered, however, when his ex-wife Karen tracks him down with a desperate plea for help.",
                "Steven Knight",
                "January 25 2019",
                R.drawable.banner_movie_serenity,
                R.drawable.poster_serenity
        ));
        movies.add(new Movie(
                "Spider-Man: Into the Verse(2018)",
                "Action, Adventure,Animation,\nScience Fiction,\nComedy",
                "1h 57m",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "Rodney Rothman",
                "Desember 1 2019",
                R.drawable.banner_movie_spiderman_intoverse,
                R.drawable.poster_spiderman
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
}
