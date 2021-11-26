//package com.syd.good.feature.book;
//
//import android.app.Activity;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.util.DisplayMetrics;
//import android.view.View;
//import android.view.WindowManager;
//
//import com.syd.good.App;
//
///**
// * <pre>
// *     @author Mobile
// *     e-mail : mobilesun516@gmail.com
// *     time   : 2021/10/27 16:54
// *     desc   :
// *     version: 1.0
// * </pre>
// */
//public class BookNote {
//    private int num;
//    private float y;
//    private float s;
//    private static final DisplayMetrics DISPLAY_METRICS = Resources.getSystem().getDisplayMetrics();
//    private static final DisplayMetrics OUT_METRICS = new DisplayMetrics();
//
//    /**
//     * 获取逻辑密度 160dpi 返回值为 1
//     *
//     * @return density
//     */
//    public static float getDensity() {
//        return DISPLAY_METRICS.density;
//    }
//
//
//    /**
//     * dots-per-inch
//     *
//     * @return 160 240 ...
//     */
//    public static int getDensityDpi() {
//        return DISPLAY_METRICS.densityDpi;
//    }
//
//    /**
//     * 与字体有关，正常情况下和 density 一样
//     *
//     * @return scaleDensity
//     */
//    public static float getScaleDensity() {
//        return DISPLAY_METRICS.scaledDensity;
//    }
//
//    /**
//     * 获取宽度 px
//     *
//     * @return px
//     */
//    public static int getWidthPixels() {
//        return DISPLAY_METRICS.widthPixels;
//    }
//
//    /**
//     * 获取高度 px
//     * 这个高度是屏幕真正的分辨率减去状态栏和导航栏（不论有还是没有）
//     *
//     * @return px
//     */
//    public static int getHeightPixels() {
//        return DISPLAY_METRICS.heightPixels;
//    }
//
//    /**
//     * Y 方向上的屏幕密度
//     *
//     * @return dpi
//     */
//    public static float getYdpi() {
//        return DISPLAY_METRICS.ydpi;
//    }
//
//    /**
//     * X 方向上的屏幕密度
//     *
//     * @return dpi
//     */
//    public static float getXdpi() {
//        return DISPLAY_METRICS.xdpi;
//    }
//
//    /**
//     * 获取状态栏高度
//     *
//     * @return 状态栏高度
//     */
//    public static int getStatusBarHeight() {
//        int height = 0;
//        int resourceId = App.getInstance().getResources().getIdentifier(
//                "status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            return App.getInstance().getResources().getDimensionPixelSize(resourceId);
//        } else {
//            return 0;
//        }
//    }
//
//    /**
//     * 获取系统栏高度
//     * 包括：导航栏、状态栏
//     * 获取的高度是固定的无论是否有导航栏和状态栏
//     * <p>
//     * <p>


