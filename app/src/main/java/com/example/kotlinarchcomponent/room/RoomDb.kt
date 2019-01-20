package com.example.kotlinarchcomponent.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlinarchcomponent.dataobjects.word.Word
import com.example.kotlinarchcomponent.dataobjects.word.daointerfaces.WordDao


@Database(entities = arrayOf(Word::class), version = 1)
abstract class RoomDb : RoomDatabase() {
    abstract fun wordDao(): WordDao


    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null
        private val dbCallback: Callback = object : Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
//                PopulateDbAsync(INSTANCE).execute()
            }

        }

        fun getDatabase(context: Context): RoomDb? {
            INSTANCE ?: synchronized(RoomDb::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    "word_database"
                ).addCallback(dbCallback).build()
            }
            return INSTANCE
        }


    }


    private class PopulateDbAsync internal constructor(db: RoomDb?) : AsyncTask<Void, Void, Void>() {

        private val mDao: WordDao

        init {
            mDao = db!!.wordDao()
        }

        override fun doInBackground(vararg params: Void): Void? {
//            mDao.deleteAll()
            var word = Word("Hello")
            mDao.insert(word)
            word = Word("World")
            mDao.insert(word)
            return null
        }
    }
}