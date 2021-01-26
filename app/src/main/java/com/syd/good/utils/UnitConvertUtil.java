package com.syd.good.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * @author sunyidong
 * 单位转换工具
 */
@SuppressWarnings({"unused"})
public class UnitConvertUtil {

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * byte[] 转 16进制字符串
     *
     * @param bytes byte数组
     * @return String
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }


    /**
     * px转化dp值
     * 方法一，通过 density 来实现
     *
     * @param context context
     * @param pxValue px 值
     * @return 转化后的 dp 值
     */
    public static int px2dpByDensity(Context context, float pxValue) {
        //获取屏幕逻辑密度
        float density = context.getResources().getDisplayMetrics().density;
        DisplayMetrics displayMetrics;
        //加 0.5 是为了四舍五入，强转成 int 类型的话，会忽略掉小数部分。
        return (int) (pxValue / density + 0.5f);

    }


    //=========================================================================================================


    /**
     * dp 转 px 值
     * 方法一，通过 density 实现
     *
     * @param context context
     * @param dpValue dp 值
     * @return 转化后的 px 值
     */
    public static int dp2pxByDensity(Context context, float dpValue) {
        // 获取屏幕逻辑密度
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * dp 转 px 值
     * 方法二 通过 TypedValue 实现
     *
     * @param context context
     * @param dpValue dp 值
     * @return 转化后的 px 值
     */
    public static int dp2pxByTypedValue(Context context, float dpValue) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    //===========================================================================================================================

    /**
     * scaledDensity  比例密度，用在文字的大小上，如果手机上的字体设置为正常的情况下，这个值和 density 是相等的。随着
     * 用户设置的字体的大小不同，这个值是会发生变化的
     * px 转 sp 值
     * 通过 scaledDensity 实现
     *
     * @param context context
     * @param pxValue px 值
     * @return 转化后的 sp 值
     */
    public static int px2spByScaledDensity(Context context, float pxValue) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;

        return (int) (pxValue / scaledDensity + 0.5f);

    }

    //==========================================================================================

    /**
     * sp 转 px
     *
     * @param context context
     * @param spValue sp 值
     * @return 转化后的 px 值
     */
    public static int sp2pxByScaledDensity(Context context, float spValue) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scaledDensity + 0.5f);

    }

    /**
     * sp 转 px
     *
     * @param context context
     * @param spValue sp 值
     * @return px 值
     */
    public static int sp2pxByTypedValue(Context context, float spValue) {
        return (int)
                (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics()));

    }


}
