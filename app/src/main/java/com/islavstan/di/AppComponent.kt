package com.islavstan.di

import com.islavstan.presenters.MainPresenter
import com.islavstan.presenters.NotePresenter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(NoteDaoModule::class))
interface AppComponent {

    fun inject(mainPresenter : MainPresenter)

    fun inject(notePresenter: NotePresenter)

}