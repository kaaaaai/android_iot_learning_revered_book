package com.kaaaaai.wifidemo;

import android.content.Context;

/**
 * Created by Zadeh
 * on 2023/3/31
 * Description：用于 WiFi 连接前置动作，扫描当前环境下的 WiFi 列表，筛选出最优的 WiFi 信道
 */
public interface IWifiChannelHelper {
    interface WifiChannelListener {

        /**
         * 扫描搜索 WiFi channel 的周期
         *
         * @param currentCycle 当前周期
         * @param totalCycle   总周期
         */
        void onFindBestChannelCycle(int currentCycle, int totalCycle);

        /**
         * 返回最优信道结果
         *
         * @param channel WiFi 信道序号
         */
        void onFindBestChannelSucceed(int channel);

        /**
         * 未找到最优 WiFi 信道
         */
        void onFindBestChannelFailure();
    }

    void startFindTheBestWifiChannel(Context context, WifiChannelListener listener);
}


