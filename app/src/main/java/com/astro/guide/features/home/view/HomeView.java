package com.astro.guide.features.home.view;

import android.support.annotation.UiThread;

import com.astro.guide.app.view.BaseView;
import com.astro.guide.model.AppUser;
import com.astro.guide.model.Channel;

import java.util.List;

@UiThread
public interface HomeView extends BaseView {

    void loadList(List<Channel> channelList);

    void showInfo();

    void setDrawerHeaderData(AppUser appUser);

    void setSortButtonChecked(int sortOrderOrdinal);

    void updateCache();

    void launchFavouritesListActivity();

    boolean isForFavourites();

    void showPrompt(String promptText);

    void launchOnAirActivity();

    void launchLoginActivity();

    void logout();

    void showLoginAlertDialog();
}