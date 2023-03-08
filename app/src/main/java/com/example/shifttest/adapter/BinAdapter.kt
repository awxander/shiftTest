package com.example.shifttest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shifttest.R
import com.example.shifttest.databinding.BinItemBinding
import com.example.shifttest.domain.BinInfo
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BinAdapter : RecyclerView.Adapter<BinAdapter.BinHolder>() {


    private val bins = ArrayList<BinInfo>()

    class BinHolder(view: View) : RecyclerView.ViewHolder(view){

        private val itemBinding = BinItemBinding.bind(view)

        fun bind(binInfo: BinInfo){
            itemBinding.apply {
                tvBinNum.text = binInfo.binNum.toString()
                tvTime.text = getDateAndTime()
            }
        }

        private fun getDateAndTime(): String {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val date = Date()
            return formatter.format(date)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bin_item, parent, false)
        return BinHolder(view)
    }

    override fun getItemCount(): Int = bins.size


    override fun onBindViewHolder(holder: BinHolder, position: Int) {
        holder.bind(bins[position])
    }

    fun addItem(binInfo: BinInfo){
        bins.add(binInfo)
        notifyDataSetChanged()
    }

    fun addItemsList(bins : ArrayList<BinInfo>){
        this.bins.addAll(bins)
        notifyDataSetChanged()
    }
}