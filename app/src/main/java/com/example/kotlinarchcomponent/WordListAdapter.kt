package com.example.kotlinarchcomponent

import android.content.Context
import android.service.autofill.TextValueSanitizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinarchcomponent.dataobjects.word.Word

class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val context : Context = context
    private val layoutInflator : LayoutInflater = LayoutInflater.from(context)
    private var wordsList : List<Word>? = null

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val wordItemView : TextView? = itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        var itemView : View = layoutInflator.inflate(R.layout.recyclerview_item,parent,false)
        return WordViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if(wordsList!=null){
            holder.wordItemView!!.setText(wordsList!!.get(position).getWord())
        }else{
            holder.wordItemView!!.setText("No Word")
        }
    }

    fun setWordsList(words : List<Word>){
        wordsList = words;
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       if(wordsList!=null) {
           return wordsList!!.size
       }else{
           return 0;
       }
    }

}