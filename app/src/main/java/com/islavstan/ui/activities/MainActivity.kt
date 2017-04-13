package com.islavstan.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
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

    private fun  initSearcView(menu: Menu) {
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