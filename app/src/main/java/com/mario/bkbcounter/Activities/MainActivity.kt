package com.mario.bkbcounter.Activities

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mario.bkbcounter.Fragment.BkBGameListFragment
import com.mario.bkbcounter.Database.Entities.BkBMatch
import com.mario.bkbcounter.Fragment.BkBResultsFragment
import com.mario.bkbcounter.R
import com.mario.bkbcounter.ViewModel.BkBMatchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BkBGameListFragment.clickListener {

    private val addActivityRequestCode = 1

    lateinit var viewModel: BkBMatchViewModel

    private lateinit var fragment: BkBGameListFragment

    private lateinit var fragmentContent: BkBResultsFragment

    private lateinit var bkBMatches: List<BkBMatch>

    override fun itemPortraitClick(bkBMatch: BkBMatch) {
        startActivity(
            Intent(this, InfoGameActivity::class.java)
                .putExtra(AddTeams.DATE, bkBMatch.date)
                .putExtra(AddTeams.POINTSTEAMA, bkBMatch.scoreTeamA)
                .putExtra(AddTeams.POINTSTEAMB, bkBMatch.scoreTeamB)
                .putExtra(AddTeams.EXTRA_TEAMA, bkBMatch.teamA)
                .putExtra(AddTeams.EXTRA_TEAMB, bkBMatch.teamB)
                .putExtra(AddTeams.END, bkBMatch.timeE)
                .putExtra(AddTeams.BEGIN, bkBMatch.timeI)
        )
    }

    override fun itemLandscapeClick(bkBMatch: BkBMatch) {
        fragmentContent = BkBResultsFragment.newInstance(bkBMatch)
        changeFragment(R.id.fragment_info_content, fragmentContent)
    }

    override fun delete(bkBMatch: BkBMatch) {
        viewModel.delete(bkBMatch.id)
        Toast.makeText(this, "the bkBMatch has been removed", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initFragment()
        viewModel = ViewModelProviders.of(this).get(BkBMatchViewModel::class.java)
        viewModel.getAllGames().observe(this, Observer {
            if (it != null) {
                this.bkBMatches = it
                fragment.updateAdapter(it)
            }
        })

        add_game.setOnClickListener {
            val intent = Intent(this@MainActivity, AddTeams::class.java)
            startActivityForResult(intent, addActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == addActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val game = BkBMatch(
                    data.getStringExtra(AddTeams.EXTRA_TEAMA),
                    data.getStringExtra(AddTeams.EXTRA_TEAMB),
                    data.getStringExtra(AddTeams.POINTSTEAMA).toInt(),
                    data.getStringExtra(AddTeams.POINTSTEAMB).toInt(),
                    data.getStringExtra(AddTeams.DATE),
                    data.getStringExtra(AddTeams.BEGIN),
                    data.getStringExtra(AddTeams.END)
                )
                viewModel.insertGame(game)
            }
        } else {
            Toast.makeText(applicationContext, "", Toast.LENGTH_LONG).show()
        }
    }

    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }

    private fun initFragment() {
        fragment = BkBGameListFragment.newInstance()
        var localitation =
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragmentContent = BkBResultsFragment.newInstance(BkBMatch("", "", 0, 0, "", "", ""))
                changeFragment(R.id.fragment_info_content, fragmentContent)

                R.id.fragment_content_land
            } else {
                R.id.fragment_content
            }
        changeFragment(localitation, fragment)
    }
}
