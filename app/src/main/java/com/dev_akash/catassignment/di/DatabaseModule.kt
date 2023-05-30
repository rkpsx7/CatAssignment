package com.dev_akash.catassignment.di

import android.app.Application
import androidx.room.Room
import com.dev_akash.catassignment.data.local.CatAppDB
import com.dev_akash.catassignment.utils.convertors.TypeConvertors
import com.dev_akash.catassignment.utils.convertors.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesWordInfoDataBase(application: Application): CatAppDB {
        return Room.databaseBuilder(
            application,
            CatAppDB::class.java,
            "CAT_APP_DB"
        )
            .addTypeConverter(TypeConvertors(GsonParser(Gson())))
            .build()
    }
}