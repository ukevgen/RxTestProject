package com.example.eugene.rxtestproject.data.executor

import com.example.eugene.rxtestproject.domain.executor.IOExecutionThread
import io.reactivex.Scheduler

class IORouteDataWorkerExecutor(val scheduler: Scheduler) : IOExecutionThread {
    override fun getDataScheduler() = scheduler

//    override val scheduler = Schedulers.io()
//        get() = Schedulers.io()
}