package com.roningrum.weseemovie.di;

import com.roningrum.weseemovie.data.remote.RemoteRepository;
import com.roningrum.weseemovie.data.remote.api.ApiHelper;
import com.roningrum.weseemovie.data.remote.api.ApiService;
import com.roningrum.weseemovie.data.source.MovieRepository;

public class Injection {
    public static MovieRepository provideRepository() {
        RemoteRepository remoteRepository = RemoteRepository.getINSTANCE(ApiHelper.getRetrofit().create(ApiService.class));
        return MovieRepository.getINSTANCE(remoteRepository);
    }
}
