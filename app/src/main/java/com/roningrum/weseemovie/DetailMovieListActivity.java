package com.roningrum.weseemovie;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.roningrum.weseemovie.data.Movie;
import com.roningrum.weseemovie.utils.GlideApp;

public class DetailMovieListActivity extends AppCompatActivity {

    public static final String EXTRA_FILMS = "movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_list);
        TextView tvNameMoviesDetail = findViewById(R.id.tv_name_detail);
        ImageView imgPosterDetail = findViewById(R.id.img_poster_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_FILMS);
        if (movie != null) {
            tvNameMoviesDetail.setText(movie.getName());
            GlideApp.with(DetailMovieListActivity.this).load(movie.getPoster()).into(imgPosterDetail);
        }

    }
}
