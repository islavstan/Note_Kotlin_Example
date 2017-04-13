package com.islavstan.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.islavstan.utils.formatDate
import com.islavstan.kotlinexample.R
import com.islavstan.model.Note
import java.util.*


class NotesAdapter(notesList: List<Note>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var mNotesList: List<Note> = notesList


    override fun getItemCount(): Int {
        return mNotesList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var note = mNotesList[i]
        viewHolder.mNoteTitle.text = note.title

        viewHolder.mNoteDate.text = formatDate(note.changeDate)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        var v = LayoutInflater.from(viewGroup.context).inflate(R.layout.note_item_layout, viewGroup, false)
        return ViewHolder(v)
    }


    class ViewHolder : RecyclerView.ViewHolder {

        var mNoteTitle: TextView
        var mNoteDate: TextView

        constructor(itemView: View) : super(itemView) {
            mNoteTitle = itemView.findViewById(R.id.tvItemNoteTitle) as TextView
            mNoteDate = itemView.findViewById(R.id.tvItemNoteDate) as TextView
        }

    }

}