package com.example.tangsoodoworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tangsoodoworkout.R.layout.activity_running_workout
import kotlinx.android.synthetic.main.activity_running_workout.*

class RunningWorkoutActivity : AppCompatActivity() {

    var CURRENT_WORKOUT = "NONE"

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_running_workout)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        var workout = tangSooDoWorkoutClass(this, this)
        var worker = speakThread()
        intent = getIntent()
        CURRENT_WORKOUT = intent.getStringExtra("CURRENT_WORKOUT")

        startRunningWorkoutButton.setOnClickListener {
            if (CURRENT_WORKOUT == "FULL_WORKOUT") {
                worker.start(workout::workOut)
            } else if (CURRENT_WORKOUT == "WARMUP") {
                worker.start(workout::totalWarmup)
            } else if (CURRENT_WORKOUT == "HAND_LINE_DRILLS") {
                worker.start(workout::handLineDrills)
            } else if (CURRENT_WORKOUT == "KICK_LINE_DRILLS") {
                worker.start(workout::kickLineDrills)
            } else if (CURRENT_WORKOUT == "BLOCK_LINE_DRILLS") {
                worker.start(workout::blockLineDrills)
            } else if (CURRENT_WORKOUT == "COMBOS") {
                worker.start(workout::combos)
            } else if (CURRENT_WORKOUT == "HAND_TECHS") {
                worker.start(workout::handTechniques)
            } else if (CURRENT_WORKOUT == "KICK_TECHS") {
                worker.start(workout::kickTechniques)
            } else if (CURRENT_WORKOUT == "BLOCK_TECHS") {
                worker.start(workout::blockTechniques)
            } else if (CURRENT_WORKOUT == "STANCE_DRILLS") {
                worker.start(workout::stances)
            } else if (CURRENT_WORKOUT == "RANDOM_COMBOS") {
                var handYes = intent.getBooleanExtra("handYes", false)
                var kickYes = intent.getBooleanExtra("kickYes", false)
                var blockYes = intent.getBooleanExtra("blockYes", false)
                var numberOfTechs = intent.getIntExtra("numberOfTechs", 3)
                var numberOfRepeats = intent.getIntExtra("numberOfRepeats", 3)
                var numberOfCombos = intent.getIntExtra("numberOfCombos", 5)
                worker.start {
                    (workout::randomCustomCombos)(
                        numberOfCombos,
                        numberOfTechs,
                        numberOfRepeats,
                        handYes,
                        kickYes,
                        blockYes
                    )
                }
            } else if (CURRENT_WORKOUT == "TIMED_WORKOUT") {
                var handYes = intent.getBooleanExtra("handYes", false)
                var kickYes = intent.getBooleanExtra("kickYes", false)
                var blockYes = intent.getBooleanExtra("blockYes", false)
                var handLineYes = intent.getBooleanExtra("handLineYes", false)
                var kickLineYes = intent.getBooleanExtra("kickLineYes", false)
                var blockLineYes = intent.getBooleanExtra("blockLineYes", false)
                var prePostStuff = intent.getBooleanExtra("prePostStuff", false)
                var minutesForWorkout = intent.getIntExtra("minutesForWorkout", 3)
                worker.start {
                    (workout::timedWorkout)(
                        handYes,
                        kickYes,
                        blockYes,
                        handLineYes,
                        kickLineYes,
                        blockLineYes,
                        prePostStuff,
                        minutesForWorkout
                    )
                }
            } else if (CURRENT_WORKOUT == "RANDOM_WORKOUT") {
                var handYes = intent.getBooleanExtra("handYes", false)
                var kickYes = intent.getBooleanExtra("kickYes", false)
                var blockYes = intent.getBooleanExtra("blockYes", false)
                var handLineYes = intent.getBooleanExtra("handLineYes", false)
                var kickLineYes = intent.getBooleanExtra("kickLineYes", false)
                var blockLineYes = intent.getBooleanExtra("blockLineYes", false)
                var prePostStuff = intent.getBooleanExtra("prePostStuff", false)
                var minutesForWorkout = intent.getIntExtra("minutesForWorkout", 3)
                worker.start {
                    (workout::randomWorkout)(
                        handYes,
                        kickYes,
                        blockYes,
                        handLineYes,
                        kickLineYes,
                        blockLineYes,
                        prePostStuff,
                        minutesForWorkout
                    )
                }
            } else if (CURRENT_WORKOUT == "STRETCHES") {
                worker.start { workout::stretches }
            } else if (CURRENT_WORKOUT == "AEROBIC_WARMUP") {
                worker.start(workout::warmUp)
            } else if (CURRENT_WORKOUT == "JOINT_WARMUP") {
                worker.start(workout::loosenUp)
            } else if (CURRENT_WORKOUT == "COOLDOWN") {
                worker.start(workout::coolDown)
            } else if (CURRENT_WORKOUT == "FORMS") {
                worker.start(workout::doForms)
            }

        }

        stopRunningWorkoutButton.setOnClickListener {
            workout.speak("Stopping workout!", 2)
            worker.kill()
            workout.mTTS.stop()
            CURRENT_WORKOUT = "NONE"

        }
    }


    public override fun onStart() {
        super.onStart()


    }
}