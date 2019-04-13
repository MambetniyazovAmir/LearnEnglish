package com.example.learnenglish

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.learnenglish.learn.LearnActivity
import com.example.learnenglish.test.TestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, LearnActivity::class.java)
        learnBtn.setOnClickListener {
            startActivity(intent)
        }

        val intent2 = Intent(this, TestActivity::class.java)
        testBtn.setOnClickListener {
            startActivity(intent2)
        }
    }
}
