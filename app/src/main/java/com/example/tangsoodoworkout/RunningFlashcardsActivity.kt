package com.example.tangsoodoworkout

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tangsoodoworkout.R.layout.activity_running_flashcards
import kotlinx.android.synthetic.main.activity_running_flashcards.*

class RunningFlashcardsActivity :
    AppCompatActivity() {
    val workout = tangSooDoWorkoutClass(this, this)
    val worker = speakThread()
    var CURRENT_FLASHCARDS = "NONE"

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_running_flashcards)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        startFlashcardsButton.setOnClickListener {
            intent = getIntent()
            CURRENT_FLASHCARDS = intent.getStringExtra("CURRENT_FLASHCARDS")
            if (CURRENT_FLASHCARDS == "NUMBER_FLASHCARDS") {
                worker.start(workout::numberFlashcards)
            } else if (CURRENT_FLASHCARDS == "TECHNIQUE_FLASHCARDS") {
                worker.start(workout::techniqueFlashcards)
            } else {
                runOnUiThread {
                    Toast.makeText(applicationContext, "No flashcards selected!", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        stopFlashcardsButton.setOnClickListener {
            if (worker.isRunning()) {
                workout.stopSpeaking()
                worker.kill()
                CURRENT_FLASHCARDS = "NONE"
            }

        }
    }

    /* access modifiers changed from: protected */
    public override fun onStart() {
        super.onStart()


    }
}