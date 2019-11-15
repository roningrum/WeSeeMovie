package com.roningrum.weseemovie.ui.tv;

import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.TVShow;
import com.roningrum.weseemovie.utils.TVShowDataDummy;

import java.util.List;

public class TVShowViewModel extends ViewModel {
    public List<TVShow> getAllTvShows() {
        return TVShowDataDummy.generateDummyTvs();
    }
}
