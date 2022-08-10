package com.syd.good.feature.animator.moveview;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.PathInterpolator;

import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.AnimatorActivityViewMoveBinding;


/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/25 14:29
 *     desc   : 视图移动动画
 *     version: 1.0
 * </pre>
 */
public class ViewMoveAnimateActivity extends BaseActivity1<AnimatorActivityViewMoveBinding> {

    @Override
    protected void initBinding() {
        mBinding = AnimatorActivityViewMoveBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        mBinding.btMove.setOnClickListener(v -> {
            moveX();
        });
        mBinding.btMoveCircle.setOnClickListener(v -> {
            moveCircle();
        });

        mBinding.btMoveCircleIn.setOnClickListener(v -> {
            moveInCircle();
        });
    }


    private void moveX(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBinding.ivImage1,"translationX",100);
        animator.setDuration(2000);
        animator.start();
    }

    private void moveCircle(){

        Path path = new Path();
//        path.moveTo(0,0);
        path.arcTo(-1,0,1,2,270,90,false);
//        path.lineTo(1,1);
//        path.arcTo(0f, 0f, 1f, 1f, 270, -225, false);
//        path.lineTo(1,1);
//        path.lineTo(1,0);
//        path.lineTo(0.5f,1.2f);
//        path.lineTo(1,1);
        PathInterpolator interpolator = new PathInterpolator(path);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBinding.ivCircle,"translationY",20,1000);
        animator.setInterpolator(interpolator);
        animator.setDuration(5000);
        animator.start();







    }

    private void moveInCircle(){
        Path path = new Path();
        path.moveTo(200,200);
        path.lineTo(500,0);
        path.lineTo(0,1000);
        path.lineTo(0,0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBinding.ivCircleIn, View.X,View.Y,path);
        animator.setDuration(5000);
        animator.start();
//
    }
}