package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_partial_workouts.*


class PartialWorkoutsActivity : AppCompatActivity() {


    /* access modifiers changed from: protected */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partial_workouts)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        warmupsButton.setOnClickListener {
            val intent = Intent(this, WarmUpsActivity::class.java)
            startActivity(intent)
        }
        lineDrillsButton.setOnClickListener {
            val intent = Intent(this, LineDrillsActivity::class.java)
            startActivity(intent)
        }
        techsButton.setOnClickListener {

            val intent = Intent(this, TechniqueDrillsActivity::class.java)
            startActivity(intent)
        }
        formsButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "FORMS")
            startActivity(intent)
        }
        coolDownButton.setOnClickListener {

            intent.putExtra("CURRENT_WORKOUT", "COOLDOWN")
            val intent = Intent(this, RunningWorkoutActivity::class.java)
            startActivity(intent)
        }
    }

    /* access modifiers changed from: protected */
    public override fun onStart() {
        super.onStart()

    }
}
