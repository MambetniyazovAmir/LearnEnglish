package com.example.learnenglish

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.learnenglish.data.DictionaryDao
import com.example.learnenglish.data.DictionaryDatabase
import com.example.learnenglish.helper.GsonHelper
import com.example.learnenglish.helper.thread.AppExecutors
import com.example.learnenglish.helper.thread.DiskIoThreadExecutor
import com.example.learnenglish.helper.thread.MainThreadExecutor

class SplashActivity : AppCompatActivity() {

    private val appExecutor: AppExecutors = AppExecutors(DiskIoThreadExecutor(), MainThreadExecutor())

    private val gsonHelper: GsonHelper = GsonHelper(this, "database.json")

    private lateinit var dictionaryDao: DictionaryDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        dictionaryDao = DictionaryDatabase.getInstance(this).dictionaryDao()
        startSplash()
        finish()
    }

    private fun startSplash() {
        appExecutor.getDiskIo().execute {
            initDictionaryData()
            appExecutor.getMainThread().execute {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initDictionaryData() {
        dictionaryDao.insertToDB(gsonHelper.getDictionaryFromLocalAssets())
    }

}
