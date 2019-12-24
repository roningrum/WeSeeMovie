package com.roningrum.weseemovie.ui.favorit.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.data.source.remote.response.Constant;
import com.roningrum.weseemovie.ui.detail.DetailTVShowActivity;
import com.roningrum.weseemovie.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavTvShowPagedAdapter extends PagedListAdapter<TVShowEntity, FavTvShowPagedAdapter.FavTvShowViewHolder> {

    private static DiffUtil.ItemCallback<TVShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TVShowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TVShowEntity oldItem, @NonNull TVShowEntity newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TVShowEntity oldItem, @NonNull TVShowEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private final Activity activity;

    public FavTvShowPagedAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    public TVShowEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    @NonNull
    @Override
    public FavTvShowPagedAdapter.FavTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavTvShowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_tv_shows, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavTvShowPagedAdapter.FavTvShowViewHolder holder, int position) {
        TVShowEntity tvShowEntity = getItem(position);
        if (tvShowEntity != null) {
            holder.bindDataTVShows(tvShowEntity);
        }
    }

    class FavTvShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_tvshows_item)
        TextView tvNameTVShows;
        @BindView(R.id.tv_overview_tv_item)
        TextView tvOverviewTV;
        @BindView(R.id.img_tv_shows_item)
        ImageView imgTVShow;

        FavTvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindDataTVShows(TVShowEntity tvShow) {
            tvNameTVShows.setText(tvShow.getName());
            tvOverviewTV.setText(tvShow.getOverview());
//            tvGenreTVShows.setText(tvShow.getGenre());
            GlideApp.with(itemView.getContext()).load(Constant.IMAGE_URL + tvShow.getPoster_path()).into(imgTVShow);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailTVShowActivity.class);
                intent.putExtra(DetailTVShowActivity.EXTRA_TV, tvShow.getId());
                activity.startActivity(intent);
            });
        }

    }
}
