package com.example.eugene.rxtestproject.domain.executor

import io.reactivex.Scheduler

interface IOExecutionThread : Executor {
    fun getDataScheduler () : Scheduler
}