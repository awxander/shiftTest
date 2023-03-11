package com.example.shifttest

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.example.shifttest.databinding.ActivityInfoBinding
import com.example.shifttest.domain.BinInfo

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
            scheme.text = "scheme: ".plus(ifNotMentioned(binInfo.scheme))
            type.text = "type: " + ifNotMentioned(binInfo.type)
            brand.text = "brand: " + ifNotMentioned(binInfo.brand)
            prepaid.text = "prepaid: " + ifNotMentioned(binInfo.prepaid.toString())
            bank.text = "bank: " + ifNotMentioned(binInfo.bankModel?.name)
            number.text = "card number len: " + ifNotMentioned(binInfo.cardNumber?.length.toString())
            country.text = "country: " + ifNotMentioned(binInfo.countryModel?.name)
        }
    }

    private fun ifNotMentioned(str: String?): String = when (str) {
        "null",
        null -> "not mentioned"
        else -> str
    }


}