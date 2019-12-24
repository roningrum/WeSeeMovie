package com.roningrum.weseemovie.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.vo.Resource;

public class DetailTVShowViewModel extends ViewModel {
    private MutableLiveData<Integer> tvId = new MutableLiveData<>();
    private MovieRepository movieRepository;

    public DetailTVShowViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<Resource<TVShowEntity>> detailTvShows = Transformations.switchMap(tvId,
            tvId -> movieRepository.getTvShowDetails(tvId));

    public int getTvId() {
        if (tvId.getValue() == null) return 0;
        return tvId.getValue();
    }

    public void setTvId(int tvId) {
        this.tvId.setValue(tvId);
    }

    public void setFavoriteTVShow() {
        if (detailTvShows.getValue() != null) {
            TVShowEntity tvShowEntity = detailTvShows.getValue().data;

            if (tvShowEntity != null) {
                final boolean newState = !tvShowEntity.isFavorite();
                movieRepository.setTvShowFavBookMark(tvShowEntity, newState);
            }
        }
    }
}
