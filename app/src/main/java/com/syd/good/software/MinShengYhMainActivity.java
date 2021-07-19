package com.syd.good.software;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.syd.good.R;
import com.syd.good.databinding.ActivityMinShengYhMainBinding;

public class MinShengYhMainActivity extends AppCompatActivity {
    ActivityMinShengYhMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMinShengYhMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }
}