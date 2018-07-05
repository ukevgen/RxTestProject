package com.example.eugene.rxtestproject.data.executor

import android.os.HandlerThread
import com.example.eugene.rxtestproject.domain.executor.IOExecutionThread
import com.example.eugene.rxtestproject.domain.executor.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class IOStopDataWorkerExecutor(val scheduler: Scheduler) :  IOExecutionThread {
    override fun getDataScheduler() = scheduler

//    override val scheduler: Scheduler
//        get() = AndroidSchedulers.from(getThread().looper)
}