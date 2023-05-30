package com.dev_akash.catassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeysTable")
data class RemoteKeys(
    @PrimaryKey val id: String,
    val prevPage: Int? = null,
    val nextPage: Int? = null
)