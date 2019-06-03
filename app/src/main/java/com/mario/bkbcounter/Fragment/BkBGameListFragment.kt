package com.mario.bkbcounter.Fragment

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mario.bkbcounter.Database.Entities.BkBMatch
import com.mario.bkbcounter.Adapter.BkBMatchAdapter
import com.mario.bkbcounter.R
import kotlinx.android.synthetic.main.fragment_bkbmatch_list.view.*
import java.lang.ClassCastException

class BkBGameListFragment : Fragment() {
    private var listenerTool: clickListener? = null
    private lateinit var bkBMatchAdapter: BkBMatchAdapter

    companion object {
        @JvmStatic
        fun newInstance() =
            BkBGameListFragment().apply {

            }
    }

    interface clickListener {
        fun itemPortraitClick(bkBMatch: BkBMatch)
        fun itemLandscapeClick(bkBMatch: BkBMatch)
        fun delete(bkBMatch: BkBMatch)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_bkbmatch_list, container, false)
        initRecyclerView(resources.configuration.orientation, view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is clickListener) {
            listenerTool = context
        } else {
            throw ClassCastException("No hay interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    fun updateAdapter(bkBMatches: List<BkBMatch>) {
        this.bkBMatchAdapter.setMatch(bkBMatches)
    }

    private fun initRecyclerView(orientation: Int, container: View) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bkBMatchAdapter =
                BkBMatchAdapter(
                    { bkBMatch: BkBMatch -> listenerTool?.itemLandscapeClick(bkBMatch) },
                    { bkBMatch: BkBMatch -> listenerTool?.delete(bkBMatch) })
            container.rv_games_list.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this.context, 1)
                adapter = bkBMatchAdapter
            }
        }
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            bkBMatchAdapter =
                BkBMatchAdapter(
                    { bkBMatch: BkBMatch -> listenerTool?.itemPortraitClick(bkBMatch) },
                    { bkBMatch: BkBMatch -> listenerTool?.delete(bkBMatch) })
            container.rv_games_list.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this.context, 2)
                adapter = bkBMatchAdapter
            }
        }
    }
}
