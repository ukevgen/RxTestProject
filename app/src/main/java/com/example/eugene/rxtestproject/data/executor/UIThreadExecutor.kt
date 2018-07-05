package com.example.eugene.rxtestproject.data.executor

import com.example.eugene.rxtestproject.domain.executor.UIExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UIThreadExecutor @Inject constructor() : UIExecutionThread {


}