package com.syd.good.feature.imageload;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.feature.a_common.adapter.CommonAdapter;
import com.syd.good.feature.a_common.bean.CommonEntity;
import com.syd.good.feature.a_common.CommonType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/3 8:59
 * 普通通用的 Activity
 *
 * @author syd
 * @version 1.0
 */
public class PicassoBaseUseActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.iv_1)
    ImageView iv1;

    @Override
    protected int layoutId() {
        return R.layout.common_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 标准化流程
        List<CommonEntity> datas = new ArrayList<>();

        datas.add(new CommonEntity("标题", "基础使用", CommonType.TYPE_CONTENT_COMMON, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {
            @Override
            public void onClick(CommonEntity commonEntity) {

                switch (commonEntity.getmContent()) {
                    case "基础使用":
                        baseUse();
                        break;
                    default:
                }
            }
        };
        actionInit(datas, callBack);


    }


    public void baseUse() {
        String url = "https://upload-images.jianshu.io/upload_images/944365-53f1a6f2f1e7925c.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/240";
        String url1 = "https://msyt.oss-cn-beijing.aliyuncs.com/2项目案例/1妇幼保健院/妇幼保健院/1首页.png?x-oss-process=image/resize,w,w_1080";
        String url2 = "https://wanandroid.com/blogimgs/08240888-1d86-4eb5-8012-b3fd6945cbb1.jpeg";
        // Picasso 使用了流式接口的调用方式
        Picasso.get()
                // 设置当前 url 缓存无效，重新加载
                .invalidate(url);

        Picasso.get()
                .load(url2)
                .into(iv1);


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }
}
