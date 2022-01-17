package com.syd.good.feature.animator;

import android.animation.Animator;
import android.view.View;

import androidx.viewbinding.ViewBinding;

import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.AnimatorVisibleGoneActivitiyBinding;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/25 14:29
 *     desc   : 动画显示或隐藏视图
 *     version: 1.0
 * </pre>
 */
public class VisibleGoneActivity extends BaseActivity1<AnimatorVisibleGoneActivitiyBinding> {
    private int duration;

    @Override
    protected void initBinding() {
        mBinding = AnimatorVisibleGoneActivitiyBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        mBinding.content.setVisibility(View.GONE);
        duration = getResources().getInteger(android.R.integer.config_longAnimTime);
        mBinding.btLoad.setOnClickListener(v -> {
            crossfade();
        });
    }

    /**
     * 淡入淡出
     */
    private void crossfade(){
        mBinding.content.animate()
                .alphaBy(0)
                .alpha(1)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mBinding.content.setVisibility(View.VISIBLE);
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
                })
                .start();
        mBinding.content.animate()
                .alpha(0)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mBinding.loadingSpinner.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

    }
}