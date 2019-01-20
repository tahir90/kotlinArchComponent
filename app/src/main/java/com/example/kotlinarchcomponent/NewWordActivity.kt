package com.example.kotlinarchcomponent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_word.*


class NewWordActivity : AppCompatActivity() {

    companion object {
        val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }

    override fun onBackPressed() {
        setResultWithWord()
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        button_save.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setResultWithWord();
                finish()
            }
        });
    }

    private fun setResultWithWord() {
        val replyIntent = Intent()
        if (TextUtils.isEmpty(edit_word.getText())) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            val word = edit_word.getText().toString()
            replyIntent.putExtra(EXTRA_REPLY, word)
            setResult(Activity.RESULT_OK, replyIntent)
        }

    }

}