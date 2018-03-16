package com.sergio.twitter.domain.utils;

import io.reactivex.disposables.Disposable;

public interface NoArgInteractor<OUTPUT extends Output> {

    Disposable execute(OUTPUT output);

}
