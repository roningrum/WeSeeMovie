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
            detailTVShowViewModel.getTvShowDetail(tvId).observe(this, tvShow -> {
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
                            toolbar.setTitle(tvShow.getName());
                            isVisible = true;
                        } else if (isVisible) {
                            toolbar.setTitle("");
                            isVisible = false;
                        }
                    }
                });

                tvNameTvShowDetail.setText(tvShow.getName());
                tvNumberSeason.setText(tvShow.getNumber_of_seasons() + " " + getString(R.string.season));
                tvReleaseDateTV.setText(new DateHelper().getReleaseDate(tvShow.getFirst_air_date()));
                tvRateTv.setText(String.valueOf(tvShow.getVote_average()));
                tvSynopsisTvShowDetail.setText(tvShow.getOverview());


                GlideApp.with(getApplicationContext()).load(tvShow.getPoster_path()).into(imgPosterDetail);
                GlideApp.with(getApplicationContext()).load(tvShow.getBackdrop_path()).into(imgBannerDetail);
            });
        }


    }
}
