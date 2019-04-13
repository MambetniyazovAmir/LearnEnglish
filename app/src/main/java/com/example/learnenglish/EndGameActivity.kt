package com.example.learnenglish

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.learnenglish.test.TestActivity
import kotlinx.android.synthetic.main.activity_end_game.*

class EndGameActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)
        val ans: String = intent.getIntExtra("ans", 0).toString()
        val wrong: String = (20 - intent.getIntExtra("ans", 0)).toString()
//        Log.d("ans", TestActivity().rightAnswers.toString())
        rightAnswers.text = "Right Answers $ans"
        wrongAnswers.text = "Wrong Answers $wrong"
        val intent = Intent(this, MainActivity::class.java)
        val intent1 = Intent(this, TestActivity::class.java)
        menu.setOnClickListener {
            startActivity(intent)
            finish()
        }
        replay.setOnClickListener {
            startActivity(intent1)
            finish()
        }
    }
}
