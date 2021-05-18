package com.syd.good.feature.viewbinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.databinding.ViewbindingActivityMainBinding;

/**
 * <p>
 * date: 2021/2/3 10:32
 *
 * @author syd
 * @version 1.0
 */
public class ViewBindingActivity extends AppCompatActivity {
    private ViewbindingActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ViewbindingActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.bt.setOnClickListener(v -> {
            Toast.makeText(this,"你竟然敢点我！",Toast.LENGTH_SHORT).show();
        });
        binding.tvContent.setText("我设置的值");

    }
}
