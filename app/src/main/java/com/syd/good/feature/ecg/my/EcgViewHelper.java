package com.syd.good.feature.ecg.my;

import android.util.Log;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/9/22 17:08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class EcgViewHelper {
    public static EcgViewHelper gEcgViewHelper;
    private ScheduledExecutorService mScheduledThreadPool;
    private BaseEcgView mBaseEcgView;

    private EcgViewHelper() {

    }

    public static EcgViewHelper getInstance() {

        if (gEcgViewHelper == null) {
            synchronized (EcgViewHelper.class) {
                if (gEcgViewHelper == null) {
                    gEcgViewHelper = new EcgViewHelper();
                }
            }
        }
        return gEcgViewHelper;
    }


    public void setBaseEcgView(BaseEcgView ecgView) {
        this.mBaseEcgView = ecgView;
    }

    public BaseEcgView getBaseEcgView() {
        return this.mBaseEcgView;
    }

    public void setTrack(String... tracks) {
        mBaseEcgView.setTrackInfo(tracks);
    }

//    public String[] getTrack(){
//        mBaseEcgView.get
//    }


//    @SuppressWarnings("AlibabaThreadShouldSetName")
//    public void startRefreshEcg() {
////        if (mScheduledThreadPool != null) {
////            mScheduledThreadPool.shutdown();
////        }
////        mScheduledThreadPool = new ScheduledThreadPoolExecutor(3);
////        mScheduledThreadPool.scheduleAtFixedRate(() -> {
////            mBaseEcgView.refreshEcgView();
////        }, 100, 100, TimeUnit.MILLISECONDS);
//
//        mBaseEcgView.refreshEcgView();
////        ThreadPoolManager.getInstance().getFixedThreadPool().execute(()->{
////        });
//    }



//    public void startRefreshEcgOnce() {
//        ThreadPoolManager.getInstance().getFixedThreadPool().execute(() -> {
//            mBaseEcgView.refreshEcgView();
//        });
//    }

    public void startRefreshEcgInCurrentThread(){
        mBaseEcgView.refreshEcgView();
    }

    public void stopRefreshEcg() {

//        if (mScheduledThreadPool != null) {
//            mScheduledThreadPool.shutdown();
//        }
        mBaseEcgView.changeFreezeState(BaseEcgView.FreezeState.frozen);
    }

    public void startRefreshEcg(){
        mBaseEcgView.changeFreezeState(BaseEcgView.FreezeState.playing);
    }

    public void addData(List<EcgPointBean> ecgPointBeans) {
        BaseEcgView.addData(ecgPointBeans);
    }


    public void destroy() {
        if (mScheduledThreadPool != null) {
            mScheduledThreadPool.shutdown();
        }
        // TODO clear
//        BaseEcgView.gDataSourceList.clear();
        mBaseEcgView = null;
        gEcgViewHelper = null;
    }


}
