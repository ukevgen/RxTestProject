package com.example.eugene.rxtestproject.android.ui


import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eway.presentation.Presenter
import com.eway.presentation.PresenterView
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


abstract class BaseFragment : Fragment(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onStart() {
        super.onStart()

        getFragmentPresenter().viewIsShown()
    }

    override fun onStop() {
        getFragmentPresenter().viewIsHidden()

        super.onStop()
    }

    override fun onDestroyView() {
        getFragmentPresenter().dropView()

        super.onDestroyView()
    }

    override fun onDestroy() {
        getFragmentPresenter().destroy()

        super.onDestroy()
    }

    // HasSupportFragmentInjector

    override fun supportFragmentInjector() = fragmentInjector

    @LayoutRes protected abstract fun getLayout(): Int
    protected abstract fun getFragmentPresenter(): Presenter<out PresenterView>
}
