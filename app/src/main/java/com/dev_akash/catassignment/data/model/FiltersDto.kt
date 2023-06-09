package com.dev_akash.catassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FiltersTable")
data class FiltersDto(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val breedName: String
)