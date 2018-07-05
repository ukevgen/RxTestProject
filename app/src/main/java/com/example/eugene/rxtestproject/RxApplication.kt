package com.example.eugene.rxtestproject

import android.app.Activity
import android.app.Application
import android.app.Service
import android.support.v4.app.Fragment
import com.example.eugene.rxtestproject.android.di.component.app.DaggerApplicationComponent
import com.example.eugene.rxtestproject.android.di.module.ApplicationModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import dagger.android.support.HasSupportFragmentInjector
import io.realm.Realm
import javax.inject.Inject

class RxApplication : Application(), HasActivityInjector, HasSupportFragmentInjector, HasServiceInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .create(this)
                .inject(this)

        Realm.init(this)
    }

    override fun activityInjector() = androidInjector
    override fun supportFragmentInjector() = fragmentInjector
    override fun serviceInjector() = serviceInjector
}