//     * mPrivateFlags3 标志位 int 类型 int 32 位，也就是可以表示 32 种是否，不管正负，只是判断与对应的标志位与运算的时候是否为 0
//     * 如果是 0 的话就说明不需要这么操作，不是零，就说明当前位置为 1 才能与 1 与运算得出结果不是 0 至于是几就和所在的位数有关了 。
//     * <p>
//     * 标志位，用来判断在 layout 的时候是否需要调用 measure 方法。  转成二进制   1000
//     * static final int PFLAG3_MEASURE_NEEDED_BEFORE_LAYOUT = 0x8;
//     * <p>
//     * 标志位还有很多，总之就是保证 32 位中只有 1 位为 1 ，其余都是 0 。
//     * <p>
//     * 图片：标识位
//     * <p>
//     * 判断
//     * <p>
//     * 什么时候不等于就0 ，也就是第4位不等于0 的时候，这个时候表明需要进行 onMeasure()
//     * <p>
//     * public void layout(int l,int t,int r,int b){
//     * if((mPrivateFlags3 & PLAG3_MEASURE_NEEDED_BEFORE_LAYOUT)!= 0){
//     * onMeasure(mOldWidthMeasureSpec,mOldHeightMeasureSpec);
//     * <p>
//     * // 重置标志位为 0 ，表示不执行。这样保证 mPrivateFlags3 的其他标志位不会发生任何改变，因为都是与 1 进行与运算，只有当前的标识为与 0 进行与运算，
//     * 结果就是把当前的位置置为 0 了。
//     * mPrivateFlags3 &= ~PLAG3_MEASURE_NEEDED_BEFORE_LAYOUT
//     * }
//     * <p>
//     * int oldL = mLeft;
//     * <p>
//     * <p>
//     * }
//     * <p>
//     * <p>
//     * <p>
//     * <p>
//     * <p>
//     * <p>
//     * <p>
//     * 与 运算的时候，与 1 与运算，那么结果就是原数，与 0 与运算那么结果就是  0 。
//     *
//     * @return 导航栏高度
//     */
//
//    public void layout(int l, int t, int r, int b) {
//        // 1. 位运算，取标志位，判断是否需要执行 onMeasure 方法
//        if ((mPrivateFlags3 & PFLAG3_MEASURE_NEEDED_BEFORE_LAYOUT) != 0) {
//            onMeasure(mOldWidthMeasureSpec, mOldHeightMeasureSpec);
//            // 重置标志位为 0 ，标识不执行。非常灵活
//            mPrivateFlags3 &= ~PFLAG3_MEASURE_NEEDED_BEFORE_LAYOUT;
//        }
//
//        int oldL = mLeft;
//        int oldT = mTop;
//        int oldB = mBottom;
//        int oldR = mRight;
//
//        // 2.判断 View 在容器的位置是否发生变化
//        // ture 表示 View 的位置或尺寸发生了变化
//        boolean changed = isLayoutModeOptical(mParent) ?
//                setOpticalFrame(l, t, r, b) : setFrame(l, t, r, b);
//
//        //
//        //如果 View 的布局发生了变化或者 mPrivateFlags 有需要 LAYOUT 的标签 PFLAG_LAYOUT_REQUIRED
//        // PFLAG_LAYOUT_REQUIED 不用管是多少，就是 32 位中某一位为 1 ，其余都是 0
//        if (changed || (mPrivateFlags & PFLAG_LAYOUT_REQUIED) == PFLAG_LAYOUT_REQUIED) {
//            // 触发 onLayout  方法， View 中默认的 onLayout 方法是个空方法
//            // 继承自 ViewGroup 的类都需要实现 onLayout 方法，从而在 onLayout 方法中依次循环子View，
//            // 并调用子 View 的 layout 方法。
//            onLayout(changed, l, t, r, b);
//        }
//    }
//
//    // 这里只需要关注 setFrame() 方法和 onLayout 方法就可以了，onLayout 方法由其子类实现，先来看一下 setFrame
//
//    /**
//     * @param l
//     * @param t
//     * @param r
//     * @param b
//     * @return 这个方法就是用来设置 view 的值的，同时如果 view 内容发生了改变，会触发
//     */
//    public boolean setFrame(int l, int t, int r, int b) {
//        boolean changed = false;
//        if (mLeft != l || mRight != r || mTop != t || mBottom != b) {
//            // 将新旧 left、right、top、bottom 进行对比，只要不完全一样，就说明有变化
//            changed = true;
//            ......
//            // 分别计算 View 的新旧尺寸
//            int oldWidth = mRight - mLeft;
//            int oldHeight = mBottom - mTop;
//            int newWidth = right - left;
//            int newHeight = bottom - top;
//            // 比较 view 的新旧尺寸是否相同，如果尺寸发生了变化，那么 sizeChanged 的值为 true
//            boolean sizeChanged = (newWidth != oldWidth) || (newHeight != oldHeight);
//            ...
//            // invalidate our old position
//
//        }
//    }
//
//
//    /*
//    https://cloud.tencent.com/developer/article/1840849
//    layout 方法，说白了，就是从 ViewRootImpl 开始执行 performLayout
//    界面的绘制触发 performMeasure、performLayout 方法，在 performLayout 方法中就会调用 mView
//    的 layout 方法开始一层层 View 的布局工作。
//
//     */
//    private void performLayout(WindowManager.LayoutParams lp, int desiredWindowWidth, int desiredWindowHeight) {
//        final View host = mView;
//        host.layout(0,0,host.getMeasuredWidth(),host.getMeasuredHeight());
//    }
//    /*
//    mView 就是顶层 View --- DecorView ，那么就去 DecorView 的 layout 方法看看：
//    结果是 DecorView 没有 layout 方法，所以调用的是它的父类的 layout 方法，直接就是 View 的 layout 方法。
//    省去细节，只看重点就是
//
//    会执行 setFrame() 来确定位置（setOpticalFrame 最终也是执行 setFrame），就是一个长方形。
//    在 setFrame 方法里面会判断位置是否发生了改变或者大小。
//    然后会根据 changed 和 标志位来判断是否执行 onLayout 方法
//    一般 View 的 onLayout 方法都是空的，只有 ViewGroup 才会实现
//     */
//    public void layout(int l,int t,int r,int b){
//        boolean changed = isLayoutModeOptical(mParent)?
//                setOpticalFrame(l,t,r,b):setFrame(l, t, r, b);
//        if (changed || mPrivateFlags & PFLAG_LAYOUT_REQUIRED == PFLAG_LAYOUT_REQUIRED){
//            onLayout(changed,l,t,r,b);
//        }
//    }
//
//    protected void onLayout(boolean changed,int left,int top,int right,int bottom){
//
//    }
//
//
//
//
//    public static int getNavigationBarHeight() {
//        int resourceId;
//        int rid = App.getInstance().getResources().getIdentifier(
//                "config_showNavigationBar", "bool", "android");
//        if (rid != 0) {
//            resourceId = App.getInstance().getResources().getIdentifier(
//                    "navigation_bar_height", "dimen", "android");
//            return App.getInstance().getResources().getDimensionPixelSize(resourceId);
//        } else{
//
//            return 0;
//        }
//    }
//
//    /**
//     * 获取真实的宽高（包含了状态栏、导航条，也就是手机的分辨率）
//     *
//     * @param activity Activity
//     * @return int[] 宽高
//     */
//    public static int[] getRealSize(Activity activity) {
//        activity.getWindowManager().getDefaultDisplay().getRealMetrics(OUT_METRICS);
//        return new int[]{OUT_METRICS.heightPixels, OUT_METRICS.widthPixels};
//    }
//
//
//    /**
//     * 获取当前屏幕截图，包含状态栏
//     *
//     * @param activity 当前页面
//     * @return 截图
//     */
//    public static Bitmap snapShotWithStatusBar(Activity activity) {
//        View view = activity.getWindow().getDecorView();
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
//        Bitmap bmp = view.getDrawingCache();
//        int[] size = getRealSize(activity);
//        Bitmap bp;
//        bp = Bitmap.createBitmap(bmp, 0, 0, size[0], size[1]);
//        view.destroyDrawingCache();
//        return bp;
//    }
//
//    /**
//     * 获取当前设备 sw 的值
//     *
//     * @return sw 值
//     */
//    public static int getSMDP() {
//        return App.getInstance().getResources().getConfiguration().smallestScreenWidthDp;
//    }
//
//
//}
