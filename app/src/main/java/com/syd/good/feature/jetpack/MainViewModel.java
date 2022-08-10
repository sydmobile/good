package com.syd.good.feature.jetpack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/30 09:19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> counter = new MutableLiveData<>();

    private MutableLiveData<User> mUserMutableLiveData = new MutableLiveData<>();

    public LiveData<String> userName = Transformations.map(mUserMutableLiveData, new Function<User, String>() {
        @Override
        public String apply(User input) {
            return input.getLastName();
        }
    });

    private User mUser = new User();

    private MutableLiveData<String> mUserMutableLiveData1 = new MutableLiveData<>();

    public LiveData<User> pUserLiveData = Transformations.switchMap(mUserMutableLiveData1, new Function<String , LiveData<User>>() {
        @Override
        public LiveData<User> apply(String  input) {
            return Repository.getUser(input);
        }
    });

    public LiveData<MutableLiveData> pLiveData = new LiveData<MutableLiveData>() {
        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public MutableLiveData getValue() {
            return counter;
        }
    };

    public MainViewModel(int countReserved) {
        this.counter.setValue(countReserved);
        mUser.setAge(10);
        mUser.setFirstName("yidong");
        mUser.setLastName("sun");
    }


    public void plusOne() {
        int j = this.counter.getValue();
        this.counter.setValue(++j);
    }

    public void clear() {
        this.counter.setValue(0);
    }

    public void changeName() {
        mUser.setFirstName(mUser.getFirstName() + mUser.getLastName());
        this.mUserMutableLiveData.setValue(mUser);
    }

    public void getUser(String userName) {
         mUserMutableLiveData1.setValue(userName);
    }

    static class MainViewModelFactory implements ViewModelProvider.Factory {
        private int countReserved;

        public MainViewModelFactory(int countReserved) {
            this.countReserved = countReserved;
        }

        @NonNull
        @NotNull
        @Override
        public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
            return (T) new MainViewModel(countReserved);
        }
    }
}
