package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_timed_workout.*

class TimedWorkoutActivity : AppCompatActivity() {

    var prePostStuff = false
    var handYes = false
    var kickYes = false
    var blockYes = false
    var handLineYes = false
    var kickLineYes = false
    var blockLineYes = false
    var minutesForWorkout = 15
    var techSelected = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timed_workout)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        includePrePostStuffSwitch.setOnClickListener {
            prePostStuff = includePrePostStuffSwitch.isChecked
        }
        timedHandDrillsSwitch.setOnClickListener {
            handLineYes = timedHandDrillsSwitch.isChecked
        }
        timedKickDrillsSwitch.setOnClickListener {
            kickLineYes = timedKickDrillsSwitch.isChecked
        }
        timedBlockDrillsSwitch.setOnClickListener {
            blockLineYes = timedBlockDrillsSwitch.isChecked
        }
        timedHandSwitch.setOnClickListener {
            handYes = timedHandSwitch.isChecked
        }
        timedKickSwitch.setOnClickListener {
            kickYes = timedKickSwitch.isChecked
        }
        timedBlockSwitch.setOnClickListener {
            blockYes = timedBlockSwitch.isChecked
        }
        beginTimedWorkoutButton.setOnClickListener {
            if (!handYes && !kickYes && !blockYes && !handLineYes && !kickLineYes && !blockLineYes) {
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "No technique type selected!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return@setOnClickListener
            } else {
                techSelected = true
            }
            minutesForWorkout = (minutesforWorkoutEditText.text).toString().toInt()
            if (minutesForWorkout == null || minutesForWorkout < 1) {
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "Minutes for workout not set!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return@setOnClickListener
            }

            if (minutesForWorkout > 1 && techSelected) {


                val intent = Intent(this, RunningWorkoutActivity::class.java)
                intent.putExtra("handYes", handYes)
                intent.putExtra("kickYes", kickYes)
                intent.putExtra("blockYes", blockYes)
                intent.putExtra("handLineYes", handLineYes)
                intent.putExtra("kickLineYes", kickLineYes)
                intent.putExtra("blockLineYes", blockLineYes)
                intent.putExtra("minutesForWorkout", minutesForWorkout)
                intent.putExtra("prePostStuff", prePostStuff)
                intent.putExtra("CURRENT_WORKOUT", "TIMED_WORKOUT")
                startActivity(intent)
            }
        }

    }


    /* access modifiers changed from: protected */
    public override fun onStart() {
        super.onStart()

    }
}
