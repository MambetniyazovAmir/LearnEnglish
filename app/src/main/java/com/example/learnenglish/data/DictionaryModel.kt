package com.example.learnenglish.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dictionary")
data class DictionaryModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "word_eng")
    @SerializedName("word_eng")
    var wordEng: String?,

    @ColumnInfo(name = "word_uzb")
    @SerializedName("word_uzb")
    var wordUzb: String?

)