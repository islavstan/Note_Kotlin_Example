package com.islavstan

import com.activeandroid.query.Delete
import com.activeandroid.query.Select
import java.util.*


class NoteDao {

    //создать новую заметку
    fun createNote(): Note {
        var note = Note("новая заметка", Date())
        note.save()
        return note
    }

    fun saveNote(note: Note) = note.save()

    //Загружает все существующие заметки и передает во View
    fun loadAllNotes() = Select().from(Note::class.java).execute<Note>()

    //Ищет заметку по id и возвращает ее
    fun getNoteById(noteId: Long) = Select().from(Note::class.java).where("id = ?", noteId).executeSingle<Note>()


    /**
     * Удаляет все существующие заметки
     */
    fun deleteAllNotes() {
        Delete().from(Note::class.java).execute<Note>();
    }

    /**
     * Удаляет заметку по id
     */
    fun deleteNote(note: Note) {
        note.delete()
    }


}

