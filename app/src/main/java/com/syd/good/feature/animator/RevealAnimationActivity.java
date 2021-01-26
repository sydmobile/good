package com.syd.good.feature.animator;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.syd.good.R;
import com.syd.good.base.BaseActivity;

/**
 * <p>
 * date: 2020/9/21 18:05
 *
 * @author syd
 * @version 1.0
 */
public class RevealAnimationActivity extends BaseActivity {

    boolean flag;
    FloatingActionButton fab;
    private View mPuppet;

    @Override
    protected int layoutId() {
        return R.layout.animator_activity_reveal_animation;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mPuppet = findViewById(R.id.view_puppet);
        flag = mPuppet.getVisibility() == View.VISIBLE;

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RevealAnimationActivity.this, "xxx", Toast.LENGTH_SHORT).show();
                launchRevealAnimation();
            }
        });
    }

    private void launchRevealAnimation() {
        Animation animation = mPuppet.getAnimation();
        if (animation != null) {
            animation.cancel();
        }

        int[] vLocation = new int[2];
        fab.getLocationInWindow(vLocation);
        int centerX = vLocation[0] + fab.getWidth() / 2;
        int centerY = vLocation[1] + fab.getHeight() / 2;

        View view = findViewById(R.id.toolbar_layout);
        int hypotenuse = (int) Math.hypot(view.getWidth(), view.getHeight());

        if (flag) {
            final Animator circularReveal = ViewAnimationUtils.createCircularReveal(mPuppet, centerX, centerY, hypotenuse, 0);
            circularReveal.setDuration(2000);
            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mPuppet.setVisibility(View.GONE);
                    circularReveal.removeListener(this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            circularReveal.start();
            flag = false;
        } else {
            final Animator circularReveal = ViewAnimationUtils.createCircularReveal(mPuppet, centerX, centerY, 0, hypotenuse);
            circularReveal.setDuration(2000);
            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    circularReveal.removeListener(this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            mPuppet.setVisibility(View.VISIBLE);
            circularReveal.start();
            flag = true;
        }
    }
}
