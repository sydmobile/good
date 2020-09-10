package com.syd.good.feature.eventbus;

import android.content.Context;
import android.content.Intent;
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
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * EventBust 第二个页面
 * date: 2020/9/3 8:59
 *
 * @author syd
 * @version 1.0
 */
public class EventBusSecondActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;

    public static void acitonStart(Context context) {
        Intent intent = new Intent(context, EventBusSecondActivity.class);
        context.startActivity(intent);
    }

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
        // TODO 添加数据
        datas.add(new CommonEntity("EventBust 学习", "", CommonType.TYPE_TITLE, null));
        datas.add(new CommonEntity("EventBust 学习", "给页面1发送消息", CommonType.TYPE_CONTENT_COMMON, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {

            @Override
            public void onClick(CommonEntity commonEntity) {
                // TODO 具体流程
                switch (commonEntity.getmContent()) {
                    case "给页面1发送消息":
                        EventBus.getDefault().post("来自第二个页面的消息");
                        finish();
                        break;
                    case "":
                    default:
                        break;

                }
            }
        };
        actionInit(datas, callBack);

    }

    /**
     * 处理消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
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
