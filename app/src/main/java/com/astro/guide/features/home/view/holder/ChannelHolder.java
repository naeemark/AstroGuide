package com.astro.guide.features.home.view.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.astro.guide.R;
import com.astro.guide.model.Channel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 23/9/2017
 */

public class ChannelHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MaterialFavoriteButton.OnFavoriteChangeListener {

    @BindView(R.id.imageView_logo)
    protected ImageView mLogoImageView;
    @BindView(R.id.textview_title)
    protected TextView mTitle;
    @BindView(R.id.textview_number)
    protected TextView mNumber;
    @BindView(R.id.button_fav)
    protected MaterialFavoriteButton mFavButton;

    private Context mContext;
    private boolean mHideFav;
    private Channel mChannel;
//    private AppUser mUserSettings;


    public ChannelHolder(View itemView, boolean hideFav) {
        super(itemView);
        mContext = itemView.getContext();
        setIsRecyclable(false);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mHideFav = hideFav;
//        mUserSettings = AstroApplication.appUserSettings;
    }

    public void setValues(Channel channel) {
        mChannel = channel;
        mTitle.setText(mChannel.getTitle());
        mNumber.setText(String.valueOf(mChannel.getStbNumber()));

        if (!mHideFav) {
            mFavButton.setFavorite(mChannel.isFavourite(), false);
            mFavButton.setVisibility(View.VISIBLE);
        } else {
            mFavButton.setVisibility(View.GONE);
        }

        Glide.with(mContext)
                .load(mChannel.getLogoUrl())
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher_round)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap arg0, GlideAnimation<? super Bitmap> arg1) {
                        mLogoImageView.setImageBitmap(arg0);
                    }
                });

//        if (mUserSettings.getUserEmail() == null) {
//            mFavButton.setOnClickListener(this);
//        } else {
//            mFavButton.setOnFavoriteChangeListener(this);
//        }
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.button_fav) {
//            showLoginAlert();
//        } else {
//            Intent intent = new Intent(mContext, DetailActivity.class);
//            intent.putExtra(DetailActivity.CHANNEL, mChannel);
//
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, mLogoImageView, "logoImageAnimation");
//                mContext.startActivity(intent, options.toBundle());
//            } else {
//                mContext.startActivity(intent);
//            }
//        }
    }

//    private void showLoginAlert() {
//        AlertDialog dialog = new AlertDialog.Builder(mContext)
//                .setMessage("Please Login before the action!")
//                .setCancelable(true)
//                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(mContext, SsoActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                        mContext.startActivity(intent);
//                        dialog.dismiss();
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .create();
//        dialog.show();
//
//    }

    @Override
    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
        mChannel.setFavourite(favorite);
//        mUserSettings.updateFavourite(mContext, mChannel, favorite);
    }
}
