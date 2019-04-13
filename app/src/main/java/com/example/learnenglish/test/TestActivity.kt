package com.example.learnenglish.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.learnenglish.EndGameActivity
import com.example.learnenglish.R
import com.example.learnenglish.data.DictionaryDao
import com.example.learnenglish.data.DictionaryDatabase
import com.example.learnenglish.data.DictionaryModel
import kotlinx.android.synthetic.main.activity_learn.*
import kotlinx.android.synthetic.main.activity_test.*
import java.util.*

class TestActivity : AppCompatActivity() {
    private lateinit var dictionaryDao: DictionaryDao
    private var allWords: List<DictionaryModel> = arrayListOf()
    private var used: MutableList<Boolean> = arrayListOf()
    private var k: Int = 1
    private var j: Int = 1
    private var curQuestion: Int = 1
    var rightAnswers: Int = 0
    lateinit var textToSpeech: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textToSpeech = TextToSpeech(this, OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.UK
            }
        })
        dictionaryDao = DictionaryDatabase.getInstance(this).dictionaryDao()
        setContentView(R.layout.activity_test)
        allWords = dictionaryDao.getAllWords()
        for (i in 0..allWords.size) {
            used.add(true)
        }
        val intent = Intent(this, EndGameActivity::class.java)
        k = (0 until allWords.size).random()
        nextWord(k)
        nextImage.setOnClickListener {
            if (curQuestion == 20) {
                intent.putExtra("ans", rightAnswers)
                startActivity(intent)
                finish()
                return@setOnClickListener
            }
            while (!used[k]) {
                k = (0 until allWords.size).random()
            }
            okImage.visibility = View.INVISIBLE
            wrongImage.visibility = View.INVISIBLE
            nextWord(k)
            aVariant.isEnabled = true
            bVariant.isEnabled = true
            cVariant.isEnabled = true
            dVariant.isEnabled = true
            curQuestion++
            currentQuestion.text = curQuestion.toString()
            return@setOnClickListener
        }
        aVariant.setOnClickListener {
            Log.d("ans", rightAnswers.toString())
            if (aVariant.text == allWords[k].wordUzb) {
                rightAnswers++
                okImage.visibility = View.VISIBLE
            } else wrongImage.visibility = View.VISIBLE
            aVariant.isEnabled = false
            cVariant.isEnabled = false
            bVariant.isEnabled = false
            dVariant.isEnabled = false
            return@setOnClickListener
        }
        bVariant.setOnClickListener {
            if (bVariant.text == allWords[k].wordUzb) {
                rightAnswers++
                okImage.visibility = View.VISIBLE
            } else wrongImage.visibility = View.VISIBLE
            aVariant.isEnabled = false
            cVariant.isEnabled = false
            bVariant.isEnabled = false
            dVariant.isEnabled = false
            return@setOnClickListener
        }
        cVariant.setOnClickListener {
            if (cVariant.text == allWords[k].wordUzb) {
                rightAnswers++
                okImage.visibility = View.VISIBLE
            } else wrongImage.visibility = View.VISIBLE
            aVariant.isEnabled = false
            cVariant.isEnabled = false
            bVariant.isEnabled = false
            dVariant.isEnabled = false
            return@setOnClickListener
        }
        dVariant.setOnClickListener {
            if (dVariant.text == allWords[k].wordUzb) {
                rightAnswers++
                okImage.visibility = View.VISIBLE
            } else wrongImage.visibility = View.VISIBLE
            aVariant.isEnabled = false
            cVariant.isEnabled = false
            bVariant.isEnabled = false
            dVariant.isEnabled = false
            return@setOnClickListener
        }
        speak.setOnClickListener {
            val toSpeak = questionWord.text.toString()
            if (toSpeak == "") {
                Toast.makeText(this, "soz kelmi atir", Toast.LENGTH_SHORT).show()
            } else {
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
            }
        }
    }

    private fun nextWord(k: Int) {
        used[k] = false
        questionWord.text = allWords[k].wordEng
        val intList: MutableList<Int> = arrayListOf()
        for (i in 0 until allWords.size) {
            intList.add(i)
        }
        for (i in 0 until allWords.size) {
            val n: Int = (0 until allWords.size).random()
            intList[i] = intList[n].also {
                intList[n] = intList[i]
            }
        }
        j = (1..4).random()
        if (j == 1) {
            aVariant.text = allWords[k].wordUzb
            if (intList[0] == k || intList[1] == k || intList[2] == k) {
                bVariant.text = allWords[intList[5]].wordUzb
                cVariant.text = allWords[intList[6]].wordUzb
                dVariant.text = allWords[intList[7]].wordUzb
            } else {
                bVariant.text = allWords[intList[0]].wordUzb
                cVariant.text = allWords[intList[1]].wordUzb
                dVariant.text = allWords[intList[2]].wordUzb
            }
        }
        if (j == 2) {
            bVariant.text = allWords[k].wordUzb
            if (intList[0] == k || intList[1] == k || intList[2] == k) {
                aVariant.text = allWords[intList[5]].wordUzb
                cVariant.text = allWords[intList[6]].wordUzb
                dVariant.text = allWords[intList[7]].wordUzb
            } else {
                aVariant.text = allWords[intList[0]].wordUzb
                cVariant.text = allWords[intList[1]].wordUzb
                dVariant.text = allWords[intList[2]].wordUzb
            }
        }
        if (j == 3) {
            cVariant.text = allWords[k].wordUzb
            if (intList[0] == k || intList[1] == k || intList[2] == k) {
                bVariant.text = allWords[intList[5]].wordUzb
                aVariant.text = allWords[intList[6]].wordUzb
                dVariant.text = allWords[intList[7]].wordUzb
            } else {
                bVariant.text = allWords[intList[0]].wordUzb
                aVariant.text = allWords[intList[1]].wordUzb
                dVariant.text = allWords[intList[2]].wordUzb
            }
        }
        if (j == 4) {
            dVariant.text = allWords[k].wordUzb
            if (intList[0] == k || intList[1] == k || intList[2] == k) {
                bVariant.text = allWords[intList[5]].wordUzb
                cVariant.text = allWords[intList[6]].wordUzb
                aVariant.text = allWords[intList[7]].wordUzb
            } else {
                bVariant.text = allWords[intList[0]].wordUzb
                cVariant.text = allWords[intList[1]].wordUzb
                aVariant.text = allWords[intList[2]].wordUzb
            }

        }
    }
}
