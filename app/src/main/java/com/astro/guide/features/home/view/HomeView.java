package com.astro.guide.features.home.view;

import android.support.annotation.UiThread;

import com.astro.guide.app.view.BaseView;
import com.astro.guide.model.Channel;

import java.util.List;

@UiThread
public interface HomeView extends BaseView {

    void showErrorLoading();

    void loadList(List<Channel> channelList);

    void showInfo();

    void setSortButtonChecked(int sortOrderOrdinal);

    void updateCache();
}