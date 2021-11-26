package com.syd.good.feature.a_study;

import android.util.Log;

import com.syd.good.R;
import com.syd.good.databinding.CommonActivityBinding;
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
 *     desc   : 学习的快速页面
 *     version: 1.0
 * </pre>
 */
public class StudyDemoActivity extends CommonActivity<CommonActivityBinding> {
    private final String TITLE = "我是功能标题";
    private final String FUNCTION1 = "功能11";
    private final String FUNCTION2 = "功能12";
    private final String TITLE2 = "我是第二个功能";
    private final String FUNCTION21 = "功能21";
    private final String FUNCTION22 = "功能22";

    @Override
    protected void initBinding() {
        mBinding = CommonActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected List<CommonEntity> createList() {
        List<CommonEntity> list = new ArrayList<>();
        list.add(new CommonEntity(TITLE, "", CommonType.TYPE_TITLE, null));
        list.add(new CommonEntity("", FUNCTION1, CommonType.TYPE_CONTENT_SPAN_SIZE_1, null));
        list.add(new CommonEntity("", FUNCTION2, CommonType.TYPE_CONTENT_SPAN_SIZE_1, null));

        list.add(new CommonEntity(TITLE2, "", CommonType.TYPE_TITLE, null));
        list.add(new CommonEntity("", FUNCTION21, CommonType.TYPE_CONTENT_SPAN_SIZE_2, null));
        list.add(new CommonEntity("", FUNCTION22, CommonType.TYPE_CONTENT_SPAN_SIZE_2, null));
        return list;
    }

    @Override
    protected CommonAdapter.CallBack createCallBack() {

        return commonEntity -> {
            switch (commonEntity.getmContent()){
                case FUNCTION1:
                    Log.e("点击了",FUNCTION1);
                    mBinding.tv1.setText("显示内容：：点击了");
                    break;
                case FUNCTION2:
                    Log.e("点击了",FUNCTION2);
                    mBinding.iv1.setImageResource(R.drawable.ic_home_selected);
                    break;
                case FUNCTION21:
                    Log.e("点击了",FUNCTION21);
                    mBinding.et.setText("我是显示内容。。。");
                    break;
                default:

            }
        };
    }
}
