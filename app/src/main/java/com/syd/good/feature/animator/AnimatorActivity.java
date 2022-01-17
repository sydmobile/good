package com.syd.good.feature.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/3 8:59
 * 属性动画学习使用
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
    @BindView(R.id.bt_set)
    Button btSet;
    @BindView(R.id.bt_pro)
    Button btPro;
    @BindView(R.id.bt_key_frame)
    Button btKeyFrame;
    @BindView(R.id.bt_vector_animator)
    Button btVectorAnimator;

    @BindView(R.id.iv_vector)
    ImageView ivVector;
    @BindView(R.id.iv_animated_state_list)
    ImageView ivAnimatedStateList;
    @BindView(R.id.iv_animated_vector)
    ImageView ivAnimatedVector;

    @BindView(R.id.iv_appt)
    ImageView ivAppt;

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
                    // 关键：需要触发绘制
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
            ObjectAnimator animator = ObjectAnimator.ofFloat(btTarget, "rotationX", 0, 180);
            animator.setDuration(3000);
            animator.start();
        });
        btRoateY.setOnClickListener(v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(btTarget, "rotationY", 0, 180);
            animator.setDuration(3000);
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        });

        // 引用资源文件进行动画
        btSet.setOnClickListener(v -> {
            Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_study_test);
            animator.setTarget(btTarget);
            animator.start();
        });

        btPro.setOnClickListener(v -> {
            Log.e(TAG,"left:"+btTarget.getLeft()+"right:"+btTarget.getRight());
            PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofInt("left", 500, 400);
            PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofInt("top", 500, 400);
            PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofInt("right", 900, 1000);
            PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofInt("bottom", 1400, 1500);
//            PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofInt("right", 0, 1);
            ObjectAnimator an = ObjectAnimator.ofPropertyValuesHolder(btTarget, valuesHolder, valuesHolder1, valuesHolder2, valuesHolder3);
            an.setDuration(3000);
            an.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Log.e(TAG,animation.getAnimatedValue()+"==");
                }
            });
            an.start();

        });

        // 关键帧
        btKeyFrame.setOnClickListener(v -> {
            Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
            Keyframe kf1 = Keyframe.ofFloat(.5f, 360f);
            Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
            PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
            ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(btTarget, pvhRotation);
            rotationAnim.setDuration(5000);
            rotationAnim.start();

        });

        // 矢量动画
        btVectorAnimator.setOnClickListener(v -> {
           AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ivVector.getBackground();
           drawable.start();
        });

        ivAnimatedStateList.setOnClickListener(v -> {

        });

        ivAnimatedVector.setOnClickListener(v -> {
           AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ivAnimatedVector.getBackground();
           drawable.start();
        });

        ivAppt.setOnClickListener(v -> {
            AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ivAppt.getBackground();
            drawable.start();

        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
