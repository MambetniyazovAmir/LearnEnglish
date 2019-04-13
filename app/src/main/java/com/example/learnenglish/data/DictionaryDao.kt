package com.example.learnenglish.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.OnConflictStrategy

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary")
    fun getAllWords(): List<DictionaryModel>

    @Query("SELECT * FROM dictionary WHERE id = :id")
    fun getWordById(id: Int): DictionaryModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToDB(models: List<DictionaryModel>)
}
