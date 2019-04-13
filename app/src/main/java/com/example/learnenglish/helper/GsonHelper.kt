package com.example.learnenglish.helper

import android.content.Context
import com.example.learnenglish.data.DictionaryModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type

class GsonHelper(private val context: Context, private val fileName: String) {

    private fun loadJsonFromAsset(): String? {
        val json: String
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset("UTF-8"))
        } catch (exception: IOException) {
            exception.printStackTrace()
            return null
        }
        return json
    }

    fun getDictionaryFromLocalAssets(): List<DictionaryModel> {
        val json = loadJsonFromAsset()
        json?.let {
            val listType: Type = object : TypeToken<List<DictionaryModel>>() {}.type
            return GsonBuilder().create().fromJson(json, listType)
        }
        return arrayListOf()
    }
}