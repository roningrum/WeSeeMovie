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
import com.roningrum.weseemovie.data.TVShow;
import com.roningrum.weseemovie.utils.GlideApp;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTVShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "tv";
    private DetailTVShowViewModel detailTVShowViewModel;

    @BindView(R.id.tv_name_tv_detail)
    TextView tvNameTvShowDetail;
    @BindView(R.id.tv_genre_tv_detail)
    TextView tvGenreTvShowDetail;
    @BindView(R.id.tv_duration_tv_item)
    TextView tvDurationTvShowDetail;
    @BindView(R.id.tv_director_detail)
    TextView tvDirectorTvShowDetail;
    @BindView(R.id.tv_sinopsis_detail)
    TextView tvSynopsisTvShowDetail;

    @BindView(R.id.img_tv_poster_detail)
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
        setContentView(R.layout.activity_detail_tv);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailTVShowViewModel = ViewModelProviders.of(this).get(DetailTVShowViewModel.class);
        showDetailTV();


    }

    private void showDetailTV() {
        TVShow tvShow = getIntent().getParcelableExtra(EXTRA_TV);
        if (tvShow != null) {
            detailTVShowViewModel.setTvShow(tvShow);
            TVShow tvShowData = detailTVShowViewModel.getTvShow();

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
                        toolbar.setTitle(tvShowData.getName());
                        isVisible = true;
                    } else if (isVisible) {
                        toolbar.setTitle("");
                        isVisible = false;
                    }
                }
            });

            tvNameTvShowDetail.setText(tvShowData.getName());
            tvDirectorTvShowDetail.setText(tvShowData.getCreator());
            tvGenreTvShowDetail.setText(tvShowData.getGenre());
            tvDurationTvShowDetail.setText(tvShowData.getDuration());
            tvSynopsisTvShowDetail.setText(tvShowData.getSynopsis());


            GlideApp.with(getApplicationContext()).load(tvShowData.getPoster()).into(imgPosterDetail);
            GlideApp.with(getApplicationContext()).load(tvShowData.getPhotoBanner()).into(imgBannerDetail);
        }
    }
}
