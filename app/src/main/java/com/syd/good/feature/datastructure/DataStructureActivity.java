package com.syd.good.feature.datastructure;

import android.util.Log;

import com.syd.good.databinding.CommonActivitySimpleBinding;
import com.syd.good.feature.a_common.base.SimpleActivity;

import org.jetbrains.annotations.TestOnly;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/12/3 15:07
 *     desc   : 数据结构 队列
 *     version: 1.0
 * </pre>
 */

public class DataStructureActivity extends SimpleActivity<CommonActivitySimpleBinding> {
    private static final String TAG = "DataStructureActivity";

    @Override
    protected void initBinding() {
        mBinding = CommonActivitySimpleBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {
        mBinding.tvAnnotation.setText("数据结构");
    }

    @Override
    public void initView() {
        mBinding.btFun1.setText("队列--链表实现");
        mBinding.btFun1.setOnClickListener(v -> {
            testQueueByLinked();
        });

    }

    /**
     * 测试 队列（基于链表实现）
     */
    private void testQueueByLinked(){
        QueueByLinked<String> queueByLinked = new QueueByLinked<>();

        queueByLinked.offer("入队1");
        queueByLinked.offer("入队2");
        queueByLinked.offer("入队3");
        queueByLinked.offer("入队4");
        queueByLinked.offer("入队5");

        Log.e(TAG,queueByLinked.poll()+"===");
        Log.e(TAG,queueByLinked.poll()+"===");
        Log.e(TAG,queueByLinked.poll()+"===");
        Log.e(TAG,queueByLinked.poll()+"===");
        Log.e(TAG,queueByLinked.poll()+"===");
        Log.e(TAG,queueByLinked.poll()+"===");
    }

}
