package com.mario.bkbcounter.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mario.bkbcounter.R
import com.mario.bkbcounter.ScoreViewModel
import com.mario.bkbcounter.databinding.ActivityMatchCounterBinding
import kotlinx.android.synthetic.main.activity_match_counter.*
import java.util.*

class CounterBkBActivity : AppCompatActivity() {

    lateinit var viewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)

        var binding: ActivityMatchCounterBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_match_counter
        )

        binding.team = viewModel

        val teamA = intent.extras.getString(AddTeams.EXTRA_TEAMA)
        val teamB = intent.extras.getString(AddTeams.EXTRA_TEAMB)
        val begin = intent.extras.getString(AddTeams.BEGIN)
        val date = intent.extras.getString(AddTeams.DATE)

        team_A.text = teamA
        team_B.text = teamB

        bt_team_a_2_p.setOnClickListener {
            viewModel.scoreTeamA = (viewModel.scoreTeamA.toInt() + 2).toString()
            binding.team = viewModel
        }

        bt_team_a_3_p.setOnClickListener {
            viewModel.scoreTeamA = (viewModel.scoreTeamA.toInt() + 3).toString()
            binding.team = viewModel
        }

        bt_team_b_2_p.setOnClickListener {
            viewModel.scoreTeamB = (viewModel.scoreTeamB.toInt() + 2).toString()
            binding.team = viewModel
        }

        bt_team_b_3_p.setOnClickListener {
            viewModel.scoreTeamB = (viewModel.scoreTeamB.toInt() + 3).toString()
            binding.team = viewModel
        }

        bt_team_a_free_throw.setOnClickListener {
            viewModel.scoreTeamA = (viewModel.scoreTeamA.toInt() + 1).toString()
            binding.team = viewModel
        }

        bt_team_b_free_throw.setOnClickListener {
            viewModel.scoreTeamB = (viewModel.scoreTeamB.toInt() + 1).toString()
            binding.team = viewModel
        }

        bt_reset.setOnClickListener {
            viewModel.scoreTeamA = "0"
            viewModel.scoreTeamB = "0"
            binding.team = viewModel
        }

        bt_save.setOnClickListener {
            val replyIntent = Intent()
            val cal = Calendar.getInstance()

            var timeEnd = cal.get(Calendar.HOUR).toString() + ":" + cal.get(Calendar.MINUTE).toString()

            replyIntent.putExtra(AddTeams.DATE, date)
            replyIntent.putExtra(AddTeams.BEGIN, begin)
            replyIntent.putExtra(AddTeams.POINTSTEAMA, viewModel.scoreTeamA)
            replyIntent.putExtra(AddTeams.POINTSTEAMB, viewModel.scoreTeamB)
            replyIntent.putExtra(AddTeams.EXTRA_TEAMA, teamA)
            replyIntent.putExtra(AddTeams.EXTRA_TEAMB, teamB)
            replyIntent.putExtra(AddTeams.END, timeEnd)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }

}
