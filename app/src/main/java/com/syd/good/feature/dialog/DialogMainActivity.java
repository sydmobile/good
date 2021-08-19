package com.syd.good.feature.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.syd.good.R;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/16 17:50
 *     desc   : dialog 源码查看
 *     version: 1.0
 * </pre>
 */
public class DialogMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_activity_dialog_main);
        Button button = findViewById(R.id.bt_dialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(DialogMainActivity.this);
                dialog.setContentView(R.layout.common_dialog_remind);
                dialog.show();
            }
        });
    }
}