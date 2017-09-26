package com.astro.guide.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.astro.guide.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 27/9/2017
 */

public class ImageUtils {

    public static void loadImage(Context context, final ImageView target, String url) {
        loadImage(context, target, Uri.parse(url));
    }

    public static void loadImage(Context context, final ImageView target, Uri uri) {
        Glide.with(context)
                .load((uri == null) ? R.drawable.ic_launcher_round_web : uri)
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher_round)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap arg0, GlideAnimation<? super Bitmap> arg1) {
                        target.setImageBitmap(arg0);
                    }
                });
    }
}
