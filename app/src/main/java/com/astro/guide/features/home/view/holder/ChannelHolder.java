package com.astro.guide.features.home.view.holder;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.astro.guide.R;
import com.astro.guide.features.details.ItemDetailsActivity;
import com.astro.guide.features.home.view.HomeView;
import com.astro.guide.model.AppUser;
import com.astro.guide.model.Channel;
import com.astro.guide.utils.DialogUtils;
import com.astro.guide.utils.ImageUtils;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_CONTENT_DESCRIPTION;
import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_CONTENT_SUBTITLE;
import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_CONTENT_TITLE;
import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_CONTENT_URL;
import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_LABEL_HEADER;

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
    private Channel mChannel;

    private AppUser mAppUser;

    public ChannelHolder(View itemView, AppUser appUser) {
        super(itemView);
        mAppUser = appUser;
        mContext = itemView.getContext();
        setIsRecyclable(false);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setValues(Channel channel) {

        mChannel = channel;
        mTitle.setText(mChannel.getTitle());
        mNumber.setText(mContext.getString(R.string.stb_prefix, mChannel.getStbNumber()));

        if (mAppUser.isHideFavouriteButton()) {
            mFavButton.setVisibility(View.GONE);
        } else {
            showFavButton();
            if (mAppUser.isLoggedIn()) {
                mFavButton.setOnFavoriteChangeListener(this);
            } else {
                mFavButton.setOnClickListener(this);
            }
        }

        ImageUtils.loadImage(mContext, mLogoImageView, mChannel.getLogoUrl());

    }

    private void showFavButton() {
        // To avoid inconsistency, set null as FavoriteChangeListener
        mFavButton.setOnFavoriteChangeListener(null);
        mFavButton.setFavorite(mAppUser.getFavouritesIds().contains(mChannel.getId()), false);
        mFavButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_fav) {
            DialogUtils.showLoginAlertDialog(mContext);
        } else {
            Intent intent = new Intent(mContext, ItemDetailsActivity.class);

            intent.putExtra(EXTRA_LABEL_HEADER, mContext.getString(R.string.title_details));
            intent.putExtra(EXTRA_CONTENT_URL, mChannel.getLogoUrl());
            intent.putExtra(EXTRA_CONTENT_TITLE, mChannel.getTitle());
            intent.putExtra(EXTRA_CONTENT_SUBTITLE, mContext.getString(R.string.stb_prefix, mChannel.getStbNumber()));
            intent.putExtra(EXTRA_CONTENT_DESCRIPTION, mChannel.getDescription());

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, mLogoImageView, "TransitionName");
                mContext.startActivity(intent, options.toBundle());
            } else {
                mContext.startActivity(intent);
            }
        }
    }

    @Override
    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
        mChannel.setFavourite(favorite);
        updateFavourite(mContext, mChannel, favorite);
    }

    public void updateFavourite(Context context, Channel channel, boolean isFavorite) {
        if (mAppUser.getFavouritesIds().contains(channel.getId())) {
            mAppUser.getFavouritesIds().remove(channel.getId());
        } else {
            mAppUser.getFavouritesIds().add(channel.getId());
        }
        Timber.e(mAppUser.toString());
        ((HomeView) context).updateCache();
    }
}
