package com.syd.good.feature.jetpack;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/30 14:49
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Repository {
    public static LiveData<User> getUser(String userName){
        MutableLiveData<User> userLiveData = new MutableLiveData<>();
        userLiveData.setValue(new User("first:"+userName,"last:"+userName));
        return userLiveData;
    }

}
