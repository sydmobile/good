package com.syd.good.feature.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.syd.good.databinding.FrgamentFragmentOneBinding;
import com.syd.good.feature.fragment.fragment.FragmentOne_ViewBinding;

/**
 * <p>
 * date: 2021/3/12 16:49
 *
 * @author syd
 * @version 1.0
 */
public class FragmentA extends Fragment {
    FrgamentFragmentOneBinding oneBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        oneBinding = FrgamentFragmentOneBinding.inflate(inflater,container,false);
        return oneBinding.getRoot();
    }
}
