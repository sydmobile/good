package com.syd.good.feature.jetpack;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.syd.good.R;
import com.syd.good.feature.jetpack.lifecycles.MyObserver;
import com.syd.good.feature.jetpack.room.AppDataBase;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/30 09:36
 *     desc   : JetPact 入门 ViewModel 练习
 *     version: 1.0
 * </pre>
 */
public class MainJetPactActivity extends AppCompatActivity {
    MainViewModel mMainViewModel;
    private TextView mTv;
    private TextView mTvName;
    private Button btClear;
    private SharedPreferences sp;
    private ThreadPoolExecutor mThreadPoolExecutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jetpact_activity_main_jet_pact);
        mThreadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        // 添加生命周期观察者
        getLifecycle().addObserver(new MyObserver());

        // 获取保存的数据
        sp = getPreferences(Context.MODE_PRIVATE);
        int c = sp.getInt("count", 0);

        // 无参构造函数
//        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mMainViewModel = new ViewModelProvider(this, new MainViewModel.MainViewModelFactory(c)).get(MainViewModel.class);


        Button btPlus = findViewById(R.id.bt_plus);
        btClear = findViewById(R.id.bt_clear);
        mTv = findViewById(R.id.tv);
        mTvName = findViewById(R.id.tv_name);
        Button btChangeName = findViewById(R.id.bt_change_name);
        Button btGetUser = findViewById(R.id.bt_get_user);

        btPlus.setOnClickListener(v -> {
//            mMainViewModel.counter++;
//            refreshCounter();
            mMainViewModel.plusOne();
        });

        btClear.setOnClickListener(v -> {
//            mMainViewModel.counter = 0;
//            refreshCounter();
            mMainViewModel.clear();
        });

        btChangeName.setOnClickListener(v -> {
            mMainViewModel.changeName();
        });

        btGetUser.setOnClickListener(v -> {
            mMainViewModel.getUser(mTvName.getText()+"xx");
        });

//        refreshCounter();

        // 给数据添加观察者
        mMainViewModel.pLiveData.getValue().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mTv.setText(integer.toString());
            }
        });

        mMainViewModel.userName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTvName.setText(s);
            }
        });

        mMainViewModel.pUserLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mTvName.setText(user.getFirstName());
            }
        });

    }


    public void refreshCounter() {
//        mTv.setText(mMainViewModel.counter+"");

    }


    @Override
    protected void onPause() {
        super.onPause();
        sp.edit().putInt("count", (Integer) mMainViewModel.pLiveData.getValue().getValue()).apply();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }



    /**
     * 与 ROOM 数据库相关
     */
    public void aboutRoom(){

        // 获取数据库访问对象
        UserDao userDao = AppDataBase.getInstance(this).userDao();
        User user1 = new User("yidong","sun");
        User user2 = new User("yico","zeng");
        Button btInsert = findViewById(R.id.bt_insert);
        btInsert.setOnClickListener(v -> {
            mThreadPoolExecutor.execute(() -> {
               long id1 =  userDao.insertUser(user1);
               long id2 = userDao.insertUser(user2);
                Log.e("id1===id2",id1+"==="+id2);
            });

        });
    }
}