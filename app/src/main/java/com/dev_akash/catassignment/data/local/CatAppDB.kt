package com.dev_akash.catassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dev_akash.catassignment.data.model.CatsDto
import com.dev_akash.catassignment.data.model.FiltersDto
import com.dev_akash.catassignment.data.model.RemoteKeys
import com.dev_akash.catassignment.utils.convertors.TypeConvertors


@Database(entities = [CatsDto::class, RemoteKeys::class, FiltersDto::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvertors::class)
abstract class CatAppDB : RoomDatabase() {

    abstract fun getCatsDao(): CatsDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
    abstract fun getFiltersDao(): FiltersDao
}