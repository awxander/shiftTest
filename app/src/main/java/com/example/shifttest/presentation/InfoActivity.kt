package com.example.shifttest.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shifttest.data.BinInfo
import com.example.shifttest.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private fun setInfo(binInfo: BinInfo) {
        binding.apply {
            scheme.text = binInfo.scheme
            type.text = binInfo.type
            brand.text = binInfo.brand
            prepaid.text = binInfo.prepaid.toString()
            bank.text = binInfo.bank?.name
            number.text = binInfo.cardNumber?.length.toString()
            country.text = binInfo.country?.name
        }
    }


}