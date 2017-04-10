package com.islavstan

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NoteDaoModule {

    @Provides
    @Singleton
    fun provideNoteDao() : NoteDao= NoteDao()

}