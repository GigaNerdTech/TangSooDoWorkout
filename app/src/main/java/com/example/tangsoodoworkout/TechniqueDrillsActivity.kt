package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_technique_drills.*

class TechniqueDrillsActivity :
    AppCompatActivity() {

    /* access modifiers changed from: protected */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technique_drills)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        handTechsButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "HAND_LINE_DRILLS")
            startActivity(intent)
        }
        kickTechsButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "KICK_LINE_DRILLS")
            startActivity(intent)
        }
        blockTechsButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "BLOCK_LINE_DRILLS")
            startActivity(intent)
        }
        stancesButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "STANCE_DRILLS")
            startActivity(intent)
        }
    }

    /* access modifiers changed from: protected */
    public override fun onStart() {
        super.onStart()

    }
}
