package com.roningrum.weseemovie.ui.movie;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.data.source.remote.response.Constant;
import com.roningrum.weseemovie.ui.detail.DetailMovieActivity;
import com.roningrum.weseemovie.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final Activity activity;
    private final List<MovieEntity> movies = new ArrayList<MovieEntity>();

    MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<MovieEntity> getMovies() {
        return movies;
    }

    void setMovies(List<MovieEntity> movies) {
        if (movies == null) return;
        this.movies.clear();
        this.movies.addAll(movies);
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.bindDataMovies(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_movie_item)
        TextView tvNameMovieItem;
        @BindView(R.id.tv_overview_movie_item)
        TextView tvSummaryMovie;
        @BindView(R.id.img_movie_item)
        ImageView imgMovieItem;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindDataMovies(MovieEntity movie) {
            tvNameMovieItem.setText(movie.getTitle());
            tvSummaryMovie.setText(movie.getOverview());
            GlideApp.with(itemView.getContext()).load(Constant.IMAGE_URL + movie.getPoster_path()).into(imgMovieItem);
            Log.d("Check ", "Poster " + movie.getPoster_path());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_FILMS, movie.getId());
                activity.startActivity(intent);
            });
        }
    }
}
