package com.sergio.twitter.domain.utils;

import io.reactivex.disposables.Disposable;

public interface Interactor<REQUEST extends Request, OUTPUT extends Output> {

    Disposable execute(REQUEST request, OUTPUT output);

}
