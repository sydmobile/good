package com.syd.good.feature.a_common.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syd.good.R;
import com.syd.good.feature.a_common.bean.CommonEntity;
import com.syd.good.utils.adapter.BaseViewHolder;

import butterknife.BindView;

/**
 * <p>
 * date: 2020/9/3 9:22
 *
 * @author syd
 * @version 1.0
 */
public class TitleViewHolder extends BaseViewHolder<CommonEntity> {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    public TitleViewHolder(ViewGroup parent, Context context) {
        super(R.layout.main_item_title, parent, context);
    }

    @Override
    public void bindViewHolder(CommonEntity value) {
        tvTitle.setText(value.getmTitle());
    }
}
