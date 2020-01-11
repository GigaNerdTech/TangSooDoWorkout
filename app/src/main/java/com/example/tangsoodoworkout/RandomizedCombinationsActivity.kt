package com.example.tangsoodoworkout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_randomized_combinations.*

class RandomizedCombinationsActivity :
    AppCompatActivity() {

    var handYes = false
    var kickYes = false
    var blockYes = false
    var numberOfTechs = 5
    var numberOfCombos = 10
    var numberOfRepeats = 3
    var techSelected = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_randomized_combinations)
        val supportActionBar = supportActionBar
        supportActionBar?.hide()




        randomHandCombosSwitch.setOnClickListener {

            handYes = randomHandCombosSwitch.isChecked

        }
        randomKickCombosSwitch.setOnClickListener {
            kickYes = randomKickCombosSwitch.isChecked
        }
        randomBlockCombosSwitch.setOnClickListener {
            blockYes = randomBlockCombosSwitch.isChecked
        }


        beginRandomCombosButton.setOnClickListener {
            if (!handYes && !kickYes && !blockYes) {
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "No technique type selected!",
                        Toast.LENGTH_SHORT
                    )
                }
                return@setOnClickListener
            } else {
                techSelected = true
            }
            numberOfTechs = (numberTechniquesEditText.text).toString().toInt()
            if (numberOfTechs == null || numberOfTechs < 1) {
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "Number of techniques not set!",
                        Toast.LENGTH_SHORT
                    )
                }
                return@setOnClickListener
            }

            numberOfCombos = (numberCombosEditText.text).toString().toInt()
            if (numberOfCombos == null || numberOfCombos < 1) {
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "Number of combinations not set!",
                        Toast.LENGTH_SHORT
                    )
                }
                return@setOnClickListener
            }
            numberOfRepeats = (numberRepetitionsEditText.text).toString().toInt()
            if (numberOfRepeats == null || numberOfRepeats < 1) {
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "Number of repetitions not set!",
                        Toast.LENGTH_SHORT
                    )
                }
                return@setOnClickListener
            }
            if (numberOfTechs > 1 && numberOfCombos > 1 && numberOfRepeats < 1 && techSelected) {


                val intent = Intent(this, RunningWorkoutActivity::class.java)
                intent.putExtra("handYes", handYes)
                intent.putExtra("kickYes", kickYes)
                intent.putExtra("blockYes", blockYes)
                intent.putExtra("numberOfCombos", numberOfCombos)
                intent.putExtra("numberOfRepeats", numberOfRepeats)
                intent.putExtra("numberOfTechs", numberOfTechs)
                intent.putExtra("CURRENT_WORKOUT", "RANDOM_COMBOS")
                startActivity(intent)
            }
        }


    }


    public override fun onStart() {
        super.onStart()

    }
}