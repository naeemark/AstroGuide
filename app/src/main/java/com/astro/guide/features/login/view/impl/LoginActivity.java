package com.astro.guide.features.login.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.astro.guide.R;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.app.view.impl.BaseActivity;
import com.astro.guide.features.login.injection.DaggerLoginViewComponent;
import com.astro.guide.features.login.injection.LoginViewModule;
import com.astro.guide.features.login.presenter.LoginPresenter;
import com.astro.guide.features.login.view.LoginView;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.ImageUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public final class LoginActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {

    private static final int RC_SIGN_IN = 9001;
    @Inject
    PresenterFactory<LoginPresenter> mPresenterFactory;

    @BindView(R.id.imageView)
    protected ImageView mUserDpImageView;

    @BindView(R.id.textView_userName)
    protected TextView mUserNameTextView;

    @BindView(R.id.sign_in_button)
    protected SignInButton mLoginButton;

    @BindView(R.id.sign_out_button)
    protected Button mLogoutButton;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerLoginViewComponent.builder()
                .appComponent(parentComponent)
                .loginViewModule(new LoginViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<LoginPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        showBackArrow();

        initApiClient();
        setButtonListeners();
    }

    private void initApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    private void setButtonListeners() {

        mLoginButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                startLogin();
                break;
            case R.id.sign_out_button:
                assert mPresenter != null;
                mPresenter.logout();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //ToDO: Not required in current scope, but usefull for sharing auth from cache
//        mGoogleApiClient.connect();
//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//        assert mPresenter != null;
//        if (opr.isDone()) {
//            Timber.i("Got cached sign-in");
//            GoogleSignInResult result = opr.get();
//            mPresenter.handleSignInResult(result);
//        } else {
//            showLoading("Logging In");
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(GoogleSignInResult googleSignInResult) {
//                    hideLoading();
//                    mPresenter.handleSignInResult(googleSignInResult);
//                }
//            });
//        }
    }

    private void startLogin() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            assert mPresenter != null;
            mPresenter.handleSignInResult(result);
        }
    }

    @Override
    public void updateUi(AppUser appUser) {
        mUserNameTextView.setText(getString(R.string.prompt_welcom_prefix, appUser.getName()));
        ImageUtils.loadImage(this, mUserDpImageView, appUser.getPhotoUrl());
        if (appUser.isLoggedIn()) {
            mLogoutButton.setVisibility(View.VISIBLE);
            mLoginButton.setVisibility(View.GONE);
        } else {
            mLogoutButton.setVisibility(View.GONE);
            mLoginButton.setVisibility(View.VISIBLE);
            mUserDpImageView.setBackgroundResource(R.mipmap.ic_launcher_round);
        }
    }

    @Override
    public void logout() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Timber.e(status.getStatusMessage());
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.e(getString(R.string.error_connection_failed) + ": " + connectionResult);
        showErrorWithMessage(getString(R.string.error_connection_failed));
    }

}
