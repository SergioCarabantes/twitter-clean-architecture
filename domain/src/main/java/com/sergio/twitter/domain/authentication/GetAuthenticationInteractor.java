package com.sergio.twitter.domain.authentication;

import com.sergio.twitter.domain.local.PreferenceRepository;
import com.sergio.twitter.domain.utils.NoArgInteractor;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class GetAuthenticationInteractor implements NoArgInteractor<GetAuthenticationOutput> {

    private AuthenticationRepository authenticationRepository;
    private PreferenceRepository preferenceRepository;

    @Inject
    GetAuthenticationInteractor(AuthenticationRepository authenticationRepository, PreferenceRepository preferenceRepository) {
        this.authenticationRepository = authenticationRepository;
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    public Disposable execute(GetAuthenticationOutput output) {
        return authenticationRepository.getAuthentication()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe((authentication, throwable) -> {
                    if (authentication == null) {
                        output.onUnknownError(throwable);
                    } else {
                        preferenceRepository.saveAccessToken(authentication.getAccessToken());
                        output.onAuthenticationSuccess();
                    }
                });
    }
}
