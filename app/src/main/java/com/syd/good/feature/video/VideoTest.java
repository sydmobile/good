package com.syd.good.feature.video;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;

/**
 * 说明：$
 * <p>
 * date: 2019/12/20 9:44
 *
 * @author syd
 * @version 1.0
 */
public class VideoTest extends BaseActivity {
    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        VideoView videoView = findViewById(R.id.vv);
//        videoView.setMediaController(new MediaController(this));
//        videoView.setVideoPath("http://app.nxssxy.com:1002/upload/newsVideo/20191219" +
//                "/6371239424761718755335131.mp4");
//        videoView.start();
//        videoView.requestFocus();
//    }
//    @Override
//    protected int layoutId() {
//        return R.layout.activity_video;
//    }
//
//    @Override
//    protected void init(Bundle savedInstanceState) {
//
//    }

}
