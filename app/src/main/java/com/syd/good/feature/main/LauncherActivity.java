package com.syd.good.feature.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;

/**
 * <p>
 * date: 2020/9/2 10:45
 *
 * @author syd
 * @version 1.0
 */
public class LauncherActivity extends BaseActivity {

    @Override
    protected int layoutId() {
        return R.layout.launcher_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_FULLSCREEN |
//                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
//                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//        );
        startAnimation();
    }

    private void startAnimation() {
        ImageView iv = findViewById(R.id.iv);
        Animation scale = AnimationUtils.loadAnimation(this, R.anim.launcher_anim_icon);
        iv.startAnimation(scale);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
