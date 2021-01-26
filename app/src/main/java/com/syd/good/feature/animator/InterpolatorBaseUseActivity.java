package com.syd.good.feature.animator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/3 8:59
 * 普通通用的 Activity
 *
 * @author syd
 * @version 1.0
 */
public class InterpolatorBaseUseActivity extends BaseActivity {


    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.tv_target_1)
    TextView tvTarget1;
    @BindView(R.id.tv_target_2)
    TextView tvTarget2;
    @BindView(R.id.tv_target_3)
    TextView tvTarget3;
    @BindView(R.id.tv_target_4)
    TextView tvTarget4;
    @BindView(R.id.tv_target_5)
    TextView tvTarget5;
    @BindView(R.id.tv_target_6)
    TextView tvTarget6;
    @BindView(R.id.tv_target_7)
    TextView tvTarget7;
    @BindView(R.id.tv_target_8)
    TextView tvTarget8;
    @BindView(R.id.tv_target_9)
    TextView tvTarget9;
    @BindView(R.id.tv_target_0)
    TextView tvTarget0;

    @Override
    protected int layoutId() {
        return R.layout.animator_activity_interpolator;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        btStart.setOnClickListener(v -> {

            Animator animator0 = ObjectAnimator.ofFloat(tvTarget0, "translationX", 0, 600, 0);
            animator0.setDuration(5000);

            Animator animator1 = ObjectAnimator.ofFloat(tvTarget1, "translationX", 0, 600, 0);
            animator1.setInterpolator(new AccelerateDecelerateInterpolator());
            animator1.setDuration(5000);

            Animator animator2 = ObjectAnimator.ofFloat(tvTarget2, "translationX", 0, 600, 0);
            animator2.setInterpolator(new AccelerateInterpolator());
            animator2.setDuration(5000);

            Animator animator3 = ObjectAnimator.ofFloat(tvTarget3, "translationX", 0, 600, 0);
            animator3.setInterpolator(new AnticipateInterpolator());
            animator3.setDuration(5000);

            Animator animator4 = ObjectAnimator.ofFloat(tvTarget4, "translationX", 0, 600, 0);
            animator4.setInterpolator(new AnticipateOvershootInterpolator());
            animator4.setDuration(5000);

            Animator animator5 = ObjectAnimator.ofFloat(tvTarget5, "translationX", 0, 600, 0);
            animator5.setInterpolator(new BounceInterpolator());
            animator5.setDuration(5000);

            Animator animator6 = ObjectAnimator.ofFloat(tvTarget6, "translationX", 0, 600, 0);
            animator6.setInterpolator(new CycleInterpolator(4));
            animator6.setDuration(5000);

            Animator animator7 = ObjectAnimator.ofFloat(tvTarget7, "translationX", 0, 600, 0);
            animator7.setInterpolator(new DecelerateInterpolator());
            animator7.setDuration(5000);

            Animator animator8 = ObjectAnimator.ofFloat(tvTarget8, "translationX", 0, 600, 0);
            animator8.setInterpolator(new LinearInterpolator());
            animator8.setDuration(5000);

            Animator animator9 = ObjectAnimator.ofFloat(tvTarget9, "translationX", 0, 600, 0);
            animator9.setInterpolator(new OvershootInterpolator());
            animator9.setDuration(5000);

            animator0.start();
            animator1.start();
            animator2.start();
            animator3.start();
            animator4.start();
            animator5.start();
            animator6.start();
            animator7.start();
            animator8.start();
            animator9.start();

        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }
}
