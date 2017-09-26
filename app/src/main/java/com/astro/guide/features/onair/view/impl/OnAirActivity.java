package com.astro.guide.features.onair.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.astro.guide.R;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.app.view.impl.BaseActivity;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.onair.injection.DaggerOnAirViewComponent;
import com.astro.guide.features.onair.injection.OnAirViewModule;
import com.astro.guide.features.onair.presenter.OnAirPresenter;
import com.astro.guide.features.onair.view.OnAirView;
import com.astro.guide.features.onair.view.adapter.ChannelsEventsAdapter;
import com.astro.guide.model.Channel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public final class OnAirActivity extends BaseActivity<OnAirPresenter, OnAirView> implements OnAirView {
    @Inject
    PresenterFactory<OnAirPresenter> mPresenterFactory;

    @BindView(R.id.radioGroup_sort)
    RadioGroup mSortRadioGroup;

    @BindView(R.id.radioButton_sortByName)
    RadioButton mNameRadioButton;

    @BindView(R.id.radioButton_sortByNumber)
    RadioButton mNumberRadioButton;

    private RadioButton[] radioButtons;

    @BindView(R.id.textView_prompt)
    protected TextView mPromptTextView;

    @BindView(R.id.recyclerView_home)
    protected RecyclerView mChannelsEventRecyclerView;

    private ArrayList<Channel> mChannelList;

    @Inject
    protected ChannelsEventsAdapter mChannelsEventsAdapter;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerOnAirViewComponent.builder()
                .appComponent(parentComponent)
                .onAirViewModule(new OnAirViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<OnAirPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            assert mPresenter != null;
            mPresenter.onRefreshClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mChannelList!=null && !mChannelList.isEmpty()){
            mChannelsEventsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_onair;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        showBackArrow();
        initializeRadioButtons();
        initList();
    }


    private void initializeRadioButtons() {
        radioButtons = new RadioButton[]{mNameRadioButton, mNumberRadioButton};
        mSortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_sortByName:
                        assert mPresenter != null;
                        mPresenter.sortChannelsList(mChannelList, AppConstants.SortOrder.SORT_BY_NAME);
                        break;
                    case R.id.radioButton_sortByNumber:
                        mPresenter.sortChannelsList(mChannelList, AppConstants.SortOrder.SORT_BY_NUMBER);
                        break;
                }
            }
        });
    }

    private void initList() {
        mChannelsEventRecyclerView.setHasFixedSize(true);
        mChannelsEventRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChannelsEventRecyclerView.setAdapter(mChannelsEventsAdapter);
    }

    @Override
    public void setSortButtonChecked(int sortOrderOrdinal) {
        radioButtons[sortOrderOrdinal].setChecked(true);
    }

    private void showList() {
        mChannelsEventsAdapter.clearList();
        mChannelsEventsAdapter.addChannels(mChannelList);
        mChannelsEventsAdapter.notifyDataSetChanged();
        mChannelsEventRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPrompt(String promptText) {
        mPromptTextView.setText(promptText);
        mPromptTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadList(List<Channel> channelList) {
        Timber.e("channelListSize: " + channelList.size());
        mChannelList = (ArrayList<Channel>) channelList;
        showList();
    }
}
