package com.syd.good.feature.drawableresource;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.feature.a_common.adapter.CommonAdapter;
import com.syd.good.feature.a_common.bean.CommonEntity;
import com.syd.good.feature.a_common.CommonType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <p>
 * date: 2020/9/3 8:59
 * 普通通用的 Activity
 * @author syd
 * @version 1.0
 */
public class DrawableResourceActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;


    @Override
    protected int layoutId() {
        return R.layout.drawableresource_activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 标准化流程
        List<CommonEntity> datas = new ArrayList<>();

        datas.add(new CommonEntity("标题", "内容", CommonType.TYPE_CONTENT_COMMON, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {
            @Override
            public void onClick(CommonEntity commonEntity) {

            }
        };
        actionInit(datas, callBack);


    }

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
                        return 4;
                    case CommonType.TYPE_CONTENT1:
                        return 2;
                    default:
                        return 1;

                }
            }
        });
        rlv.setLayoutManager(gridLayoutManager);
        rlv.setAdapter(adapter);
    }

}
