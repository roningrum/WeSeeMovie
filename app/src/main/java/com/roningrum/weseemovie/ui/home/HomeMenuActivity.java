package com.roningrum.weseemovie.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.ui.movie.MovieListFragment;
import com.roningrum.weseemovie.ui.tv.TVShowListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeMenuActivity extends AppCompatActivity {
    //    private static final String SELECTED_MENU = "selected_menu";
    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = menuItem -> {
        Fragment fragment = null;
        if (menuItem.getItemId() == R.id.nav_movie_menu) {
            fragment = MovieListFragment.newInstance();
        } else if (menuItem.getItemId() == R.id.nav_tv_menu) {
            fragment = TVShowListFragment.newInstance();
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit();
        }
        return true;
    };
    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        ButterKnife.bind(this);
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.nav_movie_menu);
        }
    }
}
