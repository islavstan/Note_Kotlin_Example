package com.islavstan.presenters

import android.app.Activity
import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.islavstan.NotelinApplication
import com.islavstan.model.Note
import com.islavstan.model.NoteDao
import com.islavstan.ui.activities.NoteActivity
import com.islavstan.views.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var mNoteDao: NoteDao

    lateinit var mNotesList: MutableList<Note>

    init {
        NotelinApplication.graph.inject(this)
    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadAllNotes()
    }

    fun openNote(activity: Activity, position: Int) {
        val intent = Intent(activity, NoteActivity::class.java)
        intent.putExtra("note_id", mNotesList[position].id)
        activity.startActivity(intent)
    }

    //Загружает все существующие заметки и передает во View
    private fun loadAllNotes() {

    }


    //Показывает контекстное меню заметки
    fun showNoteContextDialog(position: Int) {
        viewState.showNoteContextDialog(position)
    }


    // Прячет контекстное меню заметки
    fun hideNoteContextDialog() {
        viewState.hideNoteContextDialog()
    }


    fun openNewNote(activity: Activity) {
        val newNote = mNoteDao.createNote()
        mNotesList.add(newNote)
        sortNotesBy(getCurrentSortMethod())
        openNote(activity, mNotesList.indexOf(newNote))
    }


   //Ищет заметку по имени
    fun search(query: String) {
       if (query.equals("")) {
           viewState.onSearchResult(mNotesList)
       } else {
           val searchResults = mNotesList.filter { it.title!!.startsWith(query, ignoreCase = true) }
           viewState.onSearchResult(searchResults)
       }
   }
  //Сортирует заметки
    fun sortNotesBy(sortMethod: SortNotesBy) {
        mNotesList.sortWith(sortMethod)
        viewState.updateView()
    }



}