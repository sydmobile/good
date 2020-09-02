package com.syd.good.network;

import com.syd.good.constant.AppConstant;

import retrofit2.Retrofit;

/**
 * 说明：$
 * <p>
 * date: 2020/6/8 15:05
 *
 * @author syd
 * @version 1.0
 */
public class RetrofitHelper {

    /**
     * 服务器地址 1 对应的 Retrofit
     */
    public static class ServerHospital {
        /**
         * 获取 BASE URL 对应的 Retrofit
         *
         * @return Retrofit
         */
        private static Retrofit getRetrofit() {
            return RetrofitManager.getInstance().obtainRetrofit(AppConstant.HTTP.BASE_URL);
        }

        public static <T> T create(Class<T> service) {
            return getRetrofit().create(service);
        }
    }

    public static class ServerBeaconDeploy {

        private static Retrofit getRetrofit() {
            return RetrofitManager.getInstance().obtainRetrofit(AppConstant.HTTP.BEACON_DEPLOY);
        }

        public static <T> T create(Class<T> service) {
            return getRetrofit().create(service);
        }

    }


}
