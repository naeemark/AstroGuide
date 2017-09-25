package com.astro.guide.features.onair.view;

import android.support.annotation.UiThread;

import com.astro.guide.app.view.BaseView;
import com.astro.guide.model.Channel;

import java.util.List;

@UiThread
public interface OnAirView extends BaseView {

    void setSortButtonChecked(int sortOrderOrdinal);

    void showPrompt(String promptText);

    void loadList(List<Channel> channels);
}