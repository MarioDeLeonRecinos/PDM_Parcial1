package com.mario.bkbcounter.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mario.bkbcounter.Database.Entities.BkBMatch

@Dao
interface MatchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bkBMatch: BkBMatch)

    @Query("SELECT * FROM BkBmatch")
    fun getAll(): LiveData<List<BkBMatch>>

    @Query("DELETE FROM BkBmatch")
    fun deleteAll()

    @Query("DELETE FROM BkBmatch WHERE id = :id")
    fun delete(id: Int)
}