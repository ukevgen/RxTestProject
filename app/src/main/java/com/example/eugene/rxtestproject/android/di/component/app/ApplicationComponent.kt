package com.example.eugene.rxtestproject.android.di.component.app

import android.app.Activity
import com.example.eugene.rxtestproject.RxApplication
import com.example.eugene.rxtestproject.android.di.module.ApplicationModule
import com.example.eugene.rxtestproject.android.di.module.ExecutorsModule
import com.example.eugene.rxtestproject.android.ui.main.MainActivity
import com.example.eugene.rxtestproject.android.ui.main.di.MainActivityComponent
import com.example.eugene.rxtestproject.data.cache.di.CacheModule
import com.example.eugene.rxtestproject.data.cache.realm.dao.di.DaoRealmModule
import com.example.eugene.rxtestproject.data.di.DataModule
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class),
    (DaoRealmModule::class),
    (DataModule::class),
    (CacheModule::class),
    (ExecutorsModule::class),
    (AndroidSupportInjectionModule::class),
    (ApplicationComponent.ActivityBindingsModule::class),
    (ApplicationComponent.FragmentBindingsModule::class),
    (ApplicationComponent.ServiceBindingsModule::class)
])
interface ApplicationComponent : AndroidInjector<RxApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RxApplication>() {
        abstract fun applicationModule(module: ApplicationModule): Builder
    }

    @Module(subcomponents = [
        (MainActivityComponent::class)
    ])
    interface ActivityBindingsModule {

        @Binds
        @IntoMap
        @ActivityKey(value = MainActivity::class)
        fun mainActivityComponentBuilder(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    }

    @Module(subcomponents = [

    ])
    interface FragmentBindingsModule {

    }

    @Module(subcomponents = [

    ])
    interface ServiceBindingsModule {

    }
}