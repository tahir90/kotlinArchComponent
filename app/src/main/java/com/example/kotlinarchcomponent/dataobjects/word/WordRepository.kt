package com.example.kotlinarchcomponent.dataobjects.word

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.kotlinarchcomponent.dataobjects.word.daointerfaces.WordDao
import com.example.kotlinarchcomponent.room.RoomDb


class WordRepository(application: Application) {

    private var worddao: WordDao? = null
    private var wordsList: LiveData<List<Word>>? = null

    init {
        val db: RoomDb? = RoomDb.getDatabase(application)

        worddao = db?.wordDao()
        wordsList = worddao?.getAllWords()

    }

    fun getAllWords(): LiveData<List<Word>>? {
        return wordsList
    }

    fun insert(word: Word) {
        val task = insertAsyncTask(worddao)
        task.execute(word)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao?) :
        AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao?.insert(params[0])
            return null
        }
    }

}


