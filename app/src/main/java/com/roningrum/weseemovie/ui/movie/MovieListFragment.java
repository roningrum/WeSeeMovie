package com.roningrum.weseemovie.ui.movie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
public class MovieListFragment extends Fragment {
    @BindView(R.id.rv_movie)
    RecyclerView rvMovies;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private MovieAdapter movieAdapter;
    //    private ProgressBar progressBar;


    public MovieListFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        toolbar.setTitle(R.string.movie);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            MovieViewModel movieViewModel = obtainViewModel(getActivity());
            movieAdapter = new MovieAdapter(getActivity());
            pbLoading.setVisibility(View.VISIBLE);
            movieViewModel.setUserName("roningrum");
            movieViewModel.movies.observe(this, movies -> {
                if (movies != null) {
                    switch (movies.status) {
                        case LOADING:
                            pbLoading.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            pbLoading.setVisibility(View.GONE);
                            movieAdapter.setMovies(movies.data);
                            movieAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            pbLoading.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvMovies.setHasFixedSize(true);
            rvMovies.setAdapter(movieAdapter);
        }
    }

    private MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }
}
