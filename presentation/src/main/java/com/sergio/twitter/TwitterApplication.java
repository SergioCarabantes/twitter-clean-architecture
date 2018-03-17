package com.sergio.twitter;

import android.app.Application;
import com.sergio.twitter.di.components.ApplicationComponent;
import com.sergio.twitter.di.components.DaggerApplicationComponent;
import com.sergio.twitter.di.modules.ApplicationModule;
import timber.log.Timber;

public class TwitterApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initTimber();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.tag("Twitter");
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
