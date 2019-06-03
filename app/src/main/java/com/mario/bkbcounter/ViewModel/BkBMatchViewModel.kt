package com.mario.bkbcounter.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mario.bkbcounter.Database.Entities.BkBMatch
import com.mario.bkbcounter.Repository.BkBMatchRepository
import com.mario.bkbcounter.Database.RoomDataBase.BkBMatchRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BkBMatchViewModel(application: Application) : AndroidViewModel(application) {

    private val bkBMatchRepository: BkBMatchRepository

    init {
        val gameDAO = BkBMatchRoomDatabase.getInstance(application).gameDao()
        bkBMatchRepository = BkBMatchRepository(gameDAO)
    }

    fun insertGame(bkBMatch: BkBMatch) = viewModelScope.launch(Dispatchers.IO) {
        bkBMatchRepository.insertGame(bkBMatch)
    }

    fun getAllGames(): LiveData<List<BkBMatch>> = bkBMatchRepository.getAllGames()

    fun delete(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        bkBMatchRepository.delete(id)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        bkBMatchRepository.deleteAll()
    }
}