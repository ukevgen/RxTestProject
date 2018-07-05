package com.example.eugene.rxtestproject.android.di.module

import android.os.HandlerThread
import com.example.eugene.rxtestproject.android.di.qualifier.thread.DataThread
import com.example.eugene.rxtestproject.data.executor.IORouteDataWorkerExecutor
import com.example.eugene.rxtestproject.data.executor.RealmExecutor
import com.example.eugene.rxtestproject.domain.executor.IOExecutionThread
import com.example.eugene.rxtestproject.domain.executor.WorkExecutionThread
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class ExecutorsModule {


    @Provides
    @DataThread
    fun provideRouteWorkExecutor(): IOExecutionThread {
        val thread = HandlerThread("Data thread ${Calendar.getInstance().time}")
        thread.start()



        return IORouteDataWorkerExecutor(AndroidSchedulers.from(thread.looper))
    }

    @Provides
    @Singleton
    fun provideDatabaseExecutor(): WorkExecutionThread = RealmExecutor(HandlerThread("RealmThread"))
}