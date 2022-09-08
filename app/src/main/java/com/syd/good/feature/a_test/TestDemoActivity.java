package com.syd.good.feature.a_test;

import android.content.Intent;
import android.util.Log;

import com.syd.good.R;
import com.syd.good.databinding.TestActivityMainBinding;
import com.syd.good.feature.a_common.CommonType;
import com.syd.good.feature.a_common.adapter.CommonAdapter;
import com.syd.good.feature.a_common.base.CommonActivity;
import com.syd.good.feature.a_common.bean.CommonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/22 14:54
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestDemoActivity extends CommonActivity<TestActivityMainBinding> {
    private final String TITLE = "Activity Result API 结果回调";
    private final String FUNCTION1 = "返回结果";
    private final String FUNCTION2 = "测试功能2";
    private final String TITLE2 = "我是第二个功能";
    private final String FUNCTION21 = "测试功能3";


    @Override
    protected void initBinding() {
        // xinjia
        mBinding = TestActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
    }

    @Override
    protected List<CommonEntity> createList() {
        List<CommonEntity> list = new ArrayList<>();
        list.add(new CommonEntity(TITLE, "", CommonType.TYPE_TITLE, null));
        list.add(new CommonEntity("", FUNCTION1, CommonType.TYPE_CONTENT_COMMON, null));
        list.add(new CommonEntity("", FUNCTION2, CommonType.TYPE_CONTENT_COMMON, null));
        list.add(new CommonEntity(TITLE2, FUNCTION21, CommonType.TYPE_CONTENT_COMMON, null));
        return list;
    }

    @Override
    protected CommonAdapter.CallBack createCallBack() {

        return commonEntity -> {
            switch (commonEntity.getmContent()) {
                case FUNCTION1:
                    returnResult();
                    break;
                case FUNCTION2:
                    Log.e("点击了", FUNCTION2);
                    mBinding.iv1.setImageResource(R.drawable.ic_home_selected);
                    break;
                case FUNCTION21:
                    Log.e("点击了", FUNCTION21);
                    mBinding.et.setText("我是显示内容。。。");
                    break;
                default:

            }
        };
    }


    /**
     * 携带信息返回页面
     */
    private void returnResult() {
        Intent intent = new Intent();
        intent.putExtra("data", "我来自第二个页面");
        setResult(RESULT_OK, intent);
        finish();
    }
}
