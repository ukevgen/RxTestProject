package com.example.eugene.rxtestproject.android.ui.main.di

import android.support.v7.app.AppCompatActivity
import com.example.eugene.rxtestproject.android.di.scopes.PerActivity
import com.example.eugene.rxtestproject.android.ui.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.BehaviorSubject

@PerActivity
@Subcomponent(modules = [
    (MainActivityComponent.ActivityBindsModule::class),
    (MainActivityComponent.FragmentBindingsModule::class),
    (MainActivityComponent.ActivityModule::class),
    (MainActivityComponent.BindingsModule::class)
])
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

    @Module
    interface ActivityBindsModule {

        @Binds
        fun provideActivityModule(activity: MainActivity): AppCompatActivity

    }

    @Module
    class ActivityModule {
        val refreshDataSubject = BehaviorSubject.createDefault(Any())

        @PerActivity
        @Provides
        fun provideRefreshDataObservable(): Observable<Any> = refreshDataSubject

        @PerActivity
        @Provides
        fun provideRefreshDataObserver(): Observer<Any> = refreshDataSubject
    }

    @Module(subcomponents = [

    ])
    interface FragmentBindingsModule {

    }

    @Module
    interface BindingsModule {

    }
}