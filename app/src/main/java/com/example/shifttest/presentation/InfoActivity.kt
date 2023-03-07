package com.example.shifttest.presentation

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shifttest.BIN_INFO
import com.example.shifttest.data.BinInfo
import com.example.shifttest.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val binInfo = intent.parcelable<BinInfo>(BIN_INFO)
        if (binInfo != null) {
            setInfo(binInfo)
        }
    }

    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    private fun setInfo(binInfo: BinInfo) {
        binding.apply {
            scheme.text = "scheme: " + ifNotMentioned(binInfo.scheme)
            type.text = "type: " + ifNotMentioned(binInfo.type)
            brand.text = "brand: " + ifNotMentioned(binInfo.brand)
            prepaid.text = "prepaid: " + ifNotMentioned(binInfo.prepaid.toString())
            bank.text = "bank: " + ifNotMentioned(binInfo.bank?.name)
            number.text = "card number: " + ifNotMentioned(binInfo.cardNumber?.length.toString())
            country.text = "country: " + ifNotMentioned(binInfo.country?.name)
        }
    }

    private fun ifNotMentioned(str: String?): String = when (str) {
        "null",
        null -> "not mentioned"
        else -> str
    }


}