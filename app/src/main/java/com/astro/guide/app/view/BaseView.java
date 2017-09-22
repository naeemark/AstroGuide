package com.astro.guide.app.view;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 22/9/2017
 */

public interface BaseView {
    void showProgress(String message);

    void hideProgress();

    void showErrorWithMessage(String errorText);

    void showToast(String message);
}
