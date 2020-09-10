package com.syd.good.feature.common;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

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
    private Context mContext;
    private CallBack mCallBack;

    public CommonAdapter(Context context, CallBack callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @Override
    public int getItemViewType(int position) {
        return getDatas().get(position).getmType();
    }

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
