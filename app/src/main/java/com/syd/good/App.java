package com.syd.good;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.syd.good.utils.LanguageUtil;
import com.syd.good.utils.MultiLanguageUtils;
import com.syd.good.utils.SPUtils;

import java.util.Locale;

/**
 * @author syd
 */
public class App extends Application {
    private static final String TAG = "App";
    private static App app;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SPUtils.init();
//        LanguageUtil.getInstance().changeAppLanguage(this, Locale.CHINESE);
//        MultiLanguageUtils.setAppLanguage(this,Locale.CHINA);
//        registerActivityLifecycleCallbacks(MultiLanguageUtils.callbacks);
        registerActivityLifecycleCallbacks(callbacks);
    }

    ActivityLifecycleCallbacks callbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            LanguageUtil.getInstance().changeAppLanguage(activity, Locale.CHINESE);
//            MultiLanguageUtils.setAppLanguage(activity,Locale.CHINA);
            //强制修改应用语言
//            LanguageUtil.getInstance().
//               changeAppLanguage(activity,
//                        LanguageUtil.getAppLocale(activity));
//                Log.d("TAG", "language = " + LanguageUtil.getAppLocale(activity).getLanguage()
//                        + ";country = " + LanguageUtil.getAppLocale(activity).getCountry());
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

}
