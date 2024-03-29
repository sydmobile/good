package com.syd.good.feature.a_common.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.syd.good.feature.a_common.CommonType;
import com.syd.good.feature.a_common.bean.CommonEntity;
import com.syd.good.utils.adapter.BaseAdapter;
import com.syd.good.utils.adapter.BaseViewHolder;

/**
 * <p>
 * date: 2020/9/3 9:04
 *
 * @author syd
 * @version 1.0
 */
public class CommonAdapter extends BaseAdapter<CommonEntity> {
    private final Context mContext;
    private final CallBack mCallBack;

    public CommonAdapter(Context context, CallBack callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @Override
    public int getItemViewType(int position) {
        return getDatas().get(position).getmType();
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @NonNull
    @Override
    public BaseViewHolder<CommonEntity> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder<CommonEntity> viewHolder;
        switch (viewType) {
            case CommonType.TYPE_TITLE:
                viewHolder = new TitleViewHolder(parent, mContext);
                break;

            default:
                viewHolder = new ContentViewHolder(parent, mContext);
                viewHolder.itemView.setOnClickListener(view -> mCallBack.onClick(getDatas().get(viewHolder.getAdapterPosition())));
                break;
        }
        return viewHolder;
    }

    public interface CallBack {
        /**
         * 点击回调
         *
         * @param commonEntity 对象
         */
        void onClick(CommonEntity commonEntity);
    }

}
