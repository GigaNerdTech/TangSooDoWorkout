package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_randomized_workouts_setup.*


class RandomizedWorkoutsSetupActivity :
    AppCompatActivity() {

    var minutesForWorkout = 15
    var handYes = false
    var kickYes = false
    var blockYes = false
    var handLineYes = false
    var kickLineYes = false
    var blockLineYes = false
    var prePostStuff = false
    var techSelected = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_randomized_workouts_setup)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        timedHandRandomSwitch.setOnClickListener {
            handYes = timedHandRandomSwitch.isChecked
        }
        timedKickRandomSwitch.setOnClickListener {
            kickYes = timedKickRandomSwitch.isChecked
        }
        timedBlockRandomSwitch.setOnClickListener {
            blockYes = timedBlockRandomSwitch.isChecked
        }
        timedHandDrillsRandomSwitch.setOnClickListener {
            handLineYes = timedHandDrillsRandomSwitch.isChecked
        }
        timedKickDrillsRandomSwitch.setOnClickListener {
            kickLineYes = timedKickDrillsRandomSwitch.isChecked
        }
        timedBlockDrillsRandomSwitch.setOnClickListener {
            blockLineYes = timedBlockDrillsRandomSwitch.isChecked
        }
        includePrePostStuffRandomSwitch.setOnClickListener {
            prePostStuff = includePrePostStuffRandomSwitch.isChecked
        }

        beginRandomWorkoutButton.setOnClickListener {
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
            minutesForWorkout = (minutesforRandomWorkoutEditText.text).toString().toInt()
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
                intent.putExtra("CURRENT_WORKOUT", "RANDOM_WORKOUT")
                startActivity(intent)
            }
        }
    }

    /* access modifiers changed from: protected */
    public override fun onStart() {
        super.onStart()

    }
}