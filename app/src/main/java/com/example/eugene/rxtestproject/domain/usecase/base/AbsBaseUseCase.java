package com.example.eugene.rxtestproject.domain.usecase.base;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AbsBaseUseCase {

    private CompositeDisposable mDisposables;

    AbsBaseUseCase() {

    }

    public void dispose() {
        if (mDisposables != null && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    public void clear() {
        if (mDisposables != null) {
            mDisposables.clear();
        }
    }

    void addDisposable(@NonNull Disposable disposable) {
        this.mDisposables = new CompositeDisposable();
        mDisposables.add(disposable);
    }
}
