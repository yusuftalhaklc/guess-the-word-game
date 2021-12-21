package com.yusuftalhaklc.akotlinguesstheword

import android.R.attr
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import android.R.attr.value

import android.R.attr.key
import android.content.SharedPreferences.Editor

import android.preference.PreferenceManager




class GameActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var highScore :Int ?= null
    var score :Int ?= null
    var counter = 0
    var answer = ""
    var randomNew = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        highScore = intent.getIntExtra("highScore", 0)
        score = 0
        scoreTextView2.text = "Score : " + score
        generateQuestion()

    }

    fun okeyClicked(view : View){


        if ( (radioGroup.checkedRadioButtonId !=-1)){

            var checkedRadioButton = when (radioGroup.checkedRadioButtonId) {
                R.id.answerButton1 ->  answerButton1.text.toString()
                R.id.answerButton2 ->  answerButton2.text.toString()
                R.id.answerButton3 ->  answerButton3.text.toString()
                else -> null

            }
            println("result :  " + checkedRadioButton +" , " + answer)
            if (checkedRadioButton == answer)
            {
                
                Toast.makeText(applicationContext, "Tebrikler!", Toast.LENGTH_SHORT).show()

                score = score?.plus(5)
                scoreTextView2.text = "Score : " + score


                if(score!! > highScore!!){

                    val mPrefs: SharedPreferences  = getSharedPreferences("com.yusuftalhaklc.akotlinguesstheword", MODE_PRIVATE)
                    mPrefs.edit().putInt("highScore", score!!).apply()
                }


                generateQuestion()

                radioGroup.clearCheck()

            }
            else
            {
                Toast.makeText(applicationContext, "Tekrar dene", Toast.LENGTH_SHORT).show()
                radioGroup.clearCheck()
            }
        }
        else {
            Toast.makeText(applicationContext, "Seçim yapmalısın!", Toast.LENGTH_SHORT).show()
        }



    }

    fun generateQuestion() {

        if(counter !=8){

        val randomNumber = mutableListOf(0,1,2,3,4,5,6,7,8)
        val randomText= (1..3).random()


        val animals = listOf<Int>(
                                    R.drawable.bear ,
                                    R.drawable.crocodile,
                                    R.drawable.fox,
                                    R.drawable.lion,
                                    R.drawable.shark ,
                                    R.drawable.snake,
                                    R.drawable.tiger,
                                    R.drawable.whale,
                                    R.drawable.wolf
            )
        val questionMap = mapOf<Int, String>(
                                    animals[0] to "Bear",
                                    animals[1] to "Crocodile",
                                    animals[2] to "Fox",
                                    animals[3] to "Lion",
                                    animals[4] to "Shark",
                                    animals[5] to "Snake",
                                    animals[6] to "Tiger",
                                    animals[7] to "Whale",
                                    animals[8] to "Wolf",
            )

        imageView.setImageResource(animals[counter])
        answer =  questionMap.get(animals[counter]).toString()
        randomNumber.removeAt(counter)


        when(randomText){
                1 -> {
                    answerButton1.text = questionMap.get(animals[counter])

                    randomNew = (0..(randomNumber.size-1)).random()
                    answerButton2.text = questionMap.get(animals[  randomNumber[randomNew]  ])
                    randomNumber.removeAt(randomNew)

                    randomNew = (0..(randomNumber.size-1)).random()
                    answerButton3.text = questionMap.get(animals[  randomNumber[randomNew]  ])
                    randomNumber.removeAt(randomNew)
                }
                2 -> {
                    randomNew = (0..(randomNumber.size-1)).random()
                    answerButton1.text = questionMap.get(animals[  randomNumber[randomNew]  ])
                    randomNumber.removeAt(randomNew)

                    answerButton2.text = questionMap.get(animals[counter])

                    randomNew = (0..(randomNumber.size-1)).random()
                    answerButton3.text = questionMap.get(animals[  randomNumber[randomNew]  ])
                    randomNumber.removeAt(randomNew)

                }
                3 ->{
                    randomNew = (0..(randomNumber.size-1)).random()
                    answerButton1.text = questionMap.get(animals[  randomNumber[randomNew]  ])
                    randomNumber.removeAt(randomNew)


                    randomNew = (0..(randomNumber.size-1)).random()
                    answerButton2.text = questionMap.get(animals[  randomNumber[randomNew]  ])
                    randomNumber.removeAt(randomNew)

                    answerButton3.text = questionMap.get(animals[counter])


                }
        }
        counter++}
        else {
            Toast.makeText(applicationContext, "Oyun Bitti !", Toast.LENGTH_SHORT).show()
            radioGroup.visibility = View.GONE
            button.text = "Menu"
            button.setOnClickListener() {

                    finish()
            }
        }
    }
}