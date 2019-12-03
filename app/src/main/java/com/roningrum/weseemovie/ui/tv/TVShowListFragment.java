package com.roningrum.weseemovie.ui.tv;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.viewmodel.ViewModelFactory;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowListFragment extends Fragment {
    @BindView(R.id.rv_tv_show)
    RecyclerView rvTVshows;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private TVShowAdapter tvShowAdapter;

    public TVShowListFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new TVShowListFragment();
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
        ButterKnife.bind(this, view);
        toolbar.setTitle(R.string.tv_series);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            TVShowViewModel tvShowViewModel = obtainViewModel(getActivity());
            tvShowAdapter = new TVShowAdapter(getActivity());
            pbLoading.setVisibility(View.VISIBLE);
            tvShowViewModel.getAllTvShows().observe(this, tvShows -> {
                pbLoading.setVisibility(View.GONE);
                tvShowAdapter.setTvShows(tvShows);
                tvShowAdapter.notifyDataSetChanged();
            });

            rvTVshows.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvTVshows.setHasFixedSize(true);
            rvTVshows.setAdapter(tvShowAdapter);
        }
    }

    private TVShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TVShowViewModel.class);
    }
}
