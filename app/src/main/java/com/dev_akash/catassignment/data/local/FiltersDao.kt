package com.dev_akash.catassignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev_akash.catassignment.data.model.FiltersDto

@Dao
interface FiltersDao {

    @Query("SELECT * FROM FILTERSTABLE")
    fun getFilters(): LiveData<List<FiltersDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFilters(filters:List<FiltersDto>)

    @Query("DELETE FROM FILTERSTABLE")
    suspend fun clear()
}