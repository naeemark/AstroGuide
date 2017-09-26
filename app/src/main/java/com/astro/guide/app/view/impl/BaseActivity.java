package com.astro.guide.app.view.impl;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.astro.guide.R;
import com.astro.guide.app.AstroGuideApp;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.app.presenter.BasePresenter;
import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.app.presenter.loader.PresenterLoader;
import com.astro.guide.app.view.BaseView;
import com.astro.guide.utils.DialogUtils;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter<V>, V> extends AppCompatActivity implements BaseView, LoaderManager.LoaderCallbacks<P> {
    /**
     * Do we need to call {@link #doStart()} from the {@link #onLoadFinished(Loader, BasePresenter)} method.
     * Will be true if presenter wasn't loaded when {@link #onStart()} is reached
     */
    private final AtomicBoolean mNeedToCallStart = new AtomicBoolean(false);
    /**
     * The presenter for this view
     */
    @Nullable
    protected P mPresenter;
    /**
     * Is this the first start of the activity (after onCreate)
     */
    private boolean mFirstStart;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirstStart = true;

        injectDependencies();

        getSupportLoaderManager().initLoader(0, null, this).startLoading();

        setContentView(getContentView());

        ButterKnife.bind(this);

        onViewReady(savedInstanceState, getIntent());
    }

    private void injectDependencies() {
        setupComponent(((AstroGuideApp) getApplication()).getAppComponent());
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mPresenter == null) {
            mNeedToCallStart.set(true);
        } else {
            doStart();
        }
    }

    /**
     * Call the presenter callbacks for onStart
     */
    @SuppressWarnings("unchecked")
    private void doStart() {
        assert mPresenter != null;

        mPresenter.onViewAttached((V) this);

        mPresenter.onStart(mFirstStart);

        mFirstStart = false;
    }

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.onStop();

            mPresenter.onViewDetached();
        }
        super.onStop();
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, getPresenterFactory());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P presenter) {
        mPresenter = presenter;

        if (mNeedToCallStart.compareAndSet(true, false)) {
            doStart();
        }
    }

    @Override
    public final void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }

    /**
     * Get the presenter factory implementation for this view
     *
     * @return the presenter factory
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Setup the injection component for this view
     *
     * @param appComponent the app component
     */
    protected abstract void setupComponent(@NonNull AppComponent appComponent);

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

    }

    protected abstract int getContentView();

    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        this.showLoading(getString(R.string.loading));
    }

    @Override
    public void showLoading(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showErrorWithMessage(String errorText) {
        showToast(errorText);
    }

    @Override
    public void showErrorLoading() {
        showToast(getString(R.string.error_loading_data));
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError() {
        showToast(getString(R.string.error_no_network));
    }

    protected void showAbout() {
        DialogUtils.showAboutDialog(this);
    }

    protected void showLogoutDialog(String prompt, DialogInterface.OnClickListener positiveListener) {
        DialogUtils.showLogoutPromptDialog(this, prompt, positiveListener);
    }
}