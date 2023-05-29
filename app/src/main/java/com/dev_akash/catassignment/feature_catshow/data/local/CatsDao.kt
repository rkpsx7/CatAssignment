package com.dev_akash.catassignment.feature_catshow.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.dev_akash.catassignment.feature_catshow.data.model.CatsDto

@Dao
interface CatsDao {


    @Query("SELECT * FROM CATSTABLE")
    fun getCatsList(): PagingSource<Int, CatsDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCats(cats: List<CatsDto>)

    @Query("DELETE FROM CatsTable")
    suspend fun clear()

}
