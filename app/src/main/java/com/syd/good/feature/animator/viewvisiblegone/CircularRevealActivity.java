package com.syd.good.feature.animator.viewvisiblegone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;

import com.google.android.material.animation.AnimationUtils;
import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.AnimatorActivityCardFlipImgBinding;
import com.syd.good.databinding.AnimatorActivityRevealAnimationBinding;
import com.syd.good.databinding.AnimatorActivityRevealImgBinding;


/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/25 14:29
 *     desc   : 揭露动画实现 View 可见
 *     version: 1.0
 * </pre>
 */
public class CircularRevealActivity extends BaseActivity1<AnimatorActivityRevealImgBinding> {

    @Override
    protected void initBinding() {
        mBinding = AnimatorActivityRevealImgBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        mBinding.bt.setOnClickListener(v -> {
            revealShow();
        });
        mBinding.btHide.setOnClickListener(v -> {
            revealHindden();
        });
    }


    private void revealShow(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int cx = mBinding.ivImage1.getWidth()/2;
            int cy = mBinding.ivImage1.getHeight()/2;
            int r = (int) Math.hypot(cx,cy);
            Animator animator = ViewAnimationUtils.createCircularReveal(mBinding.ivImage1,cx,cy,0f,r);
            mBinding.ivImage1.setVisibility(View.VISIBLE);
            animator.setDuration(6000);
            animator.start();
        }else {
            mBinding.ivImage1.setVisibility(View.VISIBLE);
        }
    }

    private void revealHindden(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int cx = mBinding.ivImage1.getWidth()/2;
            int cy = mBinding.ivImage1.getHeight()/2;
            int r = (int) Math.hypot(cx,cy);
            Animator animator = ViewAnimationUtils.createCircularReveal(mBinding.ivImage1,cx,cy,r,0);
            mBinding.ivImage1.setVisibility(View.VISIBLE);
            animator.setDuration(6000);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mBinding.ivImage1.setVisibility(View.GONE);
                }
            });
            animator.start();
        }else {
            mBinding.ivImage1.setVisibility(View.VISIBLE);
        }

    }

}