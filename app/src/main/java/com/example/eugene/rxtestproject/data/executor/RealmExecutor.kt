package com.example.eugene.rxtestproject.data.executor

import android.os.HandlerThread
import com.example.eugene.rxtestproject.domain.executor.ThreadExecutor
import com.example.eugene.rxtestproject.domain.executor.WorkExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class RealmExecutor(thread: HandlerThread) : ThreadExecutor(thread), WorkExecutionThread {

//    override val scheduler: Scheduler
//        get() = AndroidSchedulers.from(getThread().looper)
}