package com.syd.good.feature.animator;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Button;

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
public class AnimatorActivity extends BaseActivity {


    @BindView(R.id.bt_valueanimator)
    Button btValueanimator;
    @BindView(R.id.bt_translate)
    Button btTranslate;
    @BindView(R.id.bt_scale)
    Button btScale;
    @BindView(R.id.bt_roate)
    Button btRoate;
    @BindView(R.id.bt_alpha)
    Button btAlpha;
    @BindView(R.id.bt_roateX)
    Button btRoateX;
    @BindView(R.id.bt_roateY)
    Button btRoateY;
    @BindView(R.id.bt_target)
    Button btTarget;

    @Override
    protected int layoutId() {
        return R.layout.animator_activity_property_animator;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // ValueAnimator 示例
        btValueanimator.setOnClickListener(v -> {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(200, 400);
            valueAnimator.setDuration(3000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int currentValue = (int) animation.getAnimatedValue();
                    btTarget.getLayoutParams().width = currentValue;
                    btTarget.requestLayout();
                    btTarget.invalidate();
                }
            });
            valueAnimator.start();
        });
        // ObjectAnimator translate
        btTranslate.setOnClickListener(v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(btTarget, "translationX", 0, 300);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(btTarget, "translationY", 0, 300);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator, animator1);
            set.start();
        });

        btAlpha.setOnClickListener(v -> {
            ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.animator_alpha);
            animator.setTarget(btTarget);
            animator.start();
        });

        btRoate.setOnClickListener(v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(btTarget, "rotation", 0, 180, 90);
            animator.setDuration(3000);
            animator.start();
        });

        btScale.setOnClickListener(v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(btTarget, "scaleX", 0, 3, 5, 3, 1);
            animator.setDuration(3000);
            animator.start();
        });

        btRoateX.setOnClickListener(v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(btTarget,"rotationX",0,180);
            animator.setDuration(3000);
            animator.start();
        });
        btRoateY.setOnClickListener(v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(btTarget,"rotationY",0,180);
            animator.setDuration(3000);
            animator.start();
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
