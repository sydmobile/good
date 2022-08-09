//package com.syd.good.feature.dragview;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.view.GestureDetectorCompat;
//import androidx.customview.widget.ViewDragHelper;
//
//import com.syd.good.R;
//
///**
// * <pre>
// *     @author Mobile
// *     e-mail : mobilesun516@gmail.com
// *     time   : 2022/1/18 10:27
// *     desc   :
// *     version: 1.0
// * </pre>
// */
//public class DragMenuLayout extends FrameLayout {
//
//    private boolean isShowShadow = true;
//    /** 手势处理类 */
//    private GestureDetectorCompat mGestureDetectorCompat;
//    /** 视图拖拽移动帮助类 */
//    private ViewDragHelper mViewDragHelper;
//    /** 滑动监听器 */
//    private DragListener mDragListener;
//    /** 水平拖拽的距离 */
//    private int range;
//    /** 宽度 */
//    private int width;
//    /** 高度 */
//    private int height;
//
//    /** main视图距离在 ViewGroup 左边的距离 */
//    private int mainLeft;
//    private Context mContext;
//    private ImageView ivShadow;
//
//    /** 左侧布局 */
//    private RelativeLayout vgLeft;
//    /** 右侧（主界面布局） */
//    private RelativeLayout vgMain;
//
//    // 页面状态 默认为关闭
//    private Status mStatus = Status.Close;
//
//
//    public DragMenuLayout(@NonNull Context context) {
//        this(context, null);
//    }
//
//    public DragMenuLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public DragMenuLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        this(context, attrs, defStyleAttr, 0);
//    }
//
//    public DragMenuLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        this.mContext = context;
////        mGestureDetectorCompat = new GestureDetectorCompat(context, );
//        mViewDragHelper = ViewDragHelper.create(this, mDragHelperCallback);
//    }
//
//    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
//
//
//        /**
//         * 拦截所有的子View
//         * @param child 拦截的 view
//         * @param pointerId pointerId
//         * @return 是否捕获
//         */
//        @Override
//        public boolean tryCaptureView(@NonNull View child, int pointerId) {
//            return true;
//        }
//
//        @Override
//        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
//            if (mainLeft + dx < 0) {
//                return 0;
//            } else if (mainLeft + dx > range) {
//                return range;
//            } else {
//                return left;
//            }
//        }
//
//        @Override
//        public int getViewHorizontalDragRange(@NonNull View child) {
//            return width;
//        }
//
//        @Override
//        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
//            super.onViewReleased(releasedChild, xvel, yvel);
//            if (xvel > 0) {
//                open();
//            } else if (xvel < 0) {
//                close();
//            } else if (releasedChild == vgMain && mainLeft > range * 0.3) {
//                open();
//            } else if (releasedChild == vgLeft && mainLeft > range * 0.7) {
//                open();
//            } else {
//                close();
//            }
//        }
//
//        @Override
//        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
//            if (changedView == vgMain) {
//                mainLeft = left;
//            } else {
//                mainLeft = mainLeft + left;
//            }
//            if (mainLeft < 0) {
//                mainLeft = 0;
//            } else if (mainLeft > range) {
//                mainLeft = range;
//            }
//
//            if (isShowShadow) {
//                ivShadow.layout(mainLeft, 0, mainLeft + width, height);
//            }
//            if (changedView == vgLeft) {
//                vgLeft.layout(0, 0, width, height);
//                vgMain.layout(mainLeft, 0, mainLeft + width, height);
//            }
//
//            dispatchDragEvent(mainLeft);
//
//
//        }
//
//
//    };
//
//    enum Status {
//        Close, Open
//    }
//
//
//    private void dispatchDragEvent(int mainLeft) {
//        if (mDragListener == null) {
//            return;
//        }
//        float percent = mainLeft / (float) range;
//        // 根据滑动的距离的比例，进行带有动画的缩小和放大 View
//        animateView(percent);
//        // 进行回调滑动的百分比
//        mDragListener.onDrag(percent);
//        Status lastStatus = mStatus;
//        if (lastStatus != getStatus() && mStatus == Status.Close) {
//            mDragListener.onClose();
//        } else if (lastStatus != getStatus() && mStatus == Status.Open) {
//            mDragListener.onOpen();
//        }
//
//    }
//
//    private void animateView(float percent) {
//
//        vgMain.animate()
//                .scaleX(1 - percent * 0.3f)
//                .scaleY(1-percent*0.3f)
//                .translationX()
//    }
//
//
//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        if (isShowShadow) {
//            ivShadow = new ImageView(mContext);
//            ivShadow.setImageResource(R.drawable.bg_night);
//            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            addView(ivShadow, 1, lp);
//        }
//
//        // 左侧界面
//        vgLeft = (RelativeLayout) getChildAt(0);
//        // 右侧（主）界面
//        vgMain = (RelativeLayout) getChildAt(isShowShadow ? 2 : 1);
//        vgMain.setDragLayout(this);
//
//        vgMain.setClickable(true);
//        vgLeft.setClickable(true);
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        width = vgLeft.getWidth();
//        height = vgLeft.getHeight();
//        // 可以水平拖动滑动的距离
//        range = (int) (width * 0.6f);
//
//    }
//
//    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            return Math.abs(distanceY) <= Math.abs(distanceX);
//        }
//    }
//
//}
