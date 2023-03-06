package com.example.shifttest.data

import com.google.gson.annotations.SerializedName

data class BinInfo(
    @SerializedName("scheme") var scheme: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("prepaid") var prepaid: Boolean? = null,
    @SerializedName("bank") var bank: Bank? = null,
    @SerializedName("number") var cardNumber: CardNumber? = null,
    @SerializedName("country") var country: Country? = null,
)
