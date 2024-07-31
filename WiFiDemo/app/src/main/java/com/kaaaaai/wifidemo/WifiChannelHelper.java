package com.kaaaaai.wifidemo;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Zadeh
 * on 2023/3/31
 * Description：内部实现类
 */
class WifiChannelHelper implements IWifiChannelHelper {
    private final String TAG = "WifiChannelHelper";

    private Context context;
    private WifiChannelListener listener;

    private WifiManager wifiManager;
    private List<ScanResult> results;

    private HashMap<Integer, List<String>> channelMaps = new HashMap<>();

    private Timer timer;
    private TimerTask task;
    private int TimerCycle = 0;

    public WifiChannelHelper(Context context) {

    }

    @Override
    public void startFindTheBestWifiChannel(Context context, WifiChannelListener listener) {
        this.listener = listener;

    }

    private void startTimer() {
        initTimer();
        timer.schedule(task, 0, 3 * 1000);
    }

    private void initTimer() {
        timer = new Timer();
        task  = new TimerTask() {
            @Override
            public void run() {
                if (TimerCycle > 3) {
                    cancelTimer();
                }else {
                    startScanWifi();
                }
                TimerCycle++;
            }
        };
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void startScanWifi() {

    }


}
