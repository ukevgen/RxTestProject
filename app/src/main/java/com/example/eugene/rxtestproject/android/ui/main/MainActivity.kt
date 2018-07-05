package com.example.eugene.rxtestproject.android.ui.main

import android.os.Bundle
import com.example.eugene.rxtestproject.R
import com.example.eugene.rxtestproject.android.ui.BaseActivity
import com.example.eugene.rxtestproject.presentation.main.MainPresenter
import com.example.eugene.rxtestproject.presentation.main.MainView
import io.reactivex.Observer
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var refreshObserver : Observer<Any>

    override fun getActivityPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle.setOnClickListener { presenter.togglePlacesDb() }
        refresh.setOnClickListener { refreshObserver.onNext(Unit) }
    }
}
