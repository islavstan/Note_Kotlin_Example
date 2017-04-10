package com.islavstan.di

import com.islavstan.presenters.MainPresenter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(NoteDaoModule::class))
interface AppComponent {

    fun inject(mainPresenter : MainPresenter)

}