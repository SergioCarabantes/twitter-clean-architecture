/*
 * Copyright (C) 2018 Sergio Carabantes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    protected void disableIconInToolbar() {
        if(toolbar != null) {
            toolbar.setNavigationIcon(null);
        }
    }

    protected void disableTitleInToolbar() {
        if(toolbar != null) {
            //toolbar.setTitle(null);
        }
    }
}
