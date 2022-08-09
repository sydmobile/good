package com.syd.good.feature.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.syd.good.R;

/**
 * <p>
 * date: 2020/8/28 17:15
 *
 * @author syd
 * @version 1.0
 */
public class DialogFragmentStudy extends DialogFragment {
    private TextView tvEnsure;
    private TextView tvCancel;

    public static DialogFragmentStudy newInstance() {
        return new DialogFragmentStudy();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Material_Light_Dialog_MinWidth);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.common_dialog_remind, container, false);
        tvEnsure = view.findViewById(R.id.tv_ensure);
        tvCancel = view.findViewById(R.id.tv_cancel);
        initView();
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    private void initView(){
        tvEnsure.setOnClickListener(v -> {
            Toast.makeText(getContext(),"确定",Toast.LENGTH_SHORT).show();
            dismiss();
        });

        tvCancel.setOnClickListener(v -> {
            dismiss();
        });
    }

}
