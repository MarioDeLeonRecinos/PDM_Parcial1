package com.mario.bkbcounter.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mario.bkbcounter.Database.Entities.BkBMatch
import com.mario.bkbcounter.R
import kotlinx.android.synthetic.main.fragment_cardview_list.view.*

class BkBMatchAdapter(
    private val clickListener: (BkBMatch) -> Unit,
    private val deleteGame: (BkBMatch) -> Unit
) : RecyclerView.Adapter<BkBMatchAdapter.ViewHolder>() {

    private var list = emptyList<BkBMatch>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: BkBMatch, clickListener: (BkBMatch) -> Unit, deleteGame: (BkBMatch) -> Unit) = with(itemView) {
            team_one.text = item.teamA
            team_two.text = item.teamB
            teamA_Score.text = item.teamA+": "+ item.scoreTeamA.toString()
            teamB_Score.text = item.teamB+": "+item.scoreTeamB.toString()
            btn_delete.setOnClickListener { deleteGame(item) }
            this.setOnClickListener { clickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_cardview_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position], clickListener, deleteGame)

    fun setMatch(bkBMatches: List<BkBMatch>) {
        this.list = bkBMatches
        notifyDataSetChanged()
    }
}
