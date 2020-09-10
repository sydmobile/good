package com.syd.good.feature.common;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.syd.good.R;
import com.syd.good.utils.adapter.BaseViewHolder;

import butterknife.BindView;

/**
 * <p>
 * date: 2020/9/3 9:01
 *
 * @author syd
 * @version 1.0
 */
public class ContentViewHolder extends BaseViewHolder<CommonEntity> {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.card_view)
    MaterialCardView cardView;

    public ContentViewHolder(ViewGroup parent, Context context) {
        super(R.layout.main_item_card, parent, context);
    }

    @Override
    public void bindViewHolder(CommonEntity value) {
        tvContent.setText(value.getmContent());
    }
}
