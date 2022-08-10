package com.syd.good.eventdispatcher;

import android.util.Log;

import com.syd.good.utils.L;

import org.junit.Test;

/**
 * <p>
 * date: 2021/1/29 13:52
 *
 * @author syd
 * @version 1.0
 */
public class Te {
    @Test
   public  void run(){
        Log.e("ss","ww");
    }


    @Test
    public void getRandom(){
        StringBuilder s = new StringBuilder();
        for (int i = 0;i<100;i++){
           double nu =  Math.random()*36-18;
           s.append(nu).append(",");
        }
        Log.e("e",s.toString());
    }

    @Test
    public void tee(){
        float i = 10/2;
        System.out.println(i);
    }

}
