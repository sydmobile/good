package com.syd.good.utils;

/**
 * <p>
 * date: 2020/7/10 14:10
 *
 * @author syd
 * @version 1.0
 */
public class DistanceUtils {
    // 两点间距离
    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

}
