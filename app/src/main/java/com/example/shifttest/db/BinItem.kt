package com.example.shifttest.db

import androidx.room.Entity
import androidx.room.PrimaryKey


data class BinItem(
    val binNum : Long,
    val searchDate : String
)
