package com.astro.guide.features.home.presenter;

import com.astro.guide.app.presenter.BasePresenter;
import com.astro.guide.features.home.interactor.HomeInteractor;
import com.astro.guide.features.home.view.HomeView;

public interface HomePresenter extends BasePresenter<HomeView>, HomeInteractor.OnFetchDataListener {

    void onFabClicked();

    void checkNetwork();

    void showLoading();

    void hideLoading();

    void fetchDataFromApi();

    void onNavigationItemSelected(int itemId);

    void onRefreshClicked();
}