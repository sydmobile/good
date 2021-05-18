package com.syd.good.software;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.syd.good.R;
import com.syd.good.databinding.SoftwareActivityBeaconInfoBinding;

public class BeaconInfoActivity extends AppCompatActivity {
    SoftwareActivityBeaconInfoBinding beaconInfoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beaconInfoBinding = SoftwareActivityBeaconInfoBinding.inflate(getLayoutInflater());
        setContentView(beaconInfoBinding.getRoot());
        beaconInfoBinding.rlUuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BeaconInfoActivity.this)
                        .setTitle("修改UUID")
                        .setView(R.layout.software_dialog_alter)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
//
                        .show();
            }
        });
        beaconInfoBinding.rlvMajor.setOnClickListener(v -> {
            new ProgressDialog(this).show();
        });
        beaconInfoBinding.rlvMinor.setOnClickListener(v -> {
            Snackbar.make(beaconInfoBinding.getRoot(), "修改成功", Snackbar.LENGTH_LONG).show();
        });

    }
}