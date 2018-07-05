package com.example.eugene.rxtestproject.android.ui

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.eway.presentation.Presenter
import com.eway.presentation.PresenterView
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    override fun onStart() {
        super.onStart()

        getActivityPresenter().viewIsShown()
    }

    override fun onStop() {
        getActivityPresenter().viewIsHidden()

        super.onStop()
    }

    override fun onDestroy() {

        getActivityPresenter().dropView()
        getActivityPresenter().destroy()


        super.onDestroy()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    protected abstract fun getActivityPresenter(): Presenter<out PresenterView>

    protected fun <F : BaseFragment> addFragment(fragment: F, @IdRes containerId: Int, tag: String?) {
        supportFragmentManager.beginTransaction()
                .add(containerId, fragment, tag)
                .commit()
    }

    protected fun <F : BaseFragment> replaceFragment(fragment: F, @IdRes containerId: Int, tag: String?) {
        supportFragmentManager
                .beginTransaction()
//                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(containerId, fragment, tag)
                .commit()
    }


}