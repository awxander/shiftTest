package com.example.shifttest.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shifttest.data.BinInfo
import com.example.shifttest.databinding.BinItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BinAdapter : RecyclerView.Adapter<BinAdapter.BinHolder>() {


    private val bins = ArrayList<BinInfo>()

    class BinHolder(view: View) : RecyclerView.ViewHolder(view){

        private val itemBinding = BinItemBinding.bind(view)

        fun bind(binInfo: BinInfo){
            itemBinding.apply {
                tvBinNum
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
        TODO("Not yet implemented")
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