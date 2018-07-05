package com.example.eugene.rxtestproject.domain.usecase.base;


import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class AbsUseCaseObs<Result, Params> extends AbsBaseUseCase {


    public abstract Observable<Result> buildUseCaseObservable(@NonNull Params params);

    public void execute(@NonNull DisposableObserver<Result> observer, @NonNull Params params) {
        final Observable<Result> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(observer));
    }

    public void execute(@NonNull DisposableObserver<Result> observer, @NonNull ObservableTransformer<Result, Result> transformer, @Nullable Params params) {
        final Observable<Result> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(transformer);
        addDisposable(observable.subscribeWith(observer));
    }
}
