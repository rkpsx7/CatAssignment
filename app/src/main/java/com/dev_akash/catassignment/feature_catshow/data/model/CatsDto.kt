package com.dev_akash.catassignment.feature_catshow.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CatsTable")
data class CatsDto(
    @PrimaryKey val id: String,
    val breeds: List<BreedDto>,
    val height: Int,
    val url: String,
    val width: Int
)