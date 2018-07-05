package com.example.eugene.rxtestproject.android.di.module

import android.app.Application
import android.content.Context
import com.example.eugene.rxtestproject.android.di.AppQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    @AppQualifier
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @AppQualifier
    fun provideContext(): Context = application.applicationContext

}