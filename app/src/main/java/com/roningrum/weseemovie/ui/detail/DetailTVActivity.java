package com.roningrum.weseemovie.ui.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.roningrum.weseemovie.R;

public class DetailTVActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "tv";
    private DetailTVShowViewModel detailTVShowViewModel;

    private TextView tvNameTvShowDetail;
    private TextView tvGenreTvShowDetail;
    private TextView tvDurationTvShowDetail;
    private TextView tvReleaseEpisodeDetail;
    private TextView tvDirectorTvShowDetail;
    private TextView tvSynopsisTvShowDetail;
    private ImageView imgPosterDetail;
    private ImageView imgBannerDetail;

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
    }
}
