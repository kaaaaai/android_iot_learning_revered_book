package com.kaaaaai.wifidemo.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kaaaaai.wifidemo.R

/**
 * Created by Kaaaaai
 * on 2023/3/28
 * Description：
 */
class WifiScanListAdpter(private val wifiList: List<String>): RecyclerView.Adapter<WifiScanListAdpter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wifi_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(wifiList[position])
    }

    override fun getItemCount(): Int {
        return wifiList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(item: String) {
            itemView.findViewById<TextView>(R.id.item_text).text = item
        }
    }
}