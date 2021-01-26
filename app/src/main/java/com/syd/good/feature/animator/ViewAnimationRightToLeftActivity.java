package com.syd.good.feature.animator;

import android.os.Bundle;
import android.widget.Button;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/18 14:10
 *
 * @author syd
 * @version 1.0
 */
public class ViewAnimationRightToLeftActivity extends BaseActivity {
    @BindView(R.id.bt)
    Button bt;

    @Override
    protected int layoutId() {
        return R.layout.common_bg_primary_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        bt.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.animator_in_from_right,R.anim.animator_out_to_left);
    }
}
