package com.yusuftalhaklc.akotlinguesstheword

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var highScore : Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("com.yusuftalhaklc.akotlinguesstheword", MODE_PRIVATE)
        highScore = sharedPreferences.getInt("highScore",0)

        scoreTextView.text = "High Score : " + highScore

    }
    fun newGameClicked(view : View){
        sharedPreferences.edit().remove("highScore").apply()
        intentToGameActivity()
    }
    fun playClicked(view : View) {
        intentToGameActivity()
    }

    override fun onResume() {
        super.onResume()
        scoreTextView.text = "High Score : " + sharedPreferences.getInt("highScore",0)
    }

    private fun intentToGameActivity(){
        val intent = Intent(applicationContext, GameActivity::class.java)
        intent.putExtra("highScore", highScore)
        startActivity(intent)

    }
}