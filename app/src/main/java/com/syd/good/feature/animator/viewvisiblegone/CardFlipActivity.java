package com.syd.good.feature.animator.viewvisiblegone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/18 10:11
 * 卡片翻转 Fragment 实现
 *
 * @author syd
 * @version 1.0
 */
public class CardFlipActivity extends BaseActivity {
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.bt)
    Button bt;
    boolean showingBack = false;

    @Override
    protected int layoutId() {
        return R.layout.animator_activity_card_flip;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }

        bt.setOnClickListener(v -> flipCard());
    }

    private void flipCard() {
        if (showingBack) {
            getSupportFragmentManager().popBackStack();
            showingBack = false;
            return;
        }
        showingBack = true;
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.animator_card_flip_back_in,
                        R.animator.animator_card_flip_front_out,
                        R.animator.animator_card_flip_front_in,
                        R.animator.animator_card_flip_back_out)
                .replace(R.id.container, new CardBackFragment())
                .addToBackStack(null)
                .commit();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    public static class CardFrontFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.animator_fragment_card_front, container, false);
        }
    }

    public static class CardBackFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.animator_fragment_card_back, container, false);
        }
    }
}
