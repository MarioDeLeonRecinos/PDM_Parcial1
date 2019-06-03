package com.mario.bkbcounter.Database.RoomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mario.bkbcounter.Database.DAO.MatchDAO
import com.mario.bkbcounter.Database.Entities.BkBMatch

@Database(entities = [BkBMatch::class], version = 2, exportSchema = false)
abstract class BkBMatchRoomDatabase : RoomDatabase() {
    abstract fun gameDao(): MatchDAO

    companion object {
        @Volatile
        private var INSTANCE: BkBMatchRoomDatabase? = null

        fun getInstance(context: Context): BkBMatchRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, BkBMatchRoomDatabase::class.java, "BkBDatabase")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}