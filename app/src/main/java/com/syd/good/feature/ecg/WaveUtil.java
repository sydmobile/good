package com.syd.good.feature.ecg;

import android.util.Log;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/24 13:12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class WaveUtil {
    private Timer timer;
    private TimerTask timerTask;
    private static final String TAG = "WaveUtil";

    float data = 0f;

    /**
     * 模拟数据
     */
    public void showWaveData(final WaveView waveShowView){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                data = new Random().nextFloat()*(20f)-10f;
                waveShowView.showLine(data);//取得是-10到10间的浮点数
            }
        };
        //500表示调用schedule方法后等待500ms后调用run方法，50表示以后调用run方法的时间间隔
        timer.schedule(timerTask,500,50);
    }

    /**
     * 停止绘制
     */
    public void stop(){
        if(timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        if(null != timerTask) {
            timerTask.cancel();
            timerTask = null;
        }
    }
}
