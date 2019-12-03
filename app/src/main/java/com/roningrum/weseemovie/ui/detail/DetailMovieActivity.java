package com.roningrum.weseemovie.ui.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.AppBarLayout;
import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.utils.DateHelper;
import com.roningrum.weseemovie.utils.GlideApp;
import com.roningrum.weseemovie.viewmodel.ViewModelFactory;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_FILMS = "extra_movies";
    private DetailMovieViewModel detailMovieViewModel;

    @BindView(R.id.tv_name_movie_detail)
    TextView tvNameMoviesDetail;
    @BindView(R.id.tv_rate_movie)
    TextView tvRateMovie;
    @BindView(R.id.tv_duration_movie_item)
    TextView tvDurationMoviesDetail;
    @BindView(R.id.tv_release_time_detail)
    TextView tvReleaseDateMoviesDetail;
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
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pbLoading.setVisibility(View.VISIBLE);
        detailMovieViewModel = obtainViewModel(this);
        showDetailMovie();

    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    private void showDetailMovie() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int movieId = extras.getInt(EXTRA_FILMS);
            detailMovieViewModel.getMovieDetail(movieId).observe(this, movie -> {
                pbLoading.setVisibility(View.GONE);
                appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    boolean isVisible = true;
                    int scrollRange = -1;


                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (scrollRange == -1) {
                            scrollRange = appBarLayout.getTotalScrollRange();
                        }
                        if (scrollRange + verticalOffset == 0) {
                            toolbar.setTitle(movie.getTitle());
                            isVisible = true;
                        } else if (isVisible) {
                            toolbar.setTitle("");
                            isVisible = false;
                        }
                    }
                });
                tvNameMoviesDetail.setText(movie.getTitle());
                String duration = String.valueOf(movie.getRuntime());
                tvDurationMoviesDetail.setText(duration + " " + getString(R.string.minute));
                tvRateMovie.setText(String.valueOf(movie.getVote_average()));
                DateHelper dateHelper = new DateHelper();
                tvReleaseDateMoviesDetail.setText(dateHelper.getReleaseDate(movie.getRelease_date()));
                tvSynopsisMoviesDetail.setText(movie.getOverview());

                GlideApp.with(getApplicationContext()).load(movie.getPoster_path()).into(imgPosterDetail);
                GlideApp.with(getApplicationContext()).load(movie.getBackdrop_path()).into(imgBannerDetail);
            });


        }

    }

    private DetailMovieViewModel obtainViewModel(DetailMovieActivity detailMovieActivity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(detailMovieActivity.getApplication());
        return ViewModelProviders.of(detailMovieActivity, factory).get(DetailMovieViewModel.class);
    }


}
