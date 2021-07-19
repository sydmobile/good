package com.syd.good.feature.customview.simple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.syd.good.R;
import com.syd.good.databinding.CustomviewSimpleActivityCustomViewBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomViewActivity extends AppCompatActivity {
    CustomviewSimpleActivityCustomViewBinding binding;

    private List<String> contentList = new ArrayList<>();
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CustomviewSimpleActivityCustomViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initList();
        binding.mlv.setOnDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                myAdapter.notifyDataSetChanged();
            }
        });

        myAdapter = new MyAdapter(this,0,contentList);
        binding.mlv.setAdapter(myAdapter);
    }

    private void initList() {
        contentList.add("Content Item 1");
        contentList.add("Content Item 2");
        contentList.add("Content Item 3");
        contentList.add("Content Item 4");
        contentList.add("Content Item 5");
        contentList.add("Content Item 6");
        contentList.add("Content Item 7");
        contentList.add("Content Item 8");
        contentList.add("Content Item 9");
        contentList.add("Content Item 10");
        contentList.add("Content Item 11");
        contentList.add("Content Item 12");
        contentList.add("Content Item 13");
        contentList.add("Content Item 14");
        contentList.add("Content Item 15");
        contentList.add("Content Item 16");
        contentList.add("Content Item 17");
        contentList.add("Content Item 18");
        contentList.add("Content Item 19");
        contentList.add("Content Item 20");
    }


    public static class MyAdapter extends ArrayAdapter<String>{

        public MyAdapter(@NonNull Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.customview_simple_item_lv, null);
            } else {
                view = convertView;
            }
            TextView textView = (TextView) view.findViewById(R.id.text_view);
            textView.setText(getItem(position));
            return view;
        }
    }
}