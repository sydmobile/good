package com.syd.good.feature.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        return inflater.inflate(R.layout.common_dialog_remind, container, false);
    }

}
