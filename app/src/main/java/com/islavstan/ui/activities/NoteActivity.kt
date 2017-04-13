package com.islavstan.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.arellomobile.mvp.presenter.InjectPresenter
import com.islavstan.common.MvpAppCompatActivity

import com.islavstan.kotlinexample.R
import com.islavstan.model.Note
import com.islavstan.presenters.NotePresenter
import com.islavstan.utils.formatDate
import com.islavstan.views.NoteView
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : MvpAppCompatActivity(), NoteView {
    private var mNoteDeleteDialog: MaterialDialog? = null
    private var mNoteInfoDialog: MaterialDialog? = null
    @InjectPresenter
    lateinit var mPresenter: NotePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        //перемещаем курсор в конец поля ввода
        etTitle.onFocusChangeListener = View.OnFocusChangeListener() { view, hasFocus ->
            if (hasFocus) {
                var editText = view as EditText
                editText.setSelection((editText.text.length))
            }
        }


        val noteId = intent.extras.getLong("note_id", -1)
        val notePosition = intent.extras.getInt("note_position", -1)
        mPresenter.showNote(noteId, notePosition)
    }


    override fun showNote(note: Note) {
        tvNoteDate.text = formatDate(note.changeDate)
        etTitle.setText(note.title)
        etText.setText(note.text)
    }


    override fun onNoteDeleted() {
        Toast.makeText(this, "Note is deleted", Toast.LENGTH_SHORT).show()
        finish()
    }


    override fun showNoteInfoDialog(noteInfo: String) {
        mNoteInfoDialog = MaterialDialog.Builder(this)
                .title("Информация о заметке")
                .positiveText("Ок")
                .content(noteInfo)
                .onPositive { materialDialog, dialogAction -> mPresenter.hideNoteInfoDialog() }
                .cancelListener { mPresenter.hideNoteInfoDialog() }
                .show()
    }

    override fun hideNoteDeleteDialog() {
        mNoteDeleteDialog?.dismiss()
    }

    override fun onNoteSaved() {
        Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
    }

    override fun hideNoteInfoDialog() {
        mNoteInfoDialog?.dismiss()
    }

    override fun showNoteDeleteDialog() {
        mNoteDeleteDialog = MaterialDialog.Builder(this)
                .title("Delete note")
                .content("Do you really want delete note?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive { materialDialog, dialogAction ->
                    mPresenter.hideNoteDeleteDialog()
                    mPresenter.deleteNote()
                }
                .onNegative { materialDialog, dialogAction -> mPresenter.hideNoteDeleteDialog() }
                .cancelListener { mPresenter.hideNoteDeleteDialog() }
                .show()


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSaveNote -> mPresenter.saveNote(etTitle.text.toString(), etText.text.toString())

            R.id.menuDeleteNote -> mPresenter.showNoteDeleteDialog()

            R.id.menuNoteInfo -> mPresenter.showNoteInfoDialog()
        }
        return super.onOptionsItemSelected(item)
    }

}
