package com.islavstan

import com.arellomobile.mvp.MvpActivity


class MainActivity : MvpActivity(), MainView{
    override fun onNotesLoaded(notes: List<Note>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchResult(notes: List<Note>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAllNotesDeleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNoteDeleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteInfoDialog(noteInfo: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteInfoDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteDeleteDialog(notePosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteDeleteDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteContextDialog(notePosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteContextDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}