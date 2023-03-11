package com.example.shifttest.db.dao

import androidx.room.*
import com.example.shifttest.db.BinItem
import com.example.shifttest.domain.BinInfo

@Dao
interface BinItemDao{
    @Query("SELECT * FROM binItem_table")
    fun getItems(): List<BinInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(vararg item: BinItem)

    @Delete
    fun deleteItems(vararg item: BinItem)
}