package com.syd.good.feature.animator.layouttransition;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.AnimatorLayouttransitionActivitiyHomeBinding;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/25 14:29
 *     desc   : LayoutTransition 使用
 *     version: 1.0
 * </pre>
 */
public class AnimatorLayoutTransitionActivity extends BaseActivity1<AnimatorLayouttransitionActivitiyHomeBinding> {

    @Override
    protected void initBinding() {
        mBinding = AnimatorLayouttransitionActivitiyHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        initLayoutTransition();
        mBinding.btnadd.setOnClickListener(v -> {
            Button button = new Button(this);
            button.setText("add......" + Math.random());
            mBinding.getRoot().addView(button, 1, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        });

        mBinding.btndel.setOnClickListener(v -> {
            if (mBinding.getRoot().getChildCount() > 1) {

                mBinding.getRoot().removeViewAt(2);
            }
        });
        mBinding.btHide.setOnClickListener(v -> {
            if (mBinding.getRoot().getChildCount() > 1) {

                mBinding.getRoot().getChildAt(2).setVisibility(View.INVISIBLE);
            }
        });

        mBinding.btShow.setOnClickListener(v -> {
            mBinding.getRoot().getChildAt(2).setVisibility(View.VISIBLE);
        });

    }

    private void initLayoutTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(null, "translationX", 0, 1000);
        objectAnimator.setDuration(1500);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(null, "scaleX", 0, 1);
        objectAnimator1.setDuration(1500);
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, objectAnimator);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, objectAnimator1);
        mBinding.getRoot().setLayoutTransition(layoutTransition);
    }
}