package com.sergio.twitter.di.components;

import android.content.Context;
import com.sergio.twitter.BaseActivity;
import com.sergio.twitter.di.ApplicationScope;
import com.sergio.twitter.di.modules.ApplicationModule;
import dagger.Component;

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();
}
