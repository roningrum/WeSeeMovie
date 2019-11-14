package com.roningrum.weseemovie.ui.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.Movie;
import com.roningrum.weseemovie.utils.GlideApp;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_FILMS = "extra_movies";
    private DetailMovieViewModel detailMovieViewModel;

    private TextView tvNameMoviesDetail;
    private TextView tvGenreMoviesDetail;
    private TextView tvDurationMoviesDetail;
    private TextView tvReleaseDateMoviesDetail;
    private TextView tvDirectorMoviesDetail;
    private TextView tvSynopsisMoviesDetail;
    private ImageView imgPosterDetail;
    private ImageView imgBannerDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvNameMoviesDetail = findViewById(R.id.tv_name_movie_detail);
        tvGenreMoviesDetail = findViewById(R.id.tv_genre_movie_detail);
        tvReleaseDateMoviesDetail = findViewById(R.id.tv_release_time_detail);
        tvDirectorMoviesDetail = findViewById(R.id.tv_director_detail);
        imgPosterDetail = findViewById(R.id.img_movie_poster_detail);
        imgBannerDetail = findViewById(R.id.img_detail_photo_banner);
        tvSynopsisMoviesDetail = findViewById(R.id.tv_sinopsis_detail);
        tvDurationMoviesDetail = findViewById(R.id.tv_duration_movie_item);

        detailMovieViewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);
        Movie movie = getIntent().getParcelableExtra(EXTRA_FILMS);
        if (movie != null) {
            detailMovieViewModel.setMovie(movie);
            showDetailMovie();
        }


    }

    private void showDetailMovie() {
        Movie movieData = detailMovieViewModel.getMovie();
        tvNameMoviesDetail.setText(movieData.getName());
        tvDurationMoviesDetail.setText(movieData.getDuration());
        tvGenreMoviesDetail.setText(movieData.getGenre());
        tvReleaseDateMoviesDetail.setText(movieData.getDate());
        tvDirectorMoviesDetail.setText(movieData.getCreator());
        tvSynopsisMoviesDetail.setText(movieData.getSynopsis());

        GlideApp.with(getApplicationContext()).load(movieData.getPoster()).into(imgPosterDetail);
        GlideApp.with(getApplicationContext()).load(movieData.getPhotoBanner()).into(imgBannerDetail);
    }
}
