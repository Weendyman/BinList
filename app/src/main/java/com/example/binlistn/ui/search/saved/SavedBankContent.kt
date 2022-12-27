package com.example.binlistn.ui.search.saved

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "binData")
data class SavedBankContent(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "scheme")
    val scheme: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "bank")
    val bank: String,
    @ColumnInfo(name = "url")
    val url: String
)
