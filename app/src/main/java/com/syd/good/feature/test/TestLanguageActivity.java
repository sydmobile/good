package com.syd.good.feature.test;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.syd.good.R;
import com.syd.good.databinding.ActivityTestLanguageBinding;

public class TestLanguageActivity extends AppCompatActivity {
    ActivityTestLanguageBinding mActivityTestLanguageBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTestLanguageBinding = ActivityTestLanguageBinding.inflate(getLayoutInflater());
        setContentView(mActivityTestLanguageBinding.getRoot());

        mActivityTestLanguageBinding.btC.setOnClickListener(v -> {

        });

    }
}