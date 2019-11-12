package com.roningrum.weseemovie.ui.tv;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.roningrum.weseemovie.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVListFragment extends Fragment {


    public TVListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvlist, container, false);
    }

}
