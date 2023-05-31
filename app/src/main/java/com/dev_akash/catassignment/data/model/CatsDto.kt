package com.dev_akash.catassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CatsTable")
data class CatsDto(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val breeds: List<BreedDto>,
    val height: Int,
    val url: String,
    val width: Int
)