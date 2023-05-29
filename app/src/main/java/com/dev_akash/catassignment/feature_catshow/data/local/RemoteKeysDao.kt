package com.dev_akash.catassignment.feature_catshow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev_akash.catassignment.feature_catshow.data.model.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM REMOTEKEYSTABLE WHERE id=:id")
    suspend fun getRemoteKey(id: String):RemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertKeys(remoteKeys: List<RemoteKeys>)

    @Query("DELETE FROM REMOTEKEYSTABLE")
    suspend fun clear()
}