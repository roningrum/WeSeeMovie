package com.roningrum.weseemovie.ui.tv;

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
import com.roningrum.weseemovie.data.TVShow;
import com.roningrum.weseemovie.ui.detail.DetailTVActivity;
import com.roningrum.weseemovie.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowHolder> {
    private final Activity activity;
    private List<TVShow> tvShows = new ArrayList<>();

    public TVShowAdapter(Activity activity) {
        this.activity = activity;
    }

    public List<TVShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TVShow> tvShows) {
        if (tvShows == null) return;
        this.tvShows.clear();
        this.tvShows.addAll(tvShows);
    }

    @NonNull
    @Override
    public TVShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TVShowHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_tv_shows, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowHolder holder, int position) {
        holder.bindDataTVShows(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class TVShowHolder extends RecyclerView.ViewHolder {
        TextView tvNameTVShows;
        TextView tvGenreTVShows;
        ImageView imgTVShow;

        public TVShowHolder(@NonNull View itemView) {
            super(itemView);
            tvNameTVShows = itemView.findViewById(R.id.tv_name_tvshows_item);
            tvGenreTVShows = itemView.findViewById(R.id.tv_genre_tvshows_item);
            imgTVShow = itemView.findViewById(R.id.img_tv_shows_item);
        }

        void bindDataTVShows(TVShow tvShow) {
            tvNameTVShows.setText(tvShow.getName());
            tvGenreTVShows.setText(tvShow.getGenre());
            GlideApp.with(itemView.getContext()).load(tvShow.getPoster()).into(imgTVShow);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailTVActivity.class);
                intent.putExtra(DetailTVActivity.EXTRA_TV, getTvShows().get(getAdapterPosition()));
                activity.startActivity(intent);
            });
        }
    }
}
