package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_randomized_workout.*

class RandomizedWorkoutActivity :
    AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_randomized_workout)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()

        randomWorkoutButton.setOnClickListener {
            val intent = Intent(this, RandomizedWorkoutsSetupActivity::class.java)
            startActivity(intent)
        }
        randomCombosButton.setOnClickListener {
            val intent = Intent(this, RandomizedCombinationsActivity::class.java)
            startActivity(intent)
        }
    }

    /* access modifiers changed from: protected */
    public override fun onStart() {
        super.onStart()

    }
}
