package com.syd.good.feature.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/17 17:45
 *
 * @author syd
 * @version 1.0
 */
public class CrossfadeActivity extends BaseActivity {
    @BindView(R.id.content)
    ScrollView content;
    @BindView(R.id.loading_spinner)
    ProgressBar loadingSpinner;
    private int shortAnimationDuration;


    @Override
    protected int layoutId() {
        return R.layout.animator_activity_crossfade;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        content.setVisibility(View.GONE);
        shortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
        new Handler().postDelayed(this::crossfade, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    private void crossfade() {
        content.setAlpha(0f);
        content.setVisibility(View.VISIBLE);
        content.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);
        loadingSpinner.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingSpinner.setVisibility(View.GONE);
                    }
                });
    }
}
