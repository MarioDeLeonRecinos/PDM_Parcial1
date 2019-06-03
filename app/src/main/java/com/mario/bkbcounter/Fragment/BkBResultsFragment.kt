package com.mario.bkbcounter.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mario.bkbcounter.Database.Entities.BkBMatch
import com.mario.bkbcounter.R
import kotlinx.android.synthetic.main.fragment_main_content.view.*

class BkBResultsFragment : Fragment() {

    var bkBMatch: BkBMatch? = null

    companion object {
        fun newInstance(games: BkBMatch): BkBResultsFragment {
            val newFragment = BkBResultsFragment()
            newFragment.bkBMatch = games
            return newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main_content, container, false)
        if (bkBMatch != null) {
            bindData(view, bkBMatch!!)
        }
        return view
    }

    fun bindData(view: View, bkBMatch: BkBMatch) {

        view.tv_team_a_name.text = bkBMatch.teamA
        view.tv_team_b_name.text = bkBMatch.teamB
        view.tv_final_score_team_a.text = ""
        view.tv_final_score_team_b.text = ""
        view.tv_date.text = bkBMatch.date
        view.tv_beginTime.text = bkBMatch.timeI
        view.tv_endTime_fragment.text = bkBMatch.timeE

        if (bkBMatch.teamA.isNotEmpty()) {
            view.text_Winner.text = "WINNER"
            view.tv_final_score_team_a.text = bkBMatch.scoreTeamA.toString()
            view.tv_final_score_team_b.text = bkBMatch.scoreTeamB.toString()
            view.date_fragment.text = "Date: "
            view.end_fragment.text = "End: "
            view.begin_fragment.text = "Begin: "
            view.Points.text = "POINTS"
        }

        if (bkBMatch.scoreTeamA > bkBMatch.scoreTeamB) view.winner_team_fragment.text = bkBMatch.teamA
        else view.winner_team_fragment.text = bkBMatch.teamB
    }
}