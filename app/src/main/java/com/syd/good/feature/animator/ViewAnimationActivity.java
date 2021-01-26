package com.syd.good.feature.animator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/18 11:41
 * 视图动画--补间动画
 *
 * @author syd
 * @version 1.0
 */
public class ViewAnimationActivity extends BaseActivity {
    @BindView(R.id.bt_translate)
    Button btTranslate;
    @BindView(R.id.bt_scale)
    Button btScale;
    @BindView(R.id.bt_roate)
    Button btRoate;
    @BindView(R.id.bt_alpha)
    Button btAlpha;
    @BindView(R.id.bt_target)
    Button btTarget;
    @BindView(R.id.bt_start_activity)
    Button btStartActivity;
    @BindView(R.id.bt_left)
    Button btLeft;

    @Override
    protected int layoutId() {
        return R.layout.animator_activity_view_animation;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 平移动画
        btTranslate.setOnClickListener(v -> {
//            Animation animation = AnimationUtils.loadAnimation(this, R.anim.animator_translate_simple);
//            btTarget.startAnimation(animation);

            Animation translateAnimation = new TranslateAnimation(0, 300, 0, 300);
            translateAnimation.setDuration(3000);
            btTarget.startAnimation(translateAnimation);

        });

        // 缩放动画
        btScale.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.animator_scale_simple);

            btTarget.startAnimation(animation);
        });

        // 旋转动画
        btRoate.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.animator_roate_simple);
            btTarget.startAnimation(animation);
        });
        // 透明动画
        btAlpha.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.animator_alpha_simple);
            btTarget.startAnimation(animation);
        });

        // 系统预设启动动画
        btStartActivity.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewAnimationLauncherActivity.class);
            startActivity(intent);
            // 系统预设
//            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            // 自己定义
            overridePendingTransition(R.anim.animator_fade_in, R.anim.animator_fade_out);
        });

        // 左右切换
        btLeft.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewAnimationRightToLeftActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.animator_in_from_right,R.anim.animator_out_to_left);
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

    }
}
