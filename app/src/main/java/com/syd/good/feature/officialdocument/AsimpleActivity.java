package com.syd.good.feature.officialdocument;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.syd.good.databinding.OfficialdocumentActivityStartBinding;

/**
 * <p>
 * date: 2021/2/23 16:54
 *
 * @author syd
 * @version 1.0
 */
public class AsimpleActivity extends AppCompatActivity {
    OfficialdocumentActivityStartBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OfficialdocumentActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // 获取手机配置信息
        binding.btConfig.setOnClickListener(v -> {
            Resources resources = getResources();

            Log.e("sw",resources.getConfiguration().smallestScreenWidthDp+"dp");
            Log.e("sw",resources.getConfiguration().screenLayout+"大小");
        });
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ASecondActivity.class);
        startActivity(intent);

    }
}
