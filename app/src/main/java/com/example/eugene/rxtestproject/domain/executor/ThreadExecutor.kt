package com.example.eugene.rxtestproject.domain.executor

import android.os.HandlerThread

abstract class ThreadExecutor (val threadR: HandlerThread){

   protected fun getThread(): HandlerThread {
        if (!threadR.isAlive) threadR.start()
        return threadR
    }
}