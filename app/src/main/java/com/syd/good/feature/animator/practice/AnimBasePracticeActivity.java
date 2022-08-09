package com.syd.good.feature.animator.practice;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.syd.good.R;
import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.AnimatorPracticeActivityAnimBinding;
import com.syd.good.databinding.AnimatorPracticeActivityBaseBinding;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     desc   : 视图动画练习 2022/2/9 10:35
 *     version: 1.0
 * </pre>
 */
public class AnimBasePracticeActivity extends BaseActivity1<AnimatorPracticeActivityAnimBinding> {

    @Override
    protected void initBinding() {
        mBinding = AnimatorPracticeActivityAnimBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        // 使用资源文件来完成 补间动画
        mBinding.btUseResource.setOnClickListener(v -> {
            useResourceToAnimator();

        });

        // 完成逐帧动画
        mBinding.btStartFrame.setOnClickListener(v -> {
            startFrameAnim();
        });
    }

    /**
     * 使用资源文件来完成视图动画
     */
    private void useResourceToAnimator() {
      Animation animation  = AnimationUtils.loadAnimation(this,R.anim.animator_practice_anim_set);
      mBinding.btTarget.startAnimation(animation);
    }

    private void startFrameAnim(){
       AnimationDrawable drawable = (AnimationDrawable) mBinding.ivFrame.getBackground();
       drawable.start();

    }
}