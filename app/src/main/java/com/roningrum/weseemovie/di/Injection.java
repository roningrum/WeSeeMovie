package com.roningrum.weseemovie.di;

import android.app.Application;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.data.source.locale.LocalRepository;
import com.roningrum.weseemovie.data.source.locale.room.WeSeeMovieDb;
import com.roningrum.weseemovie.data.source.remote.RemoteRepository;
import com.roningrum.weseemovie.data.source.remote.api.ApiHelper;
import com.roningrum.weseemovie.data.source.remote.api.ApiService;
import com.roningrum.weseemovie.utils.AppExecutors;

public class Injection {
    public static MovieRepository provideRepository(Application application) {
        WeSeeMovieDb database = WeSeeMovieDb.getInstance(application);
        LocalRepository localRepository = LocalRepository.getINSTANCE(database.weSeeMovieDao());

        RemoteRepository remoteRepository = RemoteRepository.getINSTANCE(ApiHelper.getRetrofit().create(ApiService.class));
        AppExecutors appExecutors = new AppExecutors();
        return MovieRepository.getINSTANCE(localRepository, remoteRepository, appExecutors);
    }
}
