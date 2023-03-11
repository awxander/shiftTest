package com.example.shifttest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shifttest.db.dao.BinItemDao

@Database(entities = [BinItem::class], version = 1)
abstract class BinItemDatabase : RoomDatabase() {
    abstract fun myItemDao(): BinItemDao
}