package com.islavstan.di

import com.islavstan.model.NoteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NoteDaoModule {

    @Provides
    @Singleton
    fun provideNoteDao() : NoteDao = NoteDao()

}