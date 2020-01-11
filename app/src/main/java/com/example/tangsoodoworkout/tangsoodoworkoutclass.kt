package com.example.tangsoodoworkout


import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.Locale.KOREAN
import java.util.Locale.US
import java.util.concurrent.TimeUnit


class tangSooDoWorkoutClass(callingContext1: Context, callingActiv1: AppCompatActivity) :
    AppCompatActivity() {


    private val blockTechs = arrayOf(
        "low block",
        "middle block",
        "high block",
        "low knife hand block",
        "middle knife hand block",
        "high knife hand block",
        "low X block",
        "middle X block",
        "high X block"
    )

    var worker = speakThread()
    var englishInstructor: Voice
    var a = mutableSetOf<String>()
    var b = mutableSetOf<String>()
    //Text To Speech
    lateinit var mTTS: TextToSpeech
    lateinit var callingContext: Context
    var koreanInstructor: Voice
    var globalSwitchPref: Boolean = false
    var partialSwitchPref = false
    lateinit var callingActiv: AppCompatActivity

    init {
        callingContext = callingContext1
        callingActiv = callingActiv1
        this.a.add("male")
        this.b.add("male")
        this.englishInstructor = Voice(
            "en-us-x-sfg#male_3-local",
            US, 500, Voice.LATENCY_VERY_LOW, true, a
        )
        this.koreanInstructor = Voice(
            "ko-kr-x-ism#male_1-local",
            KOREAN, 500, Voice.LATENCY_VERY_LOW, true, b
        )
        this.mTTS = TextToSpeech(callingContext, TextToSpeech.OnInitListener {
            if (it != TextToSpeech.ERROR) {
                this.mTTS.setVoice(englishInstructor)
                this.mTTS.setSpeechRate(1.4F)

            }


        })


    }


    private val englishToKoreanTechs = hashMapOf(
        "middle punch" to "choong dan kong kyuk",
        "low punch" to "ha dan kong kyuk",
        "high punch" to "sang dan kong kyuk",
        "karate chop" to "yuk soo do",
        "spinning back fist" to "dwee tollyo kwap kan",
        "hammer fist" to "kwan do",
        "reverse karate chop" to "bandae yuk soo do",
        "uppercut" to "tuck chi ki",
        "jab" to "chi ru ki",
        "reverse punch" to "bandae chi ru ki",
        "spear hand" to "kwan soo kong kyuck",
        "low block" to "ha dan mahk ki",
        "middle block" to "choong dan mahk ki",
        "high block" to "sang dan mahk ki",
        "low knife hand block" to "ha dan soo do mahk ki",
        "middle knife hand block" to "choong dan soo do mahk ki",
        "high knife hand block" to "sang dan soo do mahk ki",
        "low X block" to "ssang soo ha dan mahk ki",
        "high X block" to "ssang soo sang dan mahk ki",
        "middle X block" to "ssang soo choong dan mahk ki",
        "front snapping kick" to "ahp podo cha ki",
        "front push kick" to "ahp mee ro cha ki",
        "tornado kick" to "dwee hu ryo cha ki",
        "inside outside crescent kick" to "ahnes phaku ro cha ki",
        "outside inside crescent kick" to "phakeso ahnu ro cha ki",
        "spinning back kick" to "dwee tollyo cha ki",
        "side kick" to "yup cha ki",
        "stepping side kick" to "bahl yup chit pal ki",
        "roundhouse kick" to "tollyo cha ki",
        "low" to "ha dan",
        "middle" to "choong dan",
        "high" to "sang dan"
    )

    private val translateTable = arrayOf(
        "zero",
        "hana",
        "tul",
        "set",
        "net",
        "tasot",
        "yasot",
        "il-gop",
        "yo-dol",
        "a-hop",
        "yol",
        "yorhana",
        "yoldul",
        "yolset",
        "yollet",
        "yoldasot",
        "yolyasot",
        "yolilgop",
        "yolyodol",
        "yoahop",
        "sumul",
        "sumul hana",
        "sumul tul",
        "sumul set",
        "sumul net",
        "sumul tasot",
        "sumul yasot",
        "sumul il-gop",
        "sumul yo-dol",
        "sumul a-hop",
        "sorun",
        "sorun hana",
        "sorun tul",
        "sorun set",
        "sorun net",
        "sorun tasot",
        "sorun yasot",
        "sorun il-gop",
        "sorun yo-dol",
        "sorun a-hop",
        "mahun",
        "mahun hana",
        "mahun tul",
        "mahun set",
        "mahun net",
        "mahun tasot",
        "mahun yasot",
        "mahun il-gop",
        "mahun yo-dol",
        "mahun a-hop",
        "shwin",
        "shwin hana",
        "shwin tul",
        "shwin set",
        "shwin net",
        "shwin tasot",
        "shwin yasot",
        "shwin il-gop",
        "shwin yo-dol",
        "shwin a-hop",
        "yesun",
        "yesun hana",
        "yesun tul",
        "yesun set",
        "yesun net",
        "yesun tasot",
        "yesun yasot",
        "yesun il-gop",
        "yesun yo-dol",
        "yesun a-hop",
        "irhun",
        "irhun hana",
        "irhun tul",
        "irhun set",
        "irhun net",
        "irhun tasot",
        "irhun yasot",
        "irhun il-gop",
        "irhun yo-dol",
        "irhun a-hop",
        "yodun",
        "yodun hana",
        "yodun tul",
        "yodun set",
        "yodun net",
        "yodun tasot",
        "yodun yasot",
        "yodun il-gop",
        "yodun yo-dol",
        "yodun a-hop",
        "ahun",
        "ahun hana",
        "ahun tul",
        "ahun set",
        "ahun net",
        "ahun tasot",
        "ahun yasot",
        "ahun il-gop",
        "ahun yo-dol",
        "ahun a-hop"
    )

    private val englishToKoreanCommands = hashMapOf(
        "right leg back fighting stance" to "sa ko rip ja seh",
        "front stance" to "chun kul ja seh",
        "horse riding stance" to "kee ma ja seh",
        "fighting stance" to "hu kul ja seh",
        "attention" to "cha ryut",
        "bow" to "kyung yet",
        "ready" to "choon bee",
        "return" to "ba ro",
        "by the count" to "ku ryung e mat cho so",
        "begin" to "shi jak",
        "stand up" to "e ro soda",
        "warm up" to "choon be woon dong",
        "line drill" to "ki cho dong jak",
        "go" to "shi jak",
        "reverse stance" to "kyo te",
        "stand back up" to "e ro soda",
        "turn around" to "dwee ro tora"
    )

    private val footTechs = arrayOf(
        "front snapping kick",
        "front push kick",
        "scoop kick",
        "tornado kick",
        "inside outside crescent kick",
        "outside inside crescent kick",
        "spinning back kick",
        "side kick",
        "stepping side kick",
        "roundhouse kick"
    )
    private val handTechs = arrayOf(
        "middle punch",
        "low punch",
        "high punch",
        "karate chop",
        "spinning back fist",
        "hammer fist",
        "reverse karate chop",
        "uppercut",
        "reverse punch",
        "jab",
        "cross",
        "spear hand",
        "palm heel strike"
    )
    private val modifiers = arrayOf("low", "middle", "high")
    private val stances = arrayOf(
        "right leg back fighting stance",
        "horse riding stance",
        "front stance",
        "fighting stance",
        "han ball jah say"
    )


    fun stopSpeaking() {

        if (mTTS.isSpeaking()) {
            mTTS.stop()

        }
    }


    fun randomWorkout(
        handYes: Boolean,
        kickYes: Boolean,
        blockYes: Boolean,
        handLineYes: Boolean,
        kickLineYes: Boolean,
        blockLineYes: Boolean,
        prePostStuffYes: Boolean,
        minutesForWorkout: Int
    ) {

        var secondsForWorkout = minutesForWorkout * 60
        var numberOfWorkoutSections = 0
        var secondsPassed = 0
        if (handYes) {
            numberOfWorkoutSections++
        }
        if (kickYes) {
            numberOfWorkoutSections++
        }
        if (blockYes) {
            numberOfWorkoutSections++
        }
        if (handLineYes) {
            numberOfWorkoutSections++
        }
        if (kickLineYes) {
            numberOfWorkoutSections++
        }
        if (blockLineYes) {
            numberOfWorkoutSections++
        }
        if (prePostStuffYes) {
            bowIn()
            stretches()
            loosenUp()
            warmUp()
        }


        var secondsPerSection = secondsForWorkout / numberOfWorkoutSections

        val numberHand = secondsPerSection / handTechs.size
        val numberKick = secondsPerSection / footTechs.size
        var numberBlock = secondsPerSection / blockTechs.size
        val numberHandLine = secondsPerSection / handTechs.size
        val numberKickLine = secondsPerSection / footTechs.size
        val numberBlockLine = secondsPerSection / blockTechs.size
        var handCounter = 0
        var kickCounter = 0
        var blockCounter = 0
        var kickLineCounter = 0
        var handLineCounter = 0
        var blockLineCounter = 0

        while (secondsPassed < secondsForWorkout) {
            if (handYes) {

                while (handCounter <= secondsPerSection) {

                    var handTech = handTechs[(Math.random() * handTechs.size).toInt()]
                    speak("$numberHand $handTech hand techniques, go!", 3)
                    for (i in 1..10) {
                        speak("$i", 1)
                        handCounter++
                        secondsPassed++
                    }


                }

            }
            if (kickYes) {

                while (kickCounter <= secondsPerSection) {

                    var kickTech = footTechs[(Math.random() * footTechs.size).toInt()]
                    speak("$numberKick $kickTech kicking techniques, go!", 3)
                    for (i in 1..10) {
                        speak("$i", 1)
                        kickCounter++
                        secondsPassed++
                    }


                }

            }
            if (blockYes) {

                while (blockCounter <= secondsPerSection) {

                    var blockTech = blockTechs[(Math.random() * blockTechs.size).toInt()]
                    speak("$numberBlock $blockTech blocking techniques, go!", 3)
                    for (i in 1..10) {
                        speak("$i", 1)
                        blockCounter++
                        secondsPassed++
                    }


                }

            }
            if (handLineYes) {

                while (handLineCounter <= secondsPerSection) {

                    var handLineTech = handTechs[(Math.random() * handTechs.size).toInt()]
                    speak(
                        "$numberHandLine $handLineTech  hand technique line drills, stepping forward, go!",
                        3
                    )
                    for (i in 1..3) {
                        speak("$i", 2)
                        handCounter += 2
                        secondsPassed += 2
                    }


                }

            }
            if (kickLineYes) {

                while (kickLineCounter <= secondsPerSection) {

                    var kickLineTech = footTechs[(Math.random() * footTechs.size).toInt()]
                    speak(
                        "$numberKickLine $kickLineTech kicking technique line drills, stepping forward, go!",
                        3
                    )
                    for (i in 1..3) {
                        speak("$i", 2)
                        handCounter += 2
                        secondsPassed += 2
                    }


                }

            }
            if (blockLineYes) {

                while (blockLineCounter <= secondsPerSection) {

                    var blockLineTech = blockTechs[(Math.random() * blockTechs.size).toInt()]
                    speak(
                        "$numberBlockLine $blockLineTech  blocking technique line drills, stepping forward, go!",
                        3
                    )
                    for (i in 1..3) {
                        speak("$i", 2)
                        handCounter += 2
                        secondsPassed += 2
                    }


                }

            }
        }

        if (prePostStuffYes) {
            coolDown()
            bowOut()
        }
        speak("Timed random workout complete! Great job!", 3)

    }

    fun timedWorkout(
        handYes: Boolean,
        kickYes: Boolean,
        blockYes: Boolean,
        handLineYes: Boolean,
        kickLineYes: Boolean,
        blockLineYes: Boolean,
        prePostStuffYes: Boolean,
        minutesForWorkout: Int
    ) {

        var secondsForWorkout = minutesForWorkout * 60
        var numberOfWorkoutSections = 0
        var secondsPassed = 0
        if (handYes) {
            numberOfWorkoutSections++
        }
        if (kickYes) {
            numberOfWorkoutSections++
        }
        if (blockYes) {
            numberOfWorkoutSections++
        }
        if (handLineYes) {
            numberOfWorkoutSections++
        }
        if (kickLineYes) {
            numberOfWorkoutSections++
        }
        if (blockLineYes) {
            numberOfWorkoutSections++
        }
        if (prePostStuffYes) {
            bowIn()
            stretches()
            loosenUp()
            warmUp()
        }


        var secondsPerSection = secondsForWorkout / numberOfWorkoutSections

        val numberHand = secondsPerSection / handTechs.size
        val numberKick = secondsPerSection / footTechs.size
        var numberBlock = secondsPerSection / blockTechs.size
        val numberHandLine = secondsPerSection / handTechs.size
        val numberKickLine = secondsPerSection / footTechs.size
        val numberBlockLine = secondsPerSection / blockTechs.size
        var handCounter = 0
        var kickCounter = 0
        var blockCounter = 0
        var kickLineCounter = 0
        var handLineCounter = 0
        var blockLineCounter = 0

        while (secondsPassed < secondsForWorkout) {
            if (handYes) {

                while (handCounter <= secondsPerSection) {

                    handTechs.forEach {
                        speak("$numberHand $it hand techniques, go!", 3)
                        for (i in 1..10) {
                            speak("$i", 1)
                            handCounter++
                            secondsPassed++
                        }
                    }

                }

            }
            if (kickYes) {

                while (kickCounter <= secondsPerSection) {

                    footTechs.forEach {
                        speak("$numberKick $it kicking techniques, go!", 3)
                        for (i in 1..10) {
                            speak("$i", 1)
                            kickCounter++
                            secondsPassed++
                        }
                    }

                }

            }
            if (blockYes) {

                while (blockCounter <= secondsPerSection) {

                    blockTechs.forEach {
                        speak("$numberBlock $it blocking techniques, go!", 3)
                        for (i in 1..10) {
                            speak("$i", 1)
                            blockCounter++
                            secondsPassed++
                        }
                    }

                }

            }
            if (handLineYes) {

                while (handLineCounter <= secondsPerSection) {

                    handTechs.forEach {
                        speak(
                            "$numberHandLine $it  hand technique line drills, stepping forward, go!",
                            3
                        )
                        for (i in 1..3) {
                            speak("$i", 2)
                            handCounter += 2
                            secondsPassed += 2
                        }
                    }

                }

            }
            if (kickLineYes) {

                while (kickLineCounter <= secondsPerSection) {

                    footTechs.forEach {
                        speak(
                            "$numberKickLine $it  kicking technique line drills, stepping forward, go!",
                            3
                        )
                        for (i in 1..3) {
                            speak("$i", 2)
                            handCounter += 2
                            secondsPassed += 2
                        }
                    }

                }

            }
            if (blockLineYes) {

                while (blockLineCounter <= secondsPerSection) {

                    blockTechs.forEach {
                        speak(
                            "$numberBlockLine $it  blocking technique line drills, stepping forward, go!",
                            3
                        )
                        for (i in 1..3) {
                            speak("$i", 2)
                            handCounter += 2
                            secondsPassed += 2
                        }
                    }

                }

            }
        }

        if (prePostStuffYes) {
            coolDown()
            bowOut()
        }
        speak("Timed workout complete! Great job!", 3)
    }


    fun workOut() {
        speak("Beginning workout", 5)
        bowIn()
        stretches()
        loosenUp()
        warmUp()
        handLineDrills()
        kickLineDrills()
        blockLineDrills()
        takeBreak(5)
        doForms()
        combos()
        randomCombos()
        takeBreak(5)
        coolDown()
        bowOut()
        speak("Workout complete! Awesome job!", 1)
    }

    fun totalWarmup() {
        stretches()
        loosenUp()
        warmUp()
    }

    fun stances() {
        speak("Begin stance drills!", 3)

        for (i in 1..10) {
            var stance = stances[(Math.random() * stances.size).toInt()]
            speak("$stance!", 4)
        }

        speak("End stance drills! Good job!", 3)
    }

    fun bowIn() {
        speak("attention!", 3)
        speak("bow!", 2)
    }

    /* access modifiers changed from: private */
    fun translateNumber(numberToTranslate: Int): String {
        return if (numberToTranslate >= 100) {
            "zero"
        } else translateTable[numberToTranslate]
    }

    fun bowOut() {
        speak("attention!", 3)
        speak("bow!", 2)
        speak("Loyalty to country", 4)
        speak("Obedience to parents and elders", 4)
        speak(" Honor friendship", 4)
        speak("No retreat in battle", 4)
        speak("In fighting or conflict, choose with sense and honor", 5)
        speak("One, two, three, Tung Soo!", 3)
        speak("Workout dismissed!", 2)
    }

    fun randomCombos() {
        speak("Begin random combos!", 3)
        var techniqueList: Array<String> = arrayOf("", "", "")
        for (j in 1..10) {
            speak("Combination $j, go!", 3)
            for (i in 0..2) {
                val technique = (Math.random() * 3).toInt()
                if (technique == 0) {
                    techniqueList[i] = handTechs[(Math.random() * handTechs.size).toInt()]
                }
                if (technique == 1) {
                    techniqueList[i] = blockTechs[(Math.random() * blockTechs.size).toInt()]
                }
                if (technique == 2) {
                    techniqueList[i] =
                        modifiers[(Math.random() * 3).toInt()] + " " + footTechs[(Math.random() * footTechs.size).toInt()]
                }
                if (technique == 3) {
                    techniqueList[i] =
                        handTechs[(Math.random() * handTechs.size).toInt()]
                }
            }
            for (i in 1..3) {

                speak("Number $i, go!", 3)
                for (j in 0..2) {
                    speak(techniqueList[j], 2)
                }
                speak(" ", 4)

            }
        }
    }

    fun randomCustomCombos(
        numberOfCombinations: Int,
        numberOfTechniques: Int,
        numberOfRepeats: Int,
        handYes: Boolean,
        kickYes: Boolean,
        blockYes: Boolean
    ) {

        speak("Begin random custom combos!", 3)
        var techniqueList: Array<String> = arrayOf("", "", "")
        for (j in 1..numberOfCombinations) {
            speak("Combination $j, go!", 3)
            for (i in 0..numberOfTechniques) {
                val technique = (Math.random() * 3).toInt()
                if (technique == 0 && handYes) {
                    techniqueList[i] = handTechs[(Math.random() * handTechs.size).toInt()]
                }
                if (technique == 1 && kickYes) {
                    techniqueList[i] = blockTechs[(Math.random() * blockTechs.size).toInt()]
                }
                if (technique == 2 && blockYes) {
                    techniqueList[i] =
                        modifiers[(Math.random() * 3).toInt()] + " " + footTechs[(Math.random() * footTechs.size).toInt()]
                }

            }
            for (i in 1..numberOfRepeats) {

                speak("Number $i, go!", 3)
                for (j in 0..2) {
                    speak(techniqueList[j], 2)
                }
                speak(" ", 4)

            }
        }
        speak("Random custom combinations complete! Good job!", 3)
    }

    fun numberFlashcards() {
        globalSwitchPref = false
        speak("Begin number flashcards!", 4)
        for (i in 1..20) {
            val flashRandom = (Math.random() * 20.toDouble()).toInt()
            val translateNumber = translateNumber(flashRandom)
            speak("What is $flashRandom in Korean?", 5)
            speak("The Korean is ", 2)
            globalSwitchPref = true
            speak(flashRandom.toString(), 4)
            globalSwitchPref = false
        }
    }

    fun techniqueFlashcards() {
        speak("Begin technique flashcards!", 4)
        speak("Hand techniques first!", 4)
        for (i in 1..10) {
            val random = Math.random()

            val english = handTechs[(random * handTechs.size.toDouble()).toInt()]
            globalSwitchPref = false
            partialSwitchPref = false
            speak("What is $english in Korean?", 5)
            speak("The Korean is ", 2)
            globalSwitchPref = true
            partialSwitchPref = true
            speak(english, 4)

            globalSwitchPref = false
            partialSwitchPref = false

        }
        speak("Now let's do some kicking techniques.", 3)
        for (i2 in 1..10) {
            val random = Math.random()

            val english = footTechs[(random * footTechs.size.toDouble()).toInt()]
            globalSwitchPref = false
            partialSwitchPref = false
            speak("What is $english in Korean?", 5)
            speak("The Korean is ", 2)
            globalSwitchPref = true
            partialSwitchPref = true
            speak(english, 4)

            globalSwitchPref = false
            partialSwitchPref = false
        }
        speak("Now for some blocks!", 4)
        for (i3 in 1..10) {
            val random = Math.random()

            val english = blockTechs[(random * blockTechs.size.toDouble()).toInt()]
            globalSwitchPref = false
            partialSwitchPref = false
            speak("What is $english in Korean?", 5)
            speak("The Korean is ", 2)
            globalSwitchPref = true
            partialSwitchPref = true
            speak(english, 4)

            globalSwitchPref = false
            partialSwitchPref = false
        }
        speak("Finally, stances!", 4)
        for (stance in this.stances) {
            val random = Math.random()

            val english = stances[(random * stances.size.toDouble()).toInt()]
            globalSwitchPref = false
            partialSwitchPref = false
            speak("What is $english in Korean?", 5)
            speak("The Korean is ", 2)
            globalSwitchPref = true
            partialSwitchPref = true
            speak(english, 4)

            globalSwitchPref = false
            partialSwitchPref = false
        }
        speak("Done with flashcards! Excellent work!", 3)
    }

    fun stretches() {
        speak("Begin stretches!", 3)
        speak("Feet together, reach for the ground for 15 seconds!", 15)
        speak("Stand back up!", 2)
        speak("Feet shoulder-width apart! Reach for the ground for 15 seconds!", 15)
        speak("Stand back up!", 2)
        speak("Feet two shoulder-widths apart! Reach for the ground for 15 seconds!", 15)
        speak("Stand back up!", 2)
        speak("Split test for 15 seconds!", 15)
        speak("Lean to the left for 15 seconds!", 15)
        speak("Lean to the right for 15 seconds!", 15)
        speak("ahn jo!", 2)
        speak("Butterfly stretch for 20 seconds!", 20)
        speak("Stand up!", 3)
    }

    fun loosenUp() {
        speak("Begin loosening up!", 3)
        speak("Left hip rotations for 15 seconds!", 15)
        speak("Right hip rotations for 15 seconds!", 15)
        speak("Forward arm rotations for 15 seconds!", 15)
        speak("Reverse arm rotations for 15 seconds!", 15)
        speak("Torso twists for 20 seconds!", 20)
        speak("Lean back for 10 seconds!", 20)
        speak("Kicking warm ups, left side!", 2)
        for (i in 1..5) {
            speak(i.toString(), 1)
            speak("Chamber!", 1)
            speak("Out!", 1)
            speak("Rechamber!", 1)
            speak("Down!", 3)
        }
        speak("Kicking warm ups, right side!", 2)
        for (i2 in 1..5) {
            speak(i2.toString(), 1)
            speak("Chamber!", 1)
            speak("Out!", 1)
            speak("Rechamber!", 1)
            speak("Down!", 3)
        }
    }

    fun warmUp() {
        speak("Begin warm up!", 3)
        speak("15 sit ups! Go!", 2)
        for (i in 1..15) {
            speak(i.toString(), 3)
        }
        speak("Take the plank position!", 3)
        speak("10 push ups! Go!", 2)
        for (i2 in 1..10) {
            speak(i2.toString(), 1)
            speak("Down!", 2)
            speak("up!", 2)
        }
        speak("40 jumping jacks! Go!", 2)
        for (i3 in 1..40) {
            speak(i3.toString(), 1)
        }
        speak("Jog in place for thirty seconds! Go!", 30)
        speak("10 lunges! Go!", 2)
        for (i4 in 1..10) {
            speak(i4.toString(), 3)
        }
        speak("Right leg back fighting stance!", 3)
        speak("Bounce for 20 seconds!", 20)
        speak("Reverse stance!", 2)
        speak("Bounce for twenty seconds!", 20)
        speak("Reverse stance!", 4)
    }

    fun blockTechniques() {
        speak("Blocking Technique Line Drills!", 2)
        speak("Horse riding stance!", 3)
        val strArr = this.blockTechs
        val length = strArr.size
        for (i in 0 until length) {
            speak("10 " + strArr[i] + " techniques, go!", 3)
            for (i2 in 1..10) {
                speak(i2.toString(), 2)
            }
        }
    }

    fun blockLineDrills() {
        speak("Blocking Technique Line Drills!", 2)
        speak("Fighting stance!", 3)
        val strArr = this.blockTechs
        val length = strArr.size
        for (i in 0 until length) {
            speak("3 " + strArr[i] + " techniques, go!", 3)
            for (i2 in 1..3) {
                speak(i2.toString(), 2)
            }
            speak("Turn around!", 3)
            for (i3 in 1..3) {
                speak(i3.toString(), 2)
            }
        }
    }

    fun handTechniques() {
        speak("Begin Hand Technique Workout!", 4)
        speak("Horse riding stance!", 4)
        val strArr = this.handTechs
        val length = strArr.size
        for (i in 0 until length) {
            speak("10 " + strArr[i] + " techniques, go!", 3)
            for (i2 in 1..10) {
                speak(i2.toString(), 2)
            }
            speak("Reverse stance! 10 more!", 2)
            for (i3 in 1..10) {
                speak(i3.toString(), 3)
            }
            speak("Reverse stance!", 2)
        }
        speak("Ending hand technique workout! Good job!", 2)
    }

    fun handLineDrills() {
        speak("Begin Hand Technique Line Drills!", 3)
        speak("Front stance!", 3)
        val strArr = this.handTechs
        val length = strArr.size
        for (i in 0 until length) {
            speak("3 " + strArr[i] + " techniques, stepping forward, go!", 3)
            for (i2 in 1..10) {
                speak(i2.toString(), 2)
            }
        }
        speak("Hang jin, stepping forward!", 3)
        for (i3 in 1..3) {
            speak(i3.toString(), 3)
        }
        speak("Turn around!", 2)
        for (i4 in 1..3) {
            speak(i4.toString(), 3)
        }
        speak("Ending hand line drills! Good job!", 2)
    }

    fun takeBreak(quietTime: Long) {
        speak("Take a $quietTime minute break! Get some water!", 3)
        val j = 60.toLong()
        for (secondsCountdown in quietTime * j downTo 1) {
            if (Math.floorMod(secondsCountdown, 60) == 0L) {
                speak((secondsCountdown / j).toString() + " minutes left in break!", 2)
            }
            TimeUnit.SECONDS.sleep(1)
        }
        speak("Break over!", 3)
    }

    fun kickTechniques() {
        speak("Time for kicking workout! Fighting stance!", 3)
        val strArr = this.footTechs
        val length = strArr.size
        for (i in 0 until length) {
            speak("10 " + strArr[i] + " techniques, go!", 4)
            for (i2 in 1..10) {
                speak(i2.toString(), 3)
            }
            speak("Reverse stance! 10 more!", 2)
            for (i3 in 1..10) {
                speak(i3.toString(), 3)
            }
            speak("Reverse stance!", 2)
        }
        speak("Turn around!", 2)
        speak("Horse riding stance!", 3)
        speak("Ending kicking workout! Excellent work!", 3)
    }

    fun kickLineDrills() {
        var i: Int
        speak("Time for kicking line drills! Fighting stance!", 3)
        val strArr = this.footTechs
        val length = strArr.size
        for (i2 in 0 until length) {
            speak("3 " + strArr[i2] + " techniques, stepping forward, go!", 4)
            var i3 = 1
            while (true) {
                if (i3 > 3) {
                    break
                }
                speak(i3.toString(), 3)
                i3++
            }
            speak("Turn around! 3 more!", 2)
            var i4 = 1
            i = 3
            while (i4 <= i) {
                speak(i4.toString(), 3)
                i4++
                i = 3
            }
            speak("Turn around!", 2)
        }
        speak("Turn around!", 2)
        speak("Horse riding stance!", 3)
        speak("Ending line drills! Excellent work!", 3)
    }


    fun combos() {
        speak("Let's do some combinations!", 3)
        speak("Right leg back fighting stance", 3)
        speak("Jab, jab, cross! Five times! Go!", 2)
        for (i in 1..5) {
            speak(i.toString(), 4)
        }
        speak("Reverse stance!", 2)
        for (i2 in 1..5) {
            speak(i2.toString(), 4)
        }
        speak("Reverse stance!", 2)
        speak("Jab, cross, uppercut! Five times! Go!", 2)
        for (i3 in 1..5) {
            speak(i3.toString(), 4)
        }
        speak("Reverse stance!", 2)
        for (i4 in 1..5) {
            speak(i4.toString(), 4)
        }
        speak("Reverse stance!", 2)
        speak("Jab, cross, front snapping kick, stepping forward! Three times! Go!", 2)
        for (i5 in 1..3) {
            speak(i5.toString(), 5)
        }
        speak("Turn around!", 2)
        for (i6 in 1..3) {
            speak(i6.toString(), 5)
        }
        speak("Turn around!", 2)
        speak("Two roundhouse kicks! Three times, stepping forward! Go!", 2)
        for (i7 in 1..3) {
            speak(i7.toString(), 4)
        }
        speak("Turn around!", 2)
        for (i8 in 1..3) {
            speak(i8.toString(), 4)
        }
        speak("Turn around!", 2)
        speak("Done with combinations! You showed great discipline!", 2)
    }

    fun doForms() {
        speak("Time to do forms!", 3)
        speak("Ready!", 3)
        speak("Key cho chan Ill boo!", 3)
        speak("By the count!", 3)
        for (i in 1..20) {
            speak(i.toString(), 3)
        }
        speak("Return!", 8)
        speak("Ready!", 3)
        speak("Key cho chan Ill boo!", 3)
        speak("By the count!", 3)
        for (i2 in 1..20) {
            speak(i2.toString(), 3)
        }
        speak("Return!", 8)
        for (i3 in 1..2) {
            speak("Ready!", 3)
            speak("Key cho chan Ee boo!", 3)
            speak("By the count!", 3)
            for (i4 in 1..20) {
                speak(i4.toString(), 3)
            }
            speak("Return!", 8)
        }
        for (i5 in 1..4) {
            speak("Ready!", 3)
            speak("Key cho chan San boo!", 3)
            speak("By the count!", 3)
            for (i6 in 1..20) {
                speak(i6.toString(), 3)
            }
            speak("Return!", 3)
        }
        speak("End forms!", 4)
    }

    fun coolDown() {
        speak("Do cool down!", 3)
        speak("10 jumping jacks, go!", 2)
        for (i in 1..10) {
            speak(i.toString(), 2)
        }
        speak("Jog in place for thirty seconds! Go!", 2)
        for (i2 in 30 downTo 0) {
            if (i2 % 10 == 0) {
                speak("$i2 seconds left!", 1)
            }
        }
        speak(
            "Now do deep breathing exercises. Breathe in for three seconds, hold for three, exhale for five.",
            3
        )
        for (i3 in 1..10) {
            speak("Inhale for three.", 3)
            speak("Hold for three.", 3)
            speak("Exhale for five.", 5)
        }
        speak("Cooldown complete.", 2)
    }

    fun speak(words_to_speak: String, pause: Long) {

        val possibleNumber: String
        var allLowerCase = words_to_speak.toLowerCase(Locale.US)
        val originalMessage = allLowerCase
        worker.showToast(words_to_speak, callingContext, callingActiv)
        /*       this.runOnUiThread {
                   val inflater: LayoutInflater = callingActiv.getLayoutInflater()
                   val layout: View = inflater.inflate(
                       custom_toast,
                       callingActiv.custom_toast_layout_id


                   )
                   val image: ImageView = layout.findViewById(custom_toast_image)
                   image.setImageResource(R.drawable.tangsoodo)
                   val text: TextView = layout.findViewById(custom_toast_message)
                   text.text = "$words_to_speak"
                   val myToast = Toast(callingContext)
                   myToast.setGravity(Gravity.CENTER, 0, 0)

                   myToast.view = layout//setting the view of custom toast layout

                   myToast.show()
               }*/
        if (globalSwitchPref) {


            for (key in englishToKoreanTechs.keys) {
//             val regex = (""".*""" + key + """.*""").toRegex(RegexOption.COMMENTS)
                val translation: String = englishToKoreanTechs[key]!!
                allLowerCase = allLowerCase.replace(
                    oldValue = key,
                    newValue = translation, ignoreCase = true
                )

            }
            for (key in englishToKoreanCommands.keys) {
//             val regex = (""".*""" + key + """.*""").toRegex(RegexOption.COMMENTS)
                val translation: String = englishToKoreanCommands[key]!!
                allLowerCase = allLowerCase.replace(
                    oldValue = key,
                    newValue = translation, ignoreCase = true
                )

            }
            TimeUnit.MILLISECONDS.sleep(100)
            if (originalMessage != allLowerCase) {
                mTTS.setVoice(koreanInstructor)
            }
            mTTS.speak(allLowerCase, TextToSpeech.QUEUE_FLUSH, null, null)
            TimeUnit.MILLISECONDS.sleep(100)
            mTTS.setVoice(englishInstructor)
            if ((words_to_speak.toIntOrNull() != null)) {

                possibleNumber = translateNumber(words_to_speak.toInt())
                if (possibleNumber != "zero") {
                    mTTS.setVoice(koreanInstructor)
                    mTTS.speak(possibleNumber, TextToSpeech.QUEUE_FLUSH, null, null)
                    TimeUnit.MILLISECONDS.sleep(100)
                    mTTS.setVoice(englishInstructor)
                }

            }
        } else {
            /*        val url_for_speech = baseURL
                    val finalURL = URL(url_for_speech)
                    val connection = finalURL.openConnection() as HttpURLConnection
                    connection.requestMethod = "POST"
                    connection.connectTimeout = 300000
                    connection.doOutput = true

                    connection.setRequestProperty("charset", "utf-8")
                    connection.setRequestProperty("Content-Type","application/json")
                    connection.setRequestProperty("X-Goog-Api-Key","AIzaSyClqA2Z892U4A4zP6c49MlQcMS2vrtfInw")
                    val someJson: ByteArray = """
                        {
                            "input": {
                                "text": "${words_to_speak}"
                            }
                            "voice": {
                                "languageCode": "en-US"
                                "ssmlGender": "MALE"
                            }
                            "audioConfig": {
                                "audioEncoding": "OGG_OPUS"
                            }
                        }
                    """.trimIndent().toByteArray()
                    connection.setRequestProperty("Content-Length", someJson.size.toString())
                    try {
                            val outputStream = DataOutputStream(connection.outputStream)
                            outputStream.write(someJson)
                            outputStream.flush()
                            val returnedDataStream = BufferedInputStream(connection.inputStream)
                            val returnedDataBytes = returnedDataStream.readBytes()
                        this.runOnUiThread { Toast.makeText(applicationContext, returnedDataBytes.toString(), Toast.LENGTH_SHORT) }
                            val audioOut: ByteArray = Base64.decode(returnedDataBytes, Base64.DEFAULT)
                            val tempFile = File.createTempFile("speech", "ogg",cacheDir)
                            tempFile.deleteOnExit()
                            val fos = FileOutputStream(tempFile)
                            fos.write(audioOut)
                            fos.close()

                            val mediaPlayer = MediaPlayer()
                            mediaPlayer.reset()
                            val fin =  FileInputStream(tempFile)

                            mediaPlayer.setDataSource(fin.fd)
                            mediaPlayer.prepare()
                            mediaPlayer.start()
                            mediaPlayer.release()


                    } catch (exception: Exception) {}*/


            mTTS.speak(words_to_speak, TextToSpeech.QUEUE_FLUSH, null, null)
        }

        TimeUnit.SECONDS.sleep(pause)
        mTTS.setVoice(englishInstructor)
    }

}