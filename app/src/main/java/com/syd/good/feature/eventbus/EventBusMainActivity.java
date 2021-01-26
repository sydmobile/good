package com.syd.good.feature.eventbus;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.feature.common.CommonAdapter;
import com.syd.good.feature.common.CommonEntity;
import com.syd.good.feature.common.CommonType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <p>
 * date: 2020/9/3 8:59
 *
 * @author syd
 * @version 1.0
 */
public class EventBusMainActivity extends BaseActivity {

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
        // 初始化
        EventBus.getDefault().register(this);

        // 标准化流程
        List<CommonEntity> datas = new ArrayList<>();

        datas.add(new CommonEntity("EventBust 学习", "", CommonType.TYPE_TITLE, null));
        datas.add(new CommonEntity("EventBust 学习", "进入第二页", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("EventBust 学习", "向第二个页面发送消息", CommonType.TYPE_CONTENT_COMMON, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {
            @Override
            public void onClick(CommonEntity commonEntity) {

                switch (commonEntity.getmContent()) {
                    case "向第二个页面发送消息":
                        EventBus.getDefault().postSticky("来自第一个页面的信息");
                        break;
                    case "进入第二页":
                        EventBusSecondActivity.acitonStart(EventBusMainActivity.this);
                        break;
                    default:
                        break;

                }
            }
        };
        actionInit(datas, callBack);

    }

    @Subscribe
    public void handlerMessage(String info) {
        tv1.setText(info);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
