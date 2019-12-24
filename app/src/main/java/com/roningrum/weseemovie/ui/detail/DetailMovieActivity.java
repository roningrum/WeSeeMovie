package com.roningrum.weseemovie.ui.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.AppBarLayout;
import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.source.remote.response.Constant;
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

    private Menu menu;

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
            if (movieId != 0) {
                detailMovieViewModel.setMovieId(movieId);
            }
        }
        detailMovieViewModel.detailMovies.observe(this, movieEntityResource -> {
            switch (movieEntityResource.status) {
                case LOADING:
                    pbLoading.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    if (movieEntityResource.data != null) {
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
                                    toolbar.setTitle(movieEntityResource.data.getTitle());
                                    isVisible = true;
                                } else if (isVisible) {
                                    toolbar.setTitle("");
                                    isVisible = false;
                                }
                            }
                        });
                        tvNameMoviesDetail.setText(movieEntityResource.data.getTitle());
                        String duration = String.valueOf(movieEntityResource.data.getRuntime());
                        tvDurationMoviesDetail.setText(duration + " " + getString(R.string.minute));
                        tvRateMovie.setText(String.valueOf(movieEntityResource.data.getVote_average()));
                        DateHelper dateHelper = new DateHelper();
                        tvReleaseDateMoviesDetail.setText(dateHelper.getReleaseDate(movieEntityResource.data.getRelease_date()));
                        tvSynopsisMoviesDetail.setText(movieEntityResource.data.getOverview());

                        GlideApp.with(getApplicationContext()).load(Constant.IMAGE_URL + movieEntityResource.data.getPoster_path()).into(imgPosterDetail);
                        GlideApp.with(getApplicationContext()).load(Constant.IMAGE_URL + movieEntityResource.data.getBackdrop_path()).into(imgBannerDetail);

                    }
                    break;
                case ERROR:
                    pbLoading.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_detail_menu, menu);
        this.menu = menu;
        detailMovieViewModel.detailMovies.observe(this, movieEntityResource -> {
            if (movieEntityResource != null) {
                switch (movieEntityResource.status) {
                    case LOADING:
                        pbLoading.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (movieEntityResource.data != null) {
                            pbLoading.setVisibility(View.GONE);
                            boolean state = movieEntityResource.data.isFavorite();
                            setFavoriteState(state);
                        }
                        break;
                    case ERROR:
                        pbLoading.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return true;
    }

    private void setFavoriteState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_add_fav);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorited));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border));
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_fav) {
            detailMovieViewModel.setFavoriteMovie();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private DetailMovieViewModel obtainViewModel(DetailMovieActivity detailMovieActivity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(detailMovieActivity.getApplication());
        return ViewModelProviders.of(detailMovieActivity, factory).get(DetailMovieViewModel.class);
    }


}
