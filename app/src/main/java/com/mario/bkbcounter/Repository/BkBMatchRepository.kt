package com.mario.bkbcounter.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.mario.bkbcounter.Database.DAO.MatchDAO
import com.mario.bkbcounter.Database.Entities.BkBMatch

class BkBMatchRepository(private val matchDAO: MatchDAO) {

    @WorkerThread
    suspend fun insertGame(bkBMatch: BkBMatch) {
        matchDAO.insert(bkBMatch)
    }

    fun getAllGames(): LiveData<List<BkBMatch>> = matchDAO.getAll()

    fun deleteAll() = matchDAO.deleteAll()

    fun delete(id: Int) = matchDAO.delete(id)
}