package com.mario.bkbcounter.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "BkBmatch")
data class BkBMatch(
    val teamA: String,
    val teamB: String,
    val scoreTeamA: Int,
    val scoreTeamB: Int,
    val date: String,
    val timeI: String,
    val timeE: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}