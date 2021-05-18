package com.syd.good.feature.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.syd.good.databinding.FrgamentFragmentThreeBinding;

/**
 * <p>
 * date: 2021/3/12 16:49
 *
 * @author syd
 * @version 1.0
 */
public class FragmentC extends Fragment {
    FrgamentFragmentThreeBinding threeBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        threeBinding = FrgamentFragmentThreeBinding.inflate(inflater, container, false);
        return threeBinding.getRoot();
    }
}
