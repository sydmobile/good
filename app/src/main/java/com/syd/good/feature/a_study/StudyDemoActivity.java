package com.syd.good.feature.a_study;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

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
    private final String TITLE = "时间设置";
    private final String FUNCTION1 = "修改时间格式";
    private final String FUNCTION2 = "功能12";
    private final String TITLE2 = "我是第二个功能";
    private final String FUNCTION21 = "功能21";
    private final String FUNCTION22 = "功能22";

    ActivityResultLauncher<Intent> mLauncher;

    @Override
    protected void initBinding() {
        mBinding = CommonActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        registerActivityForResult();
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
            switch (commonEntity.getmContent()) {
                case FUNCTION1:
                    changeTimeFormat();
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
     * 修改时间格式
     */
    public void changeTimeFormat() {
        if (Settings.System.canWrite(this)) {
            if (DateFormat.is24HourFormat(this)) {
                boolean isSuccess = Settings.System.putString(getContentResolver(), Settings.System.TIME_12_24, "12");
                mBinding.tv1.setText("isSuccess:" + isSuccess);
            } else {
                boolean isSuccess = Settings.System.putString(getContentResolver(),
                        Settings.System.TIME_12_24, "24");
                mBinding.tv1.setText("isSuccess:" + isSuccess);
            }
        }else {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:"+getPackageName()));
            mLauncher.launch(intent);
        }

    }

    /**
     * 注册监听
     */
    private void registerActivityForResult() {
        mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == 0) {
                            mBinding.tv2.setText("获得权限");
                        }
                    }
                });
    }


}
