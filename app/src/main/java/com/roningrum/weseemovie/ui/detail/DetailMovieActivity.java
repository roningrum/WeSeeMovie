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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_FILMS = "extra_movies";
    private DetailMovieViewModel detailMovieViewModel;

    @BindView(R.id.tv_name_movie_detail)
    TextView tvNameMoviesDetail;
    @BindView(R.id.tv_genre_movie_detail)
    TextView tvGenreMoviesDetail;
    @BindView(R.id.tv_duration_movie_item)
    TextView tvDurationMoviesDetail;
    @BindView(R.id.tv_release_time_detail)
    TextView tvReleaseDateMoviesDetail;
    @BindView(R.id.tv_director_detail)
    TextView tvDirectorMoviesDetail;
    @BindView(R.id.tv_sinopsis_detail)
    TextView tvSynopsisMoviesDetail;

    @BindView(R.id.img_movie_poster_detail)
    ImageView imgPosterDetail;
    @BindView(R.id.img_detail_photo_banner)
    ImageView imgBannerDetail;

    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

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
