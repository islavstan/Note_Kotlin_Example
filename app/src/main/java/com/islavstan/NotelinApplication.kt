package com.islavstan

import com.activeandroid.app.Application
import com.islavstan.di.AppComponent
import com.islavstan.di.DaggerAppComponent
import com.islavstan.di.NoteDaoModule


//унаследуем класс Application от com.activeandroid.app.Application:
class NotelinApplication : Application() {


    companion object {
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerAppComponent.builder().noteDaoModule(NoteDaoModule()).build()
    }

}
