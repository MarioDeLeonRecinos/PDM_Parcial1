package com.mario.bkbcounter.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mario.bkbcounter.R
import kotlinx.android.synthetic.main.activity_add_teams.*
import java.util.*

class AddTeams : AppCompatActivity() {

    private val addActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_teams)

        btn_begin.setOnClickListener {
            if (name_team_a.text.isEmpty() || name_team_b.text.isEmpty()) {
                Toast.makeText(this, "Por favor introduzca el nombre de los equipos", Toast.LENGTH_LONG).show()
            } else {
                val cal = Calendar.getInstance()
                startActivityForResult(
                    Intent(this, CounterBkBActivity::class.java)
                        .putExtra(EXTRA_TEAMA, name_team_a.text.toString())
                        .putExtra(EXTRA_TEAMB, name_team_b.text.toString())
                        .putExtra(
                            DATE,
                            cal.get(Calendar.DAY_OF_MONTH).toString() + "/" + cal.get(Calendar.MONTH).toString() + "/" + cal.get(
                                Calendar.YEAR
                            ).toString()
                        )
                        .putExtra(
                            BEGIN,
                            cal.get(Calendar.HOUR).toString() + ":" + cal.get(Calendar.MINUTE).toString()
                        )
                    , addActivityRequestCode
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {

        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == addActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val replyIntent = Intent()
                replyIntent.putExtra(EXTRA_TEAMA, data.getStringExtra(EXTRA_TEAMA))
                replyIntent.putExtra(EXTRA_TEAMB, data.getStringExtra(EXTRA_TEAMB))
                replyIntent.putExtra(POINTSTEAMA, data.getStringExtra(POINTSTEAMA))
                replyIntent.putExtra(POINTSTEAMB, data.getStringExtra(POINTSTEAMB))
                replyIntent.putExtra(DATE, data.getStringExtra(DATE))
                replyIntent.putExtra(BEGIN, data.getStringExtra(BEGIN))
                replyIntent.putExtra(END, data.getStringExtra(END))
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        } else {
            Toast.makeText(applicationContext, "Se produjo un error", Toast.LENGTH_LONG).show()
        }

    }


    companion object {

        const val EXTRA_TEAMA = "GAME.TEAM_A"
        const val EXTRA_TEAMB = "GAME.TEAM_B"
        const val POINTSTEAMB = "GAME.POINTS_TEAM_B"
        const val POINTSTEAMA = "GAME.POINTS_TEAM_A"
        const val DATE = "GAME.DATE"
        const val BEGIN = "GAME.TIME_END"
        const val END = "GAME.TIME_BEGIN"

    }

}
