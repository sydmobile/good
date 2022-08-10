package com.syd.good.feature.resultapi;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.syd.good.databinding.CommonActivitySimpleBinding;
import com.syd.good.feature.a_common.base.SimpleActivity;
import com.syd.good.feature.a_template.CommonDemoActivity;
import com.syd.good.feature.a_test.TestDemoActivity;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/22 17:24
 *     desc   : Activity Result API
 *     version: 1.0
 * </pre>
 */
public class ResultApiDemoActivity extends SimpleActivity<CommonActivitySimpleBinding> {


    @Override
    protected void initBinding() {
        mBinding = CommonActivitySimpleBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {
        mBinding.tvAnnotation.setText("Activity Result API 学习");
    }

    @Override
    public void initView() {
        mBinding.btFun1.setText("启动 Activity");
        registerResult();

    }


    private void startActivityTest() {
        int requestCode = 100;
        Intent intent = new Intent(this, CommonDemoActivity.class);
        startActivityForResult(intent, requestCode);
        finish();
    }



    /**
     * 注册 Result
     */
    private void registerResult(){
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
            if (o.getResultCode() == Activity.RESULT_OK) {
                mBinding.tv1.setText(o.getData().getStringExtra("data"));
            }else {
                Toast.makeText(this,"===="+o.getResultCode(),Toast.LENGTH_SHORT).show();
            }
        });


        mBinding.btFun1.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestDemoActivity.class);
            launcher.launch(intent);
        });

    }
}
