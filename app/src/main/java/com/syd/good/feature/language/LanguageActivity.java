package com.syd.good.feature.language;

import android.os.RemoteException;

import com.syd.good.R;
import com.syd.good.databinding.CommonActivitySimpleBinding;
import com.syd.good.databinding.LanguageActivitySimpleBinding;
import com.syd.good.feature.a_common.base.SimpleActivity;
import com.syd.good.utils.LanguageUtil;

import java.util.Locale;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/22 17:24
 *     desc   : language 测试
 *     version: 1.0
 * </pre>
 */
public class LanguageActivity extends SimpleActivity<LanguageActivitySimpleBinding> {


    @Override
    protected void initBinding() {
        mBinding = LanguageActivitySimpleBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {
        mBinding.tvAnnotation.setText("language 测试");
    }

    @Override
    public void initView() {
        mBinding.btFun1.setText("中文");
        mBinding.btFun1.setOnClickListener(v -> {
            LanguageUtils.changeAppLanguage(this,"zh");
        });

        mBinding.btFun2.setText("英文");
        mBinding.btFun2.setOnClickListener(v -> {
            LanguageUtils.changeAppLanguage(this,"en");
        });
    }


    private void switchLanguage(){}




}
