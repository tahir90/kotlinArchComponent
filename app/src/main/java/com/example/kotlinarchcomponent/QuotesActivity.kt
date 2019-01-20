package com.example.kotlinarchcomponent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinarchcomponent.NewWordActivity.Companion.NEW_WORD_ACTIVITY_REQUEST_CODE
import com.example.kotlinarchcomponent.dataobjects.word.Word
import kotlinx.android.synthetic.main.content_main.*


class QuotesActivity : AppCompatActivity() {

    var viewModel: WordViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        viewModel = ViewModelProviders.of(this).get(WordViewModel(application)::class.java)


        var adapter = WordListAdapter(this)
        recyclerview.setAdapter(adapter)
        recyclerview.setLayoutManager(LinearLayoutManager(this))

        viewModel!!.getAllWords()!!.observe(this, Observer<List<Word>> {
            adapter.setWordsList(it)
        })

        fab.setOnClickListener {
            val intent = Intent(this@QuotesActivity, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE) {
            val word = Word(data!!.getStringExtra(NewWordActivity.EXTRA_REPLY))
            viewModel!!.insertWord(word)
        } else {
            Toast.makeText(
                getApplicationContext(),
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show();
        }
    }
}
