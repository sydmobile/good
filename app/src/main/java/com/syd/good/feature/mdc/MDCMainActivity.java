package com.syd.good.feature.mdc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
 *
 * @author syd
 * @version 1.0
 */
public class MDCMainActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;

    @Override
    protected int layoutId() {
        return R.layout.common_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // wwww
        // 标准化流程
        List<CommonEntity> datas =

                new ArrayList<>();

        datas.add(new CommonEntity("Material Design", "内容", CommonType.TYPE_TITLE, null));
        datas.add(new CommonEntity("Material Design", "TabLayout", CommonType.TYPE_CONTENT_SPAN_SIZE_1, null));
        datas.add(new CommonEntity("Material Design", "TabLayout+ViewPager", CommonType.TYPE_CONTENT_SPAN_SIZE_1, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {
            @Override
            public void onClick(CommonEntity commonEntity) {

                switch (commonEntity.getmContent()) {
                    case "TabLayout":
                        Intent intent = new Intent(MDCMainActivity.this, MDCTabLayoutActivity.class);
                        startActivity(intent);
                        break;
                    default:


                }
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
                    case CommonType.TYPE_CONTENT_SPAN_SIZE_1:
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
