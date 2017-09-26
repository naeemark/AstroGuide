package com.astro.guide.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.astro.guide.features.login.view.impl.LoginActivity;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 26/9/2017
 */

public class DialogUtils {

    public static void showLoginAlertDialog(final Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage("Please Login before the action!")
                .setCancelable(true)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
}
