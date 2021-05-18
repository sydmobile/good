package com.syd.good.feature.officialdocument;

import android.content.pm.PackageItemInfo;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.syd.good.R;
import com.syd.good.databinding.OfficaildocumentActivityDrawableBinding;

/**
 * <p>
 * date: 2021/2/25 16:39
 *
 * @author syd
 * @version 1.0
 */
public class DrawableActivity extends AppCompatActivity {
    OfficaildocumentActivityDrawableBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OfficaildocumentActivityDrawableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TransitionDrawable drawable = (TransitionDrawable) binding.ivTransition.getDrawable();
        drawable.setCrossFadeEnabled(true);
        binding.ivTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable.startTransition(5000);

            }
        });
        int level = 0;
        ClipDrawable clipDrawable = (ClipDrawable) binding.ivClip.getDrawable();
        binding.ivClip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clipDrawable.setLevel(clipDrawable.getLevel() + 1000);
            }
        });

        ScaleDrawable scaleDrawable = (ScaleDrawable) binding.ivScale.getDrawable();
        scaleDrawable.setLevel(10000);
        binding.ivScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaleDrawable.setLevel(scaleDrawable.getLevel() + 1000);
            }
        });
        String text = getString(R.string.text_html);
        Spanned spanned = Html.fromHtml(text,Html.FROM_HTML_MODE_LEGACY);
        binding.tvContent.setText(spanned);
        Log.e(getClass().getSimpleName(),getTaskId()+"");


    }

    private static CharSequence applyStyles(CharSequence[] content,Object[] tags){
        SpannableStringBuilder text = new SpannableStringBuilder();
        openTags(text,tags);
        for (CharSequence item:content){
            text.append(item);
        }
        closeTags(text,tags);
        return text;
    }

    private static void openTags(Spannable text,Object[] tags){
        for (Object tag:tags){
            text.setSpan(tag,0,0,Spannable.SPAN_MARK_MARK);
        }
    }

    private static void closeTags(Spannable text,Object[] tags){
        int len = text.length();
        for (Object tag:tags){
            if (len>0){
                text.setSpan(tag,0,len,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }else {
                text.removeSpan(tag);
            }
        }
    }

//    public static CharSequence bold(CharSequence... content){
//        return applyStyles(content,new StyleSpan(Typeface.ITALIC));
//    }
}
