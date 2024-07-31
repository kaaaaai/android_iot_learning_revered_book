package com.kaaaaai.wifidemo

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.app.Activity
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaaaaai.wifidemo.databinding.FragmentFirstBinding
import com.kaaaaai.wifidemo.utils.WifiScanListAdpter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var wifiScanListAdapter: WifiScanListAdpter? = null

    private var wifiManager: WifiManager? = null
    private var results: List<ScanResult>? = null

    private var arrayList = ArrayList<String>()

    private var timer: Timer? = null
    private var count = 0

    private var channelMaps = mutableMapOf<Int, MutableList<String>>()

    private val TAG = "🐼 Wifi Scan"
    fun startWiFiTimer() {
//        timer = Timer()
//        timer?.schedule(object : TimerTask() {
//            override fun run() {
//                if (count < 10) {
//                    // 每五秒执行一次方法
//                    scanWiFi()
//                    count++
//                } else {
//                    // 取消定时器
//                    timer?.cancel()
//                    timer = null
//                }
//            }
//        }, 0, 5000)

        channelMaps = mutableMapOf<Int, MutableList<String>>()

        val timer = object: CountDownTimer(25000, 5000) {
            override fun onTick(millisUntilFinished: Long) {
                scanWiFi()
            }

            override fun onFinish() {
                count = 0
                for ((key, value) in channelMaps) {
                    val count = value.count()
                    Log.i("🐼", "onFinish: $key-$count")
                    addLog(TAG,"onFinish: $key-$count")
                }
                Log.i("🐼", "onFinish: timer")
                addLog(TAG,"onFinish: onFinish: timer")

            }
        }
        timer.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        wifiManager = requireContext().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (!wifiManager!!.isWifiEnabled) {
            Toast.makeText(requireContext(), "WiFi 没有打开，请先打开 WIFi", Toast.LENGTH_LONG).show()

            wifiManager!!.isWifiEnabled = true
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        } else {
//            scanWiFi()
            startWiFiTimer()
        }
    }

    private fun initView() {
        requireActivity().title = "Scan WiFi"

        wifiScanListAdapter = WifiScanListAdpter(arrayList)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = wifiScanListAdapter

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonScan.setOnClickListener {
//            scanWiFi()
            startWiFiTimer()
        }
        binding.textLog.movementMethod = ScrollingMovementMethod()
    }

    private fun scanWiFi() {
        addLog(TAG,"---------- 分割线 ----------")
        Log.d("🐼 WIFIScannerActivity","---------- 分割线 ----------")
        count += 1
        requireActivity().registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        wifiManager!!.startScan()

//        arrayList.clear()
//        wifiScanListAdapter!!.notifyDataSetChanged()
//
//        Toast.makeText(requireActivity(), "Scanning WiFi ...", Toast.LENGTH_SHORT).show()
    }

    private val wifiReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }

            results = wifiManager!!.scanResults

            requireActivity().unregisterReceiver(this)

            for (scanResult in results!!) {
                var wifi_ssid = ""
                wifi_ssid = scanResult.SSID
//                Log.d("WIFIScannerActivity", "WIFI SSID: $wifi_ssid")

                var wifi_ssid_first_nine_characters = ""

                if (wifi_ssid.length > 8) {
                    wifi_ssid_first_nine_characters = wifi_ssid.substring(0, 9)
                } else {
                    wifi_ssid_first_nine_characters = wifi_ssid
                }
//                Log.d("WIFIScannerActivity", "WIFI SSID 9: $wifi_ssid_first_nine_characters")

//                Log.d("WIFIScannerActivity", "scanResult.SSID: " + scanResult.SSID + ", scanResult.capabilities: " + scanResult.capabilities)

                val frequency = scanResult.frequency
                val channel = getChannelFromFrequency(frequency)
//                Log.d("🐼 WIFIScannerActivity","当前信道"+channel)

                if (channel in 0..12) {
                    addLog("$TAG 第 $count 轮"," SSID: ${scanResult.SSID} 信道：$channel")
                    Log.i("🐼 第" + "$count" + "轮", "SSID: " + scanResult.SSID + " 信道：" + channel)
                    channelMaps.getOrPut(channel) { mutableListOf() }.add(scanResult.BSSID)
                }

                var itemStr = "名称：" + (scanResult.SSID ?: "unKnown") + " " + "信道：" + channel
                if (!arrayList.contains(itemStr)) {
                    arrayList.add(itemStr)
                    wifiScanListAdapter!!.notifyDataSetChanged()

                    binding.textviewFirst.text = "${arrayList.count()} ssid"
                }
            }
        }

        fun getChannelFromFrequency(frequency: Int): Int {
            return when (frequency) {
                in 2412..2484 -> (frequency - 2412) / 5 + 1
                in 5170..5825 -> (frequency - 5170) / 5 + 34
                else -> -1
            }
        }
    }

    private val dateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    private fun addLog(log: String, s: String) {
        var log = log
        log = dateFormat.format(Date()) + " " + log + "$s \n"
        Log.i(TAG, "addLog: $log")
        log += "${binding.textLog.text}".trimIndent()

        if (log.length > 1 shl 13) {
            //8192
            log = log.substring(0, 1 shl 13)
        }
        val finalLog = log

        requireActivity().runOnUiThread{ binding.textLog.text = finalLog }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

//        requireActivity().unregisterReceiver(wifiReceiver)
    }
}