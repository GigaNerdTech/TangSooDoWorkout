package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pre_programmed_workout.*

class PreProgrammedWorkoutActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_programmed_workout)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        fullWorkoutButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "FULL_WORKOUT")
            startActivity(intent)
        }

        partialWorkoutButton.setOnClickListener {

            val intent = Intent(this, PartialWorkoutsActivity::class.java)
            startActivity(intent)
        }

        combosPreButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "COMBOS")
            startActivity(intent)
        }

    }


    public override fun onStart() {
        super.onStart()

    }
}
