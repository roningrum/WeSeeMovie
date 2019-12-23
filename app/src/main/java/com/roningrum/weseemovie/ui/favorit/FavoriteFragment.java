package com.roningrum.weseemovie.ui.favorit;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.ui.favorit.tab.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar favToolbar;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new FavoriteFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        ViewPagerAdapter favViewPageAdapter = new ViewPagerAdapter(getChildFragmentManager());
        favViewPageAdapter.addFragment(new MovieFavFragment(), getString(R.string.movie));
        favViewPageAdapter.addFragment(new TvFavFragment(), getString(R.string.tv_series));

        viewPager.setAdapter(favViewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        favToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }
}
