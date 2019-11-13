package com.roningrum.weseemovie.ui.movie;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.Movie;
import com.roningrum.weseemovie.ui.detail.DetailMovieActivity;
import com.roningrum.weseemovie.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final Activity activity;
    private List<Movie> movies = new ArrayList<>();

    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
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
        TextView tvNameMovieItem;
        TextView tvGenreMovie;
        ImageView imgMovieItem;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameMovieItem = itemView.findViewById(R.id.tv_name_movie_item);
            tvGenreMovie = itemView.findViewById(R.id.tv_genre_movie_item);
            imgMovieItem = itemView.findViewById(R.id.img_movie_item);
        }

        void bindDataMovies(Movie movie) {
            tvNameMovieItem.setText(movie.getName());
            tvGenreMovie.setText(movie.getGenre());
            GlideApp.with(itemView.getContext()).load(movie.getPoster()).into(imgMovieItem);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_FILMS, getMovies().get(getAdapterPosition()));
                activity.startActivity(intent);
            });
        }
    }
}
