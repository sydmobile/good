package com.syd.good.feature.ecg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.syd.good.R;
import com.syd.good.feature.ecg.my.EcgPointBean;
import com.syd.good.feature.ecg.my.EcgView;
import com.syd.good.feature.ecg.my.EcgViewHelper;
import com.syd.good.surfaceView.ThreadPoolManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/24 10:48
 *     desc   : ecg
 *     version: 1.0
 * </pre>
 */
public class EcgActivity extends AppCompatActivity {


    private WaveUtil waveUtil1, waveUtil2;

    private WaveView wave_view1;
    private EcgView mEcgView;
    private SeekBar seekBar;
    private TextView mTextView;
    private Button mBtRefresh;
    private String health_data1 =
//        "0, 0, 0, 20, -4.074210067040861, 10.607175034563927, 8.63021033276993, 3.176998617710538, -2.0982448819630104, 15.989544571777444, -8.01661631809069, 3.1672666881831617, -14.986781365586827, 6.975028067180986, -8.670066274746912, -14.166629549252436, -15.563741519566843, -3.435872251153146, -15.495712129484298, -17.034567201374486, 3.113490776293297, 17.852402514870548, -6.620214596465598, 2.7059985435385627, -0.7340428983493652, -10.043973686078312, -7.7737739199853735, -2.8424998462695203, 17.059975738564418, 15.93792518897802, -2.3771648195463673, 15.193994524995134, 6.621484019611856, -4.239984545754492, 10.917991920104662, 8.379614529790555, 13.469255562481752, 4.572205990863893, 3.168447930322287, -15.256125971186611, 10.442333018136686, 10.058988094758831, 4.557194068451025, -5.863412098902115, -10.674119049302345, -17.90039886688294, 17.480511927989504, -8.017137853690382, -11.366394807960924, -4.342094284776621, -5.188813019488368, -14.518778576603843, 0.38376607069999835, 15.60326161059335, 0.2862259566523022, 9.63564578383711, -15.790278183981389, -3.9261444442295748, 4.533870652141857, 1.9848933256806944, 17.7748719657289, -8.970901630941778, 15.946066993000628, -3.509803506425989, 0.21572468891272578, 0.7978325828833022, 8.418984261053573, 14.765024349135096, 0.2157807681088002, -16.285708001769663, -4.07443261385866, 16.678293917884453, 2.3167820403348323, 6.343874914328779, 16.950519720197946, 6.3350608646351425, -13.797201550797688, 6.3359636448556955, -1.9904817573055098, -10.463632753037821, 17.946546652249914, 6.601183681429902, -3.209125663491344, -10.95741142417091, -16.413791046067832, -11.704358858310352, -14.100762677762402, 16.312286171063313, 5.904962020177933, 15.004839665719885, -12.252615566891805, -3.138796203599046, -17.085699443839182, -14.44486214732084, -2.1664917686820857, 6.983727041885537, 7.296166157006457, 14.040750638799572, -6.542161524595487, 2.301773715768185"
            "0, 0, 0, 20, -4.074210067040861, 10.607175034563927, 8.63021033276993, 3.176998617710538,-2.0982448819630104,15.989544571777444,-8.01661631809069, 3.1672666881831617, -14.986781365586827, 6.975028067180986 ";
    int index = 0;
    private Button btStop;
    private Timer mTimer;

    @SuppressWarnings("unused")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg);
//        EcgShowView ecgView = findViewById(R.id.ecg);
       mEcgView  = findViewById(R.id.ecg1);
        btStop = findViewById(R.id.bt_stop);
        mBtRefresh = findViewById(R.id.bt_refresh);
//        ecgView.setData("10.101253886148333549,8.001253886148333549,-10.003036087844520807,3.002440808573737741," +
//                "13.101253886148333549,14.001253886148333549,-15.003036087844520807,9.002440808573737741," +
//                "13.101253886148333549,14.001253886148333549,-10.003036087844520807,3.002440808573737741");
////        ecgView.setDataList(list);
//
//        ecgView.startScrollTimer();

        String formatHealthData = health_data1.trim();
        List<String> list1 = Arrays.asList(formatHealthData.split(",").clone());

