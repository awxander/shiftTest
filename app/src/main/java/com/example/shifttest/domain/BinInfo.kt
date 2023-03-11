package com.example.shifttest.domain

import android.os.Parcelable
import androidx.room.Entity
import com.example.shifttest.data.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class BinInfo(
    var binNum : Long,
    var scheme: String? = null,
    var type: String? = null,
    var brand: String? = null,
    var prepaid: Boolean? = null,
    var bankModel: BankModel? = null,
    var cardNumber: CardNumberModel? = null,
    var countryModel: CountryModel? = null,
) : Parcelable
{
    constructor(
        binNum: Long,
        binInfoModel: BinInfoModel
    ) : this(
        binNum,
        binInfoModel.scheme,
        binInfoModel.type,
        binInfoModel.brand,
        binInfoModel.prepaid,
        binInfoModel.bankModel,
        binInfoModel.cardNumber,
        binInfoModel.countryModel,
    )
}