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
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaaaaai.wifidemo.databinding.FragmentFirstBinding
import com.kaaaaai.wifidemo.utils.WifiScanListAdpter
import kotlin.math.log

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
            scanWiFi()
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
            scanWiFi()
        }
    }

    private fun scanWiFi() {
        arrayList.clear()
        wifiScanListAdapter!!.notifyDataSetChanged()

        requireActivity().registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        wifiManager!!.startScan()
        Toast.makeText(requireActivity(), "Scanning WiFi ...", Toast.LENGTH_SHORT).show()

//        val channelList = mutableListOf<Int>()
//        val channelHistogram = wifiScanner.channelHistogram
//
//        for (i in 0 until channelHistogram.size) {
//            val numAps = channelHistogram[i]
//            if (numAps > 0) {
//                val channel = i + 1
//                channelList.add(channel)
//                Log.d(TAG, "Channel $channel has $numAps APs")
//            }
//        }
//
//        for (channel in channelList) {
//            val accessPoints = wifiScanner.getScanResultsFromChannel(channel)
//            for (accessPoint in accessPoints) {
//                val ssid = accessPoint.SSID
//                val bssid = accessPoint.BSSID
//                val level = accessPoint.level
//                Log.d(TAG, "SSID: $ssid, BSSID: $bssid, Level: $level")
//            }
//        }
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
                Log.d("WIFIScannerActivity", "WIFI SSID: $wifi_ssid")

                var wifi_ssid_first_nine_characters = ""

                if (wifi_ssid.length > 8) {
                    wifi_ssid_first_nine_characters = wifi_ssid.substring(0, 9)
                } else {
                    wifi_ssid_first_nine_characters = wifi_ssid
                }
                Log.d("WIFIScannerActivity", "WIFI SSID 9: $wifi_ssid_first_nine_characters")

                Log.d(
                    "WIFIScannerActivity",
                    "scanResult.SSID: " + scanResult.SSID + ", scanResult.capabilities: " + scanResult.capabilities
                )

                val frequency = scanResult.frequency
                val channel = getChannelFromFrequency(frequency)
                Log.d("WIFIScannerActivity","当前信道"+channel)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        requireActivity().unregisterReceiver(wifiReceiver)
    }
}