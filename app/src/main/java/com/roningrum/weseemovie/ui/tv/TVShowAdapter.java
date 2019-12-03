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
import com.roningrum.weseemovie.data.locale.entity.TVShow;
import com.roningrum.weseemovie.ui.detail.DetailTVShowActivity;
import com.roningrum.weseemovie.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowHolder> {
    private final Activity activity;
    private final List<TVShow> tvShows = new ArrayList<>();

    TVShowAdapter(Activity activity) {
        this.activity = activity;
    }

    void setTvShows(List<TVShow> tvShows) {
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
        @BindView(R.id.tv_name_tvshows_item)
        TextView tvNameTVShows;
        @BindView(R.id.tv_overview_tv_item)
        TextView tvOverviewTV;
        @BindView(R.id.img_tv_shows_item)
        ImageView imgTVShow;

        TVShowHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindDataTVShows(TVShow tvShow) {
            tvNameTVShows.setText(tvShow.getName());
            tvOverviewTV.setText(tvShow.getOverview());
//            tvGenreTVShows.setText(tvShow.getGenre());
            GlideApp.with(itemView.getContext()).load(tvShow.getPoster_path()).into(imgTVShow);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailTVShowActivity.class);
                intent.putExtra(DetailTVShowActivity.EXTRA_TV, tvShow.getId());
                activity.startActivity(intent);
            });
        }
    }
}
