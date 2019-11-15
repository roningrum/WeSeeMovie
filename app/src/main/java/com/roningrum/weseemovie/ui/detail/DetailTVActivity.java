package com.roningrum.weseemovie.ui.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.roningrum.weseemovie.R;

public class DetailTVActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "tv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
    }
}
