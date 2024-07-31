package com.kaaaaai.wifidemo

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.kaaaaai.wifidemo.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var TAG = "SecondFragment"

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val wifiManager: WifiManager by lazy {
        requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 连接到指定的 WiFi 热点
        connectToWifi("NiceBuild", "izyrec2022")

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun connectToWifi(ssid: String, password: String) {
        Log.i(TAG, "connectToWifi: " + ssid + " password:" + password)
        // 检查 WiFi 是否已启用
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        if (!wifiManager.isWifiEnabled) {
            wifiManager.isWifiEnabled = true
        }

        // 扫描可用的 WiFi 热点
        wifiManager.startScan()
        val wifiList = wifiManager.scanResults

        // 查找指定的 WiFi 热点
        val wifi = wifiList.firstOrNull { it.SSID == "\"$ssid\"" }
        if (wifi != null) {
            // 创建 WiFi 配置
            val wifiConfig = WifiConfiguration().apply {
                SSID = "\"$ssid\""
                preSharedKey = "\"$password\""
                allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
            }

            // 连接到 WiFi 热点
            val netId = wifiManager.addNetwork(wifiConfig)
            wifiManager.enableNetwork(netId, true)
            wifiManager.reconnect()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}