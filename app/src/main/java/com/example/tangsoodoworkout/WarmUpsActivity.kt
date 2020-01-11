package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tangsoodoworkout.R.layout.activity_warm_ups
import kotlinx.android.synthetic.main.activity_warm_ups.*


class WarmUpsActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_warm_ups)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        bowInButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "BOW_IN")
            startActivity(intent)
        }
        aerobicWarmupButton.setOnClickListener {

            val intent = Intent(this, RunningFlashcardsActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "AEROBIC_WARMUP")
            startActivity(intent)
        }
        stretchesButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "STRETCHES")
            startActivity(intent)
        }
        jointWarmUpButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "JOINT_WARMUP")
            startActivity(intent)

        }

    }

    public override fun onStart() {
        super.onStart()

    }
}
