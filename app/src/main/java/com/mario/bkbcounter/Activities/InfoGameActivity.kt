package com.mario.bkbcounter.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mario.bkbcounter.R
import kotlinx.android.synthetic.main.activity_game_info.*

class InfoGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game_info)

        val teamA = intent.extras.getString(AddTeams.EXTRA_TEAMA)
        val teamB = intent.extras.getString(AddTeams.EXTRA_TEAMB)
        val begin = intent.extras.getString(AddTeams.BEGIN)
        val date = intent.extras.getString(AddTeams.DATE)
        val end = intent.extras.getString(AddTeams.END)
        val teamAPoints = intent.extras.getInt(AddTeams.POINTSTEAMA)
        val teamBPoints = intent.extras.getInt(AddTeams.POINTSTEAMB)

        tv_team_a_name.text = teamA
        tv_team_b_name.text = teamB
        tv_final_score_team_a.text = teamAPoints.toString()
        tv_final_score_team_b.text = teamBPoints.toString()
        tv_date.text = date
        tv_beginTime.text = begin
        tv_endTime.text = end

        if (teamAPoints > teamBPoints) {
            winner.text = teamA
        } else {
            winner.text = teamB
        }
    }

}
