package com.roningrum.weseemovie.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.ui.movie.MovieListFragment;
import com.roningrum.weseemovie.ui.tv.TVListFragment;

public class HomeMenuActivity extends AppCompatActivity {
    private static final String SELECTED_MENU = "selected_menu";
    private BottomNavigationView navView;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = menuItem -> {
        Fragment fragment = null;
        if (menuItem.getItemId() == R.id.nav_movie_menu) {
            fragment = MovieListFragment.newInstance();
        } else if (menuItem.getItemId() == R.id.nav_tv_menu) {
            fragment = TVListFragment.newInstance();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (savedInstanceState != null) {
            savedInstanceState.getInt(SELECTED_MENU);
        } else {
            navView.setSelectedItemId(R.id.nav_movie_menu);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_MENU, navView.getSelectedItemId());
    }

}
