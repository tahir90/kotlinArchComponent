package com.example.kotlinarchcomponent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinarchcomponent.dataobjects.word.Word
import com.example.kotlinarchcomponent.dataobjects.word.WordRepository


class WordViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: WordRepository = WordRepository(application)
    private var mAllWords: LiveData<List<Word>>? = mRepository.getAllWords()

    fun getAllWords(): LiveData<List<Word>>? {
        return mAllWords
    }

    fun insertWord(word: Word) {
        mRepository.insert(word)
    }

}

