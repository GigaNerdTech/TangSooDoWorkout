package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tangsoodoworkout.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    //    val baseURL = "https://texttospeech.googleapis.com/v1/text:synthesize"
    var globalSwitchPref = false
    var partialSwitchPref = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        setSupportActionBar(toolbar)


        //       PreferenceManager.setDefaultValues(applicationContext, preferences, false)
        //       var sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        //       var switchPref: Boolean = sharedPref.getBoolean("Language", false)

        //       var partialPref: Boolean = sharedPref.getBoolean("MostlyKorean", false)


        preProgrammedWorkoutsButton.setOnClickListener {
            val intent = Intent(this, PreProgrammedWorkoutActivity::class.java)
            startActivity(intent)
        }
        flashcardsButton.setOnClickListener {
            val intent = Intent(this, FlashcardsActivity::class.java)
            startActivity(intent)
        }
        randomizedWorkoutsButton.setOnClickListener {
            val intent = Intent(this, RandomizedWorkoutActivity::class.java)
            startActivity(intent)
        }
        customWorkoutButton.setOnClickListener {
            val intent = Intent(this, TimedWorkoutActivity::class.java)
            startActivity(intent)
        }


    }


}

