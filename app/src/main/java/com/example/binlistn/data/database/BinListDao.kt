package com.example.binlistn.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.binlistn.ui.search.saved.SavedBankContent

@Dao
interface BinListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedBankContent: SavedBankContent): Long

    @Query("SELECT * FROM binData")
    suspend fun getAllBinHistory(): List<SavedBankContent>
}