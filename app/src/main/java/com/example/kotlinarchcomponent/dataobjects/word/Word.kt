package com.example.kotlinarchcomponent.dataobjects.word

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Word(var selectedWord: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private var id: Int = 0;

    @NonNull
    @ColumnInfo
    private var word: String? = selectedWord

    fun getWord() = word;

    fun setWord(selectedWord: String) {
        word = selectedWord
    }

    fun getId() = id

    fun setId(identity: Int) {
        id = identity
    }

}