package com.astro.guide.features.login.injection;

import com.astro.guide.app.injection.ActivityScope;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.features.login.view.impl.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginViewModule.class)
public interface LoginViewComponent {
    void inject(LoginActivity activity);
}