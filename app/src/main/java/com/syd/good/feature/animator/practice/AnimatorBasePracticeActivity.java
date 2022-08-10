package com.syd.good.feature.animator.practice;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;

import com.syd.good.R;
import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.AnimatorPracticeActivityBaseBinding;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     desc   : 属性动画基础练习 2022/2/9 10:35
 *     version: 1.0
 * </pre>
 */
public class AnimatorBasePracticeActivity extends BaseActivity1<AnimatorPracticeActivityBaseBinding> {

    @Override
    protected void initBinding() {
        mBinding = AnimatorPracticeActivityBaseBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        // 使用资源文件来完成属性动画
        mBinding.btUseResource.setOnClickListener(v -> {
            useResourceToAnimator();

        });
    }

    /**
     * 使用资源文件来完成属性动画
     */
    private void useResourceToAnimator() {
        AnimatorSet animatorSet =
                (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_practice_set);
        animatorSet.setTarget(mBinding.btTarget);
        animatorSet.start();
    }
}