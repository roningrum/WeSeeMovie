package com.roningrum.weseemovie.ui.tv;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.TVShow;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVListFragment extends Fragment {
    private RecyclerView rvTVshows;


    public TVListFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstances() {
        return new TVListFragment();
    }

    public static Fragment newInstance() {
        return new TVListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVshows = view.findViewById(R.id.rv_tv_show);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tv_series);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            TVShowViewModel tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel.class);
            List<TVShow> tvShows = tvShowViewModel.getAllTvShows();

            TVShowAdapter tvShowAdapter = new TVShowAdapter(getActivity());
            tvShowAdapter.setTvShows(tvShows);

            rvTVshows.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvTVshows.setHasFixedSize(true);
            rvTVshows.setAdapter(tvShowAdapter);
        }
    }
}
