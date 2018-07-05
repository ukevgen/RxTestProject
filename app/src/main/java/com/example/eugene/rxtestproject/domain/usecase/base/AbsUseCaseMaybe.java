package com.example.eugene.rxtestproject.domain.usecase.base;


import io.reactivex.Maybe;
import io.reactivex.MaybeTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class AbsUseCaseMaybe<Result, Params> extends AbsBaseUseCase {


    public abstract Maybe<Result> buildUseCaseObservable(@NonNull Params params);

    public void execute(@NonNull DisposableMaybeObserver<Result> observer, @Nullable Params params) {
        final Maybe<Result> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        addDisposable(observable.subscribeWith(observer));
    }

    public void execute(@NonNull DisposableMaybeObserver<Result> observer, @NonNull MaybeTransformer<Result, Result> transformer, @Nullable Params params) {
        final Maybe<Result> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(transformer);

        addDisposable(observable.subscribeWith(observer));
    }
}
