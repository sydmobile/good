package com.syd.good.feature.ecg;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/9/7 13:44
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class EcgShowHelper {
    private static List<Float> refreshList = new ArrayList<>();
    private static float[] data;
    /**
     * 一排显示的数据，一排 10 个
     */
    private static int intervalNumHeart = 10;

    private static int showIndex;

    public static void start() {
        data = new float[intervalNumHeart];

        for (int i = 1; i < 100; i++) {
            refreshList.add((float) i);
            drawHeartRefresh();
        }

    }

    private static void drawHeartRefresh() {
        // 当前长度
        int nowIndex = refreshList.size();
        if (nowIndex < intervalNumHeart) {
            showIndex = nowIndex - 1;
        } else {
            showIndex = (nowIndex - 1) % intervalNumHeart;
        }

        for (int i = 0; i < intervalNumHeart; i++) {
            if (i > refreshList.size() - 1) {
                break;
            }
            if (nowIndex <= intervalNumHeart) {
                data[i] = refreshList.get(i);
            }else {
                int times = (nowIndex -1)/intervalNumHeart;
                int temp = times * intervalNumHeart + i;
                if (temp < nowIndex){
                    data[i] = refreshList.get(temp);
                }
            }
        }
        logdata();
    }

    private static void logdata(){
        String str = "";
        for (float temp:data){
            int tempInt = (int)temp;
            str+= tempInt+",";
        }
        Log.e("syd","第"+refreshList.size()+"次添加"+str+"========");
    }
}
