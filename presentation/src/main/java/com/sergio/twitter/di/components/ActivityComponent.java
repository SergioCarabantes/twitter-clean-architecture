package com.sergio.twitter.di.components;

import android.app.Activity;
import com.sergio.twitter.di.scopes.ActivityScope;
import com.sergio.twitter.di.modules.ActivityModule;
import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activityContext();
}