//        mEcgShowView1.setData("", EcgShowView1.SHOW_MODEL_REFRESH);

//        mBtRefresh.setOnClickListener(v -> {
//
//            //noinspection AlibabaAvoidUseTimer
//            mTimer = new Timer();
//            //noinspection AlibabaAvoidUseTimer
//            mTimer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    if (index >= list1.size()) {
//                        index = 0;
//                    }
//                    if (list1.get(index) == null || "".equals(list1.get(index)) || mEcgShowView1 == null) {
//                        return;
//                    }
////                    mEcgShowView1.showLine(Float.parseFloat(list1.get(index)));
//                    List<Float> list = new ArrayList<>();
//                    list.add((float) (Math.random() * 20 - 10));
//                    list.add((float) (Math.random() * 20 - 10));
//                    list.add((float) (Math.random() * 20 - 10));
//                    list.add((float) (Math.random() * 20 - 10));
//                    list.add((float) (Math.random() * 20 - 10));
//                    mMyEcgShowView.drawEcg(list);
//                    index++;
//                }
//            }, 1000, 200);
//        });

        btStop.setOnClickListener(v -> {


        send();
        });


        // 另一个控件的内容
        waveUtil1 = new WaveUtil();
        waveUtil2 = new WaveUtil();

        wave_view1 = findViewById(R.id.wave_view1);
//        wave_view2 = findViewById(R.id.wave_view2);
        seekBar = findViewById(R.id.seek_bar);
        mTextView = findViewById(R.id.tv_wave1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("seek_bar progress is", i + "");
                if (i == 0) {

                    return;
                }
                wave_view1.setWaveLineWidth(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        waveUtil1.showWaveData(wave_view1);
//        waveUtil2.showWaveData(wave_view2);
        wave_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float data = new Random().nextFloat() * (20f) - 10f;
                Log.i("data is --------------", data + "");
                wave_view1.showLine(data);//取得是-10到10间的浮点数
            }
        });

        mTextView.setOnClickListener(v -> {
            waveUtil1.stop();
        });

        EcgViewHelper.getInstance().setBaseEcgView(mEcgView);

    }


    public void send(){
//        ThreadPoolManager.getInstance().getScheduledThreadPool()
//                .scheduleAtFixedRate(() -> {
//                    Log.e("send",System.currentTimeMillis()+"");
//                    getModifyData();
//                }, 0, 50, TimeUnit.MILLISECONDS);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
//                Log.e("send",System.currentTimeMillis()+"");
                getModifyData();
            }
        },10,50);
    }

    /**
     * 获取模拟数据
     */
    public  void getModifyData() {
        List<EcgPointBean> list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            EcgPointBean ecgPointBean = new EcgPointBean();
//            if (mCurrentModifyIndex >= modifyData.length) {
//                mCurrentModifyIndex = 0;
//            }
//            ecgPointBean.LEAD_I = modifyData[mCurrentModifyIndex];
//            ecgPointBean.LEAD_II = modifyData[mCurrentModifyIndex];
//            ecgPointBean.LEAD_III = modifyData[mCurrentModifyIndex];
//            mCurrentModifyIndex++;
//            list.add(ecgPointBean);
//        }

        for (int i = 0; i < 20; i++) {
            EcgPointBean ecgPointBean = new EcgPointBean();
//            if (mCurrentModifyIndex >= modifyData.length) {
//                mCurrentModifyIndex = 0;
//            }
            float nu = (float) (Math.random()*3-1.5);
            ecgPointBean.LEAD_I = nu;
            ecgPointBean.LEAD_II = nu;
            ecgPointBean.LEAD_III = nu;
//            mCurrentModifyIndex++;
            list.add(ecgPointBean);
        }

        EcgViewHelper.getInstance().addData(list);
        EcgViewHelper.getInstance().startRefreshEcgInCurrentThread();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        waveUtil1.stop();
        waveUtil2.stop();
    }
}