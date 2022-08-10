package com.syd.good.feature.a_common.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.syd.good.feature.a_common.CommonType;
import com.syd.good.feature.a_common.adapter.CommonAdapter;
import com.syd.good.feature.a_common.bean.CommonEntity;

import java.util.List;

/**
 * <p>
 * date: 2020/9/3 8:59
 * 普通通用的 Activity
 *
 * @author syd
 * @version 1.0
 */
@SuppressLint("NonConstantResourceId")
public abstract class CommonActivity<T extends ViewBinding> extends AppCompatActivity {

    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setContentView(mBinding.getRoot());
        init(savedInstanceState);
    }


    private void init(Bundle savedInstanceState) {
        actionInit(createList(), createCallBack());
        initView();

    }

    /**
     * 完成 ViewBinding 的初始化，默认使用 CommonActivityBinding 就可以了
     */
    protected abstract void initBinding();


    /**
     * 初始 View
     */
    protected abstract void initView();

    /**
     * 生成功能列表数据
     *
     * @return list
     */
    protected abstract List<CommonEntity> createList();

    /**
     * 创建回调
     *
     * @return 功能回调
     */
    protected abstract CommonAdapter.CallBack createCallBack();


    /**
     * 基本内容
     *
     * @param datas    数据
     * @param callBack 回调
     */
    private void actionInit(List<CommonEntity> datas, CommonAdapter.CallBack callBack) {
        CommonAdapter adapter = new CommonAdapter(this, callBack);
        adapter.setDatas(datas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getDatas().get(position).getmType()) {
                    case CommonType.TYPE_TITLE:
                    case CommonType.TYPE_CONTENT_SPAN_SIZE_4:
                        return 4;
                    case CommonType.TYPE_CONTENT_SPAN_SIZE_2:
                        return 2;
                    case CommonType.TYPE_CONTENT_SPAN_SIZE_3:
                        return 3;
                    default:
                        return 1;

                }
            }
        });
        try {
            ViewGroup viewGroup = (ViewGroup) mBinding.getRoot();
            ViewGroup vg = (ViewGroup) viewGroup.getChildAt(0);
            RecyclerView rl = (RecyclerView) vg.getChildAt(0);
            rl.setLayoutManager(gridLayoutManager);
            rl.setAdapter(adapter);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage() + "=");
            Toast.makeText(this, "布局不正确", Toast.LENGTH_SHORT).show();
        }

    }

}
