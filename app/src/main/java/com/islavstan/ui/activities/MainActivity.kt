package com.islavstan.ui.activities

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.islavstan.common.MvpAppCompatActivity
import com.islavstan.kotlinexample.R
import com.islavstan.model.Note
import com.islavstan.presenters.MainPresenter
import com.islavstan.ui.adapter.NotesAdapter
import com.islavstan.ui.commons.ItemClickSupport
import com.islavstan.views.MainView
import com.pawegio.kandroid.onQueryChange
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var mPresenter: MainPresenter
    private var mNoteContextDialog: MaterialDialog? = null
    private var mNoteDeleteDialog: MaterialDialog? = null
    private var mNoteInfoDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fabButton.attachToRecyclerView(rvNotesList)



        fabButton.setOnClickListener {
            mPresenter.openNewNote(this)
        }

        with(ItemClickSupport.addTo(rvNotesList)) {
            setOnItemClickListener { recyclerView, position, v -> mPresenter.openNote(this@MainActivity, position) }
            setOnItemLongClickListener { recyclerView, position, v -> mPresenter.showNoteContextDialog(position); true }
        }

    }


    override fun onNotesLoaded(notes: List<Note>) {
        rvNotesList.adapter = NotesAdapter(notes)
        updateView()
    }

    override fun updateView() {
        rvNotesList.adapter.notifyDataSetChanged()
        if (rvNotesList.adapter.itemCount == 0) {
            rvNotesList.visibility = View.GONE
            tvNotesIsEmpty.visibility = View.VISIBLE
        } else {
            rvNotesList.visibility = View.VISIBLE
            tvNotesIsEmpty.visibility = View.GONE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        initSearcView(menu)
        return true
    }

    private fun initSearcView(menu: Menu) {
        var searchViewMenuItem = menu.findItem(R.id.action_search);
        var searchView = searchViewMenuItem.actionView as SearchView;
        searchView.onQueryChange { query -> mPresenter.search(query) }
        searchView.setOnCloseListener { mPresenter.search(""); false }


    }


    override fun onSearchResult(notes: List<Note>) {
        rvNotesList.adapter = NotesAdapter(notes)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSortByName -> mPresenter.sortNotesBy(MainPresenter.SortNotesBy.NAME)
            R.id.menuSortByDate -> mPresenter.sortNotesBy(MainPresenter.SortNotesBy.DATE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAllNotesDeleted() {
        updateView()
        Toast.makeText(this, "All notes is deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteDeleted() {
        updateView()
        Toast.makeText(this, "Note is deleted", Toast.LENGTH_SHORT).show()

    }

    override fun showNoteInfoDialog(noteInfo: String) {
        mNoteInfoDialog = MaterialDialog.Builder(this)
                .title("Note info")
                .positiveText("Ок")
                .content(noteInfo)
                .onPositive { materialDialog, dialogAction -> mPresenter.hideNoteInfoDialog() }
                .cancelListener { mPresenter.hideNoteInfoDialog() }
                .show()
    }



    override fun hideNoteInfoDialog() {
        mNoteInfoDialog?.dismiss()
    }

    override fun showNoteDeleteDialog(notePosition: Int) {
        mNoteDeleteDialog = MaterialDialog.Builder(this)
                .title("Delete note")
                .content("Do you really want delete note?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive { materialDialog, dialogAction ->
                    mPresenter.deleteNoteByPosition(notePosition)
                    mNoteInfoDialog?.dismiss()
                }
                .onNegative { materialDialog, dialogAction -> mPresenter.hideNoteDeleteDialog() }
                .cancelListener { mPresenter.hideNoteDeleteDialog() }
                .show()
    }


    override fun hideNoteDeleteDialog() {
        mNoteDeleteDialog?.dismiss()
    }


    override fun showNoteContextDialog(notePosition: Int) {
        mNoteContextDialog = MaterialDialog.Builder(this)
                .items(R.array.main_note_context)
                .itemsCallback { dialog, itemView, which, text ->
                    onContextDialogItemClick(which, notePosition)
                    mPresenter.hideNoteContextDialog()
                }
                .cancelListener { mPresenter.hideNoteContextDialog() }
                .show()

    }

    override fun hideNoteContextDialog() {
        mNoteContextDialog?.dismiss()
    }


    fun onContextDialogItemClick(contextItemPosition: Int, notePosition: Int) {
        when (contextItemPosition) {
            0 -> mPresenter.openNote(this, notePosition)
            1 -> mPresenter.showNoteInfo(notePosition)
            2 -> mPresenter.showNoteDeleteDialog(notePosition)
        }
    }
}