package com.dev_akash.catassignment.feature_catshow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dev_akash.catassignment.feature_catshow.data.model.CatsDto
import com.dev_akash.catassignment.feature_catshow.data.model.RemoteKeys


@Database(entities = [CatsDto::class, RemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvertors::class)
abstract class CatAppDB : RoomDatabase() {

    abstract fun getCatsDao(): CatsDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}