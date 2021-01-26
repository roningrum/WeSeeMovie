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
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.ui.favorit.adapter.FavTvShowPagedAdapter;
import com.roningrum.weseemovie.ui.tv.TVShowViewModel;
import com.roningrum.weseemovie.viewmodel.ViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavTvShowFragment extends Fragment {

    @BindView(R.id.rv_tvshows_favs)
    RecyclerView rvTvShowsFav;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private FavTvShowPagedAdapter adapter;
    private TVShowViewModel tvShowViewModel;


    public FavTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false);
    }

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
                TVShowEntity tvShowEntity = adapter.getItemById(swipedPosition);
                tvShowViewModel.setFavorite(tvShowEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> tvShowViewModel.setFavorite(tvShowEntity));
                snackbar.show();
            }
        }
    });

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            tvShowViewModel = obtainViewModel(getActivity());
            adapter = new FavTvShowPagedAdapter(this.getActivity());

            tvShowViewModel.getFavoritedTvSPaged().observe(this, tvshows -> {
                if (tvshows != null) {
                    switch (tvshows.status) {
                        case LOADING:
                            pbLoading.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            pbLoading.setVisibility(View.GONE);
                            adapter.submitList(tvshows.data);
                            adapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            pbLoading.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            rvTvShowsFav.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTvShowsFav.setHasFixedSize(true);
            rvTvShowsFav.setAdapter(adapter);

            itemTouchHelper.attachToRecyclerView(rvTvShowsFav);

        }
    }

    private TVShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TVShowViewModel.class);
    }
}
