package com.example.shifttest.data

import com.google.gson.annotations.SerializedName

data class CardNumber(
    @SerializedName("length" ) var length : Int?     = null,
    @SerializedName("luhn"   ) var luhn   : Boolean? = null
)
