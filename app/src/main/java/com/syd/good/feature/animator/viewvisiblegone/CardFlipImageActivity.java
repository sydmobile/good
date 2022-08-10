package com.syd.good.feature.animator.viewvisiblegone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.AnimatorActivityCardFlipImgBinding;


/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/25 14:29
 *     desc   : 动画显示或隐藏视图
 *     version: 1.0
 * </pre>
 */
public class CardFlipImageActivity extends BaseActivity1<AnimatorActivityCardFlipImgBinding> {
    private int duration;

    @Override
    protected void initBinding() {
        mBinding = AnimatorActivityCardFlipImgBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        mBinding.ivImage2.setVisibility(View.GONE);
        mBinding.bt.setOnClickListener(v -> {
            cardFlip();
        });
    }

    /**
     * 切换
     */
    private void cardFlip() {
        if (mBinding.ivImage2.getVisibility() == View.GONE) {
            mBinding.ivImage2.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(mBinding.ivImage1, "rotationY", 0, 180);
            animator.setDuration(6000);
            ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(mBinding.ivImage1, "alpha", 1.0f, 0);
            animatorAlpha.setStartDelay(3000);
            animatorAlpha.setDuration(1);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator, animatorAlpha);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mBinding.ivImage1.setVisibility(View.GONE);
                }
            });
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(mBinding.ivImage2, "rotationY", 180, 360);
            animator1.setDuration(6000);
            ObjectAnimator animatorAlpha1 = ObjectAnimator.ofFloat(mBinding.ivImage2, "alpha", 0, 1);
            animatorAlpha1.setStartDelay(3000);
            animatorAlpha1.setDuration(1);
            AnimatorSet set1 = new AnimatorSet();
            set1.playTogether(animator1, animatorAlpha1);

            set.start();
            set1.start();
        }else {
            mBinding.ivImage1.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(mBinding.ivImage1,"rotationY",180,0);
            animator.setDuration(6000);
            ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(mBinding.ivImage1,"alpha",0,1);
            animatorAlpha.setStartDelay(3000);
            animatorAlpha.setDuration(1);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator,animatorAlpha);

            ObjectAnimator animator1 = ObjectAnimator.ofFloat(mBinding.ivImage2,"rotationY",0,-180);
            animator1.setDuration(6000);
            ObjectAnimator animatorAlpha1 = ObjectAnimator.ofFloat(mBinding.ivImage2, "alpha", 1.0f, 0);
            animatorAlpha1.setStartDelay(3000);
            animatorAlpha1.setDuration(1);
            AnimatorSet set1 = new AnimatorSet();
            set1.playTogether(animator1, animatorAlpha1);
            set1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mBinding.ivImage2.setVisibility(View.GONE);
                }
            });

            set.start();
            set1.start();


        }


    }
}