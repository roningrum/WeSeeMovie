package com.roningrum.weseemovie.ui.detail;

import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.TVShow;

public class DetailTVShowViewModel extends ViewModel {
    private TVShow tvShow;

    public TVShow getTvShow() {
        return this.tvShow;
    }

    public void setTvShow(TVShow tvShow) {
        this.tvShow = tvShow;
    }
}
