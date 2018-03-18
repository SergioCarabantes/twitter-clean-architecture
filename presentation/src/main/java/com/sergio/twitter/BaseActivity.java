package com.sergio.twitter;

import android.os.Bundle;
import javax.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sergio.twitter.di.components.ApplicationComponent;
import com.sergio.twitter.navigator.Navigator;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity  {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Inject
    protected Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        configureToolbar();

    }

    protected ApplicationComponent getApplicationComponent() {
        return ((TwitterApplication) getApplication()).getApplicationComponent();
    }

    public abstract int getLayoutResource();

    protected void configureToolbar() {
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_action_arrow_back);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
            setSupportActionBar(toolbar);
        }
    }

    protected void disableIconInToolbar() {
        if(toolbar != null) {
            toolbar.setNavigationIcon(null);
        }
    }
}
