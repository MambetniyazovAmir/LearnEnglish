package com.example.learnenglish.learn

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.util.Log
import android.widget.Toast
import com.example.learnenglish.R
import com.example.learnenglish.data.DictionaryDao
import com.example.learnenglish.data.DictionaryDatabase
import com.example.learnenglish.data.DictionaryModel
import kotlinx.android.synthetic.main.activity_learn.*
import java.util.*

class LearnActivity : AppCompatActivity() {
    private var k: Int = 1
    lateinit var texToSpeech: TextToSpeech
    private lateinit var dictionaryDao: DictionaryDao
    private var allWords: List<DictionaryModel> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        texToSpeech = TextToSpeech(this, OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                texToSpeech.language = Locale.UK
            }
        })

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        dictionaryDao = DictionaryDatabase.getInstance(this).dictionaryDao()

        k = (0..20).random()
        allWords = dictionaryDao.getAllWords()
        wordEng.text = allWords[k].wordEng
        wordUzb.text = allWords[k].wordUzb
        Log.d("soz", allWords[2].wordEng.toString())
        nextBtn.setOnClickListener {
            if (k == allWords.size) {
                Toast.makeText(this, "Tawsildi soz qoyagoy", Toast.LENGTH_LONG).show()
                nextBtn.isEnabled = false
                return@setOnClickListener
            }
            k = (0..20).random()
            wordEng.text = allWords[k].wordEng
            wordUzb.text = allWords[k].wordUzb
        }
        speakButton.setOnClickListener {
            val toSpeak = wordEng.text.toString()
            if (toSpeak == "") {
                Toast.makeText(this, "soz kelmi atir", Toast.LENGTH_SHORT).show()
            } else {
                texToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
            }
        }

    }
}
