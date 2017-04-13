package com.islavstan

import android.content.Context
import com.activeandroid.app.Application
import com.islavstan.di.AppComponent
import com.islavstan.di.DaggerAppComponent
import com.islavstan.di.NoteDaoModule
import com.islavstan.utils.initPrefs


//унаследуем класс Application от com.activeandroid.app.Application:
class NotelinApplication : Application() {


    companion object {
        lateinit var graph: AppComponent
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        initPrefs(this)
        context = this
        graph = DaggerAppComponent.builder().noteDaoModule(NoteDaoModule()).build()
    }

}
