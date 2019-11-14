package com.roningrum.weseemovie.ui.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.AppBarLayout;
import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.Movie;
import com.roningrum.weseemovie.utils.GlideApp;

import java.util.Objects;

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

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;


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

        appBarLayout = findViewById(R.id.appBar);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        detailMovieViewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);
        showDetailMovie();


    }

    private void showDetailMovie() {
        Movie movie = getIntent().getParcelableExtra(EXTRA_FILMS);
        if (movie != null) {
            detailMovieViewModel.setMovie(movie);
            Movie movieData = detailMovieViewModel.getMovie();

            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isVisible = true;
                int scrollRange = -1;

                @SuppressLint("ResourceAsColor")
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        toolbar.setTitle(movieData.getName());
                        isVisible = true;
                    } else if (isVisible) {
                        toolbar.setTitle("");
                        isVisible = false;
                    }
                }
            });
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
}
