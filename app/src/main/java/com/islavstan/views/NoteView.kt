package com.islavstan.views

import com.arellomobile.mvp.MvpView
import com.islavstan.model.Note


interface NoteView : MvpView {

    fun showNote(note: Note)

    fun onNoteSaved()

    fun onNoteDeleted()

    fun showNoteInfoDialog(noteInfo: String)

    fun hideNoteInfoDialog()

    fun showNoteDeleteDialog()

    fun hideNoteDeleteDialog()

}