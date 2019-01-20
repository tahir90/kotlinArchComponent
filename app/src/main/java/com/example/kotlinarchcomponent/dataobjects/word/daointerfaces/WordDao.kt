package com.example.kotlinarchcomponent.dataobjects.word.daointerfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlinarchcomponent.dataobjects.word.Word


@Dao
interface WordDao {

    @Insert
    fun  insert(word: Word)

    @Query("DELETE FROM Word")
    fun deleteAll()

    @Query("SELECT * FROM Word ORDER BY word ASC ")
    fun getAllWords() : LiveData<List<Word>>



}