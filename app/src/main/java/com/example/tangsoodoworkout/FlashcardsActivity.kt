package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tangsoodoworkout.R.layout.activity_flashcards
import kotlinx.android.synthetic.main.activity_flashcards.*


class FlashcardsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_flashcards)


        numbersFlashcardButton.setOnClickListener {

            val intent = Intent(this, RunningFlashcardsActivity::class.java)
            intent.putExtra("CURRENT_FLASHCARDS", "NUMBERS_FLASHCARDS")
            startActivity(intent)
        }
        techniquesFlashcardButton.setOnClickListener {

            val intent = Intent(this, RunningFlashcardsActivity::class.java)
            intent.putExtra("CURRENT_FLASHCARDS", "TECHNIQUES_FLASHCARDS")
            startActivity(intent)
        }

    }

    /* access modifiers changed from: protected */
    override fun onStart() {
        super.onStart()


    }

}