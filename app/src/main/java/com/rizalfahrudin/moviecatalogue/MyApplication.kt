package com.rizalfahrudin.moviecatalogue

import android.app.Application
import com.rizalfahrudin.moviecatalogue.core.di.CoreComponent
import com.rizalfahrudin.moviecatalogue.core.di.DaggerCoreComponent
import com.rizalfahrudin.moviecatalogue.di.AppComponent
import com.rizalfahrudin.moviecatalogue.di.DaggerAppComponent

open class MyApplication: Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}