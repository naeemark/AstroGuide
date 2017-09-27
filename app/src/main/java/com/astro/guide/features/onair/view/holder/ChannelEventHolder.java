package com.astro.guide.features.onair.view.holder;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astro.guide.R;
import com.astro.guide.features.details.ItemDetailsActivity;
import com.astro.guide.model.Channel;
import com.astro.guide.model.Event;
import com.astro.guide.utils.DateTimeUtils;
import com.astro.guide.utils.ImageUtils;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_CONTENT_DESCRIPTION;
import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_CONTENT_SUBTITLE;
import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_CONTENT_TITLE;
import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_CONTENT_URL;
import static com.astro.guide.features.details.ItemDetailsActivity.EXTRA_LABEL_HEADER;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 25/09/2017
 */

public class ChannelEventHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.linearLayout_channel)
    protected LinearLayout channelLayout;

    @BindView(R.id.linearLayout_event)
    protected LinearLayout eventLayout;

    @BindView(R.id.logo_icon)
    protected ImageView mLogoImageView;

    @BindView(R.id.textview_channel_number)
    protected TextView mChannelNumber;

    @BindView(R.id.textview_channel_name)
    protected TextView mChannelName;

    @BindView(R.id.textview_eventTitle)
    protected TextView mEventTitle;

    @BindView(R.id.textview_eventTime)
    protected TextView mEventTime;


    private Context mContext;


    public ChannelEventHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void setValues(final Channel channel) {
        mChannelNumber.setText(mContext.getString(R.string.stb_prefix, channel.getStbNumber()));
        mChannelName.setText(channel.getTitle());

        ImageUtils.loadImage(mContext, mLogoImageView, channel.getLogoUrl());


        View.OnClickListener channelClickListener = getEventClickListener(
                mContext.getString(R.string.title_details), channel.getTitle(),
                mContext.getString(R.string.stb_prefix, channel.getStbNumber()),
                channel.getDescription(), channel.getLogoUrl(), null);
        channelLayout.setOnClickListener(channelClickListener);
        mLogoImageView.setOnClickListener(channelClickListener);


        Event currentEvent = DateTimeUtils.getCurrentEvent(channel.getEvents());
        if (currentEvent == null) {
            mEventTitle.setText(R.string.prompt_no_event_found);
        } else {
            mEventTitle.setText(currentEvent.getProgrammeTitle());
            try {
                mEventTime.setText(mContext.getString(R.string.prefix_event_time_on_air, DateTimeUtils.getFormatedDateTime(currentEvent)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            eventLayout.setOnClickListener(getEventClickListener(
                    mContext.getString(R.string.title_details_event), currentEvent.getProgrammeTitle(),
                    mContext.getString(R.string.stb_prefix, channel.getTitle()),
                    currentEvent.getShortSynopsis(), currentEvent.getEpgEventImage(), channel.getLogoUrl()));
        }
    }

    private View.OnClickListener getEventClickListener(final String header, final String title, final String subTitle, final String description, final String channelLogo, final String eventImage) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ItemDetailsActivity.class);
                intent.putExtra(EXTRA_LABEL_HEADER, header);
                intent.putExtra(EXTRA_CONTENT_URL, (eventImage == null) ? channelLogo : eventImage);
                intent.putExtra(EXTRA_CONTENT_TITLE, title);
                intent.putExtra(EXTRA_CONTENT_SUBTITLE, subTitle);
                intent.putExtra(EXTRA_CONTENT_DESCRIPTION, description);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, mLogoImageView, "TransitionName");
                    mContext.startActivity(intent, options.toBundle());
                } else {
                    mContext.startActivity(intent);
                }
            }
        };
    }
}