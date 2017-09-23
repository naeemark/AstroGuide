package com.astro.guide.features.home.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.astro.guide.R;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.app.view.impl.BaseActivity;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.home.injection.DaggerHomeViewComponent;
import com.astro.guide.features.home.injection.HomeViewModule;
import com.astro.guide.features.home.presenter.HomePresenter;
import com.astro.guide.features.home.view.HomeView;
import com.astro.guide.features.home.view.adapter.ChannelsAdapter;
import com.astro.guide.model.Channel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class HomeActivity extends BaseActivity<HomePresenter, HomeView> implements HomeView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawer;

    @BindView(R.id.nav_view)
    protected NavigationView navigationView;

    @BindView(R.id.radioGroup_sort)
    RadioGroup mSortRadioGroup;

    @BindView(R.id.radioButton_sortByName)
    RadioButton mNameRadioButton;

    @BindView(R.id.radioButton_sortByNumber)
    RadioButton mNumberRadioButton;

    private RadioButton[] radioButtons;

    @BindView(R.id.recyclerView_home)
    protected RecyclerView mChannelsRecyclerView;

    private ArrayList<Channel> mChannelList;

    @Inject
    protected ChannelsAdapter mChannelsAdapter;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerHomeViewComponent.builder()
                .appComponent(parentComponent)
                .homeViewModule(new HomeViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        setTitle(getString(R.string.title_activity_home));
        initializeRadioButtons();

        initDrawerLayout();
        initList();
    }

    private void loadData() {
        assert mPresenter != null;
        mPresenter.fetchData();
    }

    private void initDrawerLayout() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initializeRadioButtons() {
        radioButtons = new RadioButton[]{mNameRadioButton, mNumberRadioButton};
        mSortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                assert mPresenter != null;
                assert mChannelList != null;
                switch (checkedId) {
                    case R.id.radioButton_sortByName:
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
        mChannelsRecyclerView.setHasFixedSize(true);
        mChannelsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChannelsAdapter.hideFavoriteIcon();
        mChannelsRecyclerView.setAdapter(mChannelsAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        assert mPresenter != null;
        mPresenter.onNavigationItemSelected(item.getItemId());

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        assert mPresenter != null;
        mPresenter.onFabClicked();
    }

    @Override
    public void showErrorLoading() {
        super.showToast(getString(R.string.error_loading_data));
    }

    @Override
    public void loadList(List<Channel> channelList) {
        Timber.e("channelList: " + channelList.toString());
        mChannelList = (ArrayList<Channel>) channelList;
        showList();
    }

    @Override
    public void showInfo() {
        super.showAbout();
    }

    private void showList() {
        mChannelsAdapter.clearList();
        mChannelsAdapter.addChannels(mChannelList);
        mChannelsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSortButtonChecked(int sortOrderOrdinal) {
        radioButtons[sortOrderOrdinal].setChecked(true);
    }
}
