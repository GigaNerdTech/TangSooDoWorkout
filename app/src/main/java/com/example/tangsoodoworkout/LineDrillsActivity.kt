package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_line_drills.*


class LineDrillsActivity : AppCompatActivity() {


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_drills)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        handLineDrillsButton.setOnClickListener {
            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "HAND_LINE_DRILLS")
            startActivity(intent)
        }
        kickingLineDrillsButton.setOnClickListener {
            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "KICK_LINE_DRILLS")
            startActivity(intent)
        }
        blockLineDrillsButton.setOnClickListener {

            val intent = Intent(this, RunningWorkoutActivity::class.java)
            intent.putExtra("CURRENT_WORKOUT", "BLOCK_LINE_DRILLS")
            startActivity(intent)
        }

    }


    public override fun onStart() {
        super.onStart()

    }
}
