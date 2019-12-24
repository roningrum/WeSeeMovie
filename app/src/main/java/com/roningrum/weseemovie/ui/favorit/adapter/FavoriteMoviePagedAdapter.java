package com.roningrum.weseemovie.ui.favorit.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.data.source.remote.response.Constant;
import com.roningrum.weseemovie.ui.detail.DetailMovieActivity;
import com.roningrum.weseemovie.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteMoviePagedAdapter extends PagedListAdapter<MovieEntity, FavoriteMoviePagedAdapter.FavMovieViewHolder> {

    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private final Activity activity;


    public FavoriteMoviePagedAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    public MovieEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    @NonNull
    @Override
    public FavoriteMoviePagedAdapter.FavMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavMovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMoviePagedAdapter.FavMovieViewHolder holder, int position) {
        MovieEntity movieEntity = getItem(position);
        if (movieEntity != null) {
            holder.bindDataMovies(movieEntity);
        }

    }

    class FavMovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name_movie_item)
        TextView tvNameMovieItem;
        @BindView(R.id.tv_overview_movie_item)
        TextView tvSummaryMovie;
        @BindView(R.id.img_movie_item)
        ImageView imgMovieItem;

        FavMovieViewHolder(@NonNull View itemView) {
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
