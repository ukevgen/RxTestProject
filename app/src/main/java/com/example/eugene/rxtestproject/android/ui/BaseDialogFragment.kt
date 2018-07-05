package com.example.eugene.rxtestproject.android.ui

import android.content.Context
import android.support.v4.app.DialogFragment
import com.eway.presentation.Presenter
import com.eway.presentation.PresenterView
import dagger.android.support.AndroidSupportInjection

abstract class BaseDialogFragment : DialogFragment() {

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
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

    protected abstract fun getFragmentPresenter(): Presenter<out PresenterView>
}