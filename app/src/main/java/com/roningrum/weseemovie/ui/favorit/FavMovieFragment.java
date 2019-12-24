package com.roningrum.weseemovie.ui.favorit;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.ui.favorit.adapter.FavoriteMoviePagedAdapter;
import com.roningrum.weseemovie.ui.movie.MovieViewModel;
import com.roningrum.weseemovie.viewmodel.ViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment {
    @BindView(R.id.rv_movies_favs)
    RecyclerView rvMovieFavs;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private FavoriteMoviePagedAdapter adapter;
    private MovieViewModel movieViewModel;
    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null) {
                int swipedPosition = viewHolder.getAdapterPosition();
                MovieEntity movieEntity = adapter.getItemById(swipedPosition);
                movieViewModel.setFavorite(movieEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> movieViewModel.setFavorite(movieEntity));
                snackbar.show();
            }
        }
    });

    public FavMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    private MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            movieViewModel = obtainViewModel(getActivity());

            adapter = new FavoriteMoviePagedAdapter(this.getActivity());

            movieViewModel.getFavoritedMoviePaged().observe(this, movies -> {
                if (movies != null) {
                    switch (movies.status) {
                        case LOADING:
                            pbLoading.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            pbLoading.setVisibility(View.GONE);
                            adapter.submitList(movies.data);
                            adapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            pbLoading.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            rvMovieFavs.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovieFavs.setHasFixedSize(true);
            rvMovieFavs.setAdapter(adapter);

            itemTouchHelper.attachToRecyclerView(rvMovieFavs);

        }
    }
}
