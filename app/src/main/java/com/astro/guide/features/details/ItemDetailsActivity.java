package com.astro.guide.features.details;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.astro.guide.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_LABEL_HEADER = "EXTRA_ACTIVITY_LABEL";
    public static final String EXTRA_CONTENT_URL = "EXTRA_CONTENT_URL";
    public static final String EXTRA_CONTENT_TITLE = "EXTRA_CONTENT_TITLE";
    public static final String EXTRA_CONTENT_SUBTITLE = "EXTRA_CONTENT_SUBTITLE";
    public static final String EXTRA_CONTENT_DESCRIPTION = "EXTRA_CONTENT_DESCRIPTION";

    @BindView(R.id.logoImage)
    protected ImageView mLogoImage;
    @BindView(R.id.title)
    protected TextView mTitle;
    @BindView(R.id.subTitle)
    protected TextView mSubTitle;
    @BindView(R.id.description)
    protected TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        showBackArrow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mLogoImage.setTransitionName("TransitionName");
        }

        setTitle(getIntent().getStringExtra(EXTRA_LABEL_HEADER));
        mTitle.setText(getIntent().getStringExtra(EXTRA_CONTENT_TITLE));
        mSubTitle.setText(getIntent().getStringExtra(EXTRA_CONTENT_SUBTITLE));
        mDescription.setText(getIntent().getStringExtra(EXTRA_CONTENT_DESCRIPTION));
        loadImage(getIntent().getStringExtra(EXTRA_CONTENT_URL));

    }

    private void loadImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher_round)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap arg0, GlideAnimation<? super Bitmap> arg1) {
                        mLogoImage.setImageBitmap(arg0);
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
