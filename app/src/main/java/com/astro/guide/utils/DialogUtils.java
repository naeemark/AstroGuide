package com.astro.guide.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.astro.guide.R;
import com.astro.guide.features.login.view.impl.LoginActivity;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 26/9/2017
 */

public class DialogUtils {

    /**
     * Shows a dialog to prompt that login is required
     * @param context
     */
    public static void showLoginAlertDialog(final Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage(R.string.prompt_please_login)
                .setCancelable(true)
                .setPositiveButton(R.string.lbl_login, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.lbl_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    /**
     * Shows About
     * @param context
     * @param <V>
     */
    public static <V> void showAboutDialog(Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage(R.string.lbl_about)
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    /**
     * Not in use
     * @param context
     * @param prompt
     * @param positiveListener
     */
    public static void showLogoutPromptDialog(Context context, String prompt, DialogInterface.OnClickListener positiveListener) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage(prompt)
                .setCancelable(true)
                .setPositiveButton(android.R.string.yes, positiveListener)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
}
