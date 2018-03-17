package com.sergio.twitter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import javax.annotation.Nullable;

public abstract class BasePresenter  {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void onResume() {
        resume();
    }

    public void onDestroy() {
        destroy();
    }

    protected void clearSubscriptions() {
        compositeDisposable.clear();
    }

    protected void registerDisposable(@Nullable Disposable disposable) {
        if (disposable != null) {
            compositeDisposable.add(disposable);
        }
    }

    public abstract void resume();

    public abstract void destroy();

}
