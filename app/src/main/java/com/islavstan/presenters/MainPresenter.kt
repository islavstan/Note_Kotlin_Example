package com.islavstan.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.islavstan.NotelinApplication
import com.islavstan.model.NoteDao
import com.islavstan.views.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var mNoteDao: NoteDao

    init {
        NotelinApplication.graph.inject(this)
    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadAllNotes()
    }

//Загружает все существующие заметки и передает во View
    private fun loadAllNotes() {
       
    }
}