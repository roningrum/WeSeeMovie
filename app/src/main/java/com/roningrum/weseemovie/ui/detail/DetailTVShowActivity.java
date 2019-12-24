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
import com.roningrum.weseemovie.utils.DateHelper;
import com.roningrum.weseemovie.utils.GlideApp;
import com.roningrum.weseemovie.viewmodel.ViewModelFactory;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTVShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "tv";
    private DetailTVShowViewModel detailTVShowViewModel;

    @BindView(R.id.tv_name_tv_detail)
    TextView tvNameTvShowDetail;
    @BindView(R.id.tv_release_tv_time_detail)
    TextView tvReleaseDateTV;

    @BindView(R.id.tv_number_tv_season)
    TextView tvNumberSeason;
    @BindView(R.id.tv_sinopsis_detail)
    TextView tvSynopsisTvShowDetail;

    @BindView(R.id.tv_rate_tv)
    TextView tvRateTv;

    @BindView(R.id.img_tv_poster_detail)
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
        setContentView(R.layout.activity_detail_tv);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pbLoading.setVisibility(View.VISIBLE);
        detailTVShowViewModel = obtainViewModel(this);

        showDetailTV();


    }

    private DetailTVShowViewModel obtainViewModel(DetailTVShowActivity detailTVShowActivity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(detailTVShowActivity.getApplication());
        return ViewModelProviders.of(detailTVShowActivity, factory).get(DetailTVShowViewModel.class);

    }

    @SuppressLint("SetTextI18n")
    private void showDetailTV() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int tvId = extras.getInt(EXTRA_TV);
            if (tvId != 0) {
                detailTVShowViewModel.setTvId(tvId);
            }
        }
        detailTVShowViewModel.detailTvShows.observe(this, tvShowEntityResource -> {
            pbLoading.setVisibility(View.GONE);
            switch (tvShowEntityResource.status) {
                case LOADING:
                    pbLoading.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    pbLoading.setVisibility(View.GONE);
                    if (tvShowEntityResource.data != null) {
                        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                            boolean isVisible = true;
                            int scrollRange = -1;

                            @Override
                            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                                if (scrollRange == -1) {
                                    scrollRange = appBarLayout.getTotalScrollRange();
                                }
                                if (scrollRange + verticalOffset == 0) {
                                    toolbar.setTitle(tvShowEntityResource.data.getName());
                                    isVisible = true;
                                } else if (isVisible) {
                                    toolbar.setTitle("");
                                    isVisible = false;
                                }
                            }
                        });

                        tvNameTvShowDetail.setText(tvShowEntityResource.data.getName());
                        tvNumberSeason.setText(tvShowEntityResource.data.getNumber_of_seasons() + " " + getString(R.string.season));
                        tvReleaseDateTV.setText(new DateHelper().getReleaseDate(tvShowEntityResource.data.getFirst_air_date()));
                        tvRateTv.setText(String.valueOf(tvShowEntityResource.data.getVote_average()));
                        tvSynopsisTvShowDetail.setText(tvShowEntityResource.data.getOverview());


                        GlideApp.with(getApplicationContext()).load(tvShowEntityResource.data.getPoster_path()).into(imgPosterDetail);
                        GlideApp.with(getApplicationContext()).load(tvShowEntityResource.data.getBackdrop_path()).into(imgBannerDetail);
                    }
                    break;
                case ERROR:
                    pbLoading.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    break;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_detail_menu, menu);
        this.menu = menu;
        detailTVShowViewModel.detailTvShows.observe(this, tvShowEntityResource -> {
            if (tvShowEntityResource != null) {
                switch (tvShowEntityResource.status) {
                    case LOADING:
                        pbLoading.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (tvShowEntityResource.data != null) {
                            pbLoading.setVisibility(View.GONE);
                            boolean state = tvShowEntityResource.data.isFavorite();
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
            detailTVShowViewModel.setFavoriteTVShow();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
