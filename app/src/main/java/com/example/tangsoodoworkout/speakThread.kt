package com.example.tangsoodoworkout

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tangsoodoworkout.R.id.*
import com.example.tangsoodoworkout.R.layout.custom_toast

class speakThread : Runnable {

    @Volatile
    private var running = false
    private lateinit var t: Thread
    private lateinit var theWorkout: () -> (Unit)


    fun start(function: () -> (Unit)) {
        if (running) {
            return
        }
        running = true
        t = Thread(this)
        t.start()
        theWorkout = function

    }

    fun isRunning(): Boolean {
        return running

    }

    fun stop() {
        running = false
    }

    fun kill() {
        running = false
        t.interrupt()
    }

    override fun run() {
        try {
            theWorkout.invoke()
            while (running) {
                Thread.sleep(100)
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }

    }

    fun showToast(
        wordsToDisplay: String,
        callingContext: Context,
        callingActiv: AppCompatActivity
    ) {


        callingActiv.runOnUiThread {
            val inflater: LayoutInflater = callingActiv.layoutInflater
            val layout: View = inflater.inflate(
                custom_toast,
                callingActiv.findViewById(custom_toast_layout_id)
            )
            val image: ImageView = layout.findViewById(custom_toast_image)
            image.setImageResource(R.drawable.tangsoodo)
            val text: TextView = layout.findViewById(custom_toast_message)
            text.text = "$wordsToDisplay"
            val myToast = Toast(callingContext)
            myToast.setGravity(Gravity.CENTER, 0, 0)

            myToast.view = layout//setting the view of custom toast layout

            myToast.show()
        }
    }


}