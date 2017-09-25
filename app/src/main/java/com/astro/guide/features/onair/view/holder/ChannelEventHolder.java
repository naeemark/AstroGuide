package com.astro.guide.features.onair.view.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astro.guide.R;
import com.astro.guide.model.Channel;
import com.astro.guide.model.Event;
import com.astro.guide.utils.DateTimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 25/09/2017
 */

public class ChannelEventHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.linearLayout_channel)
    protected LinearLayout channelLayout;

    @BindView(R.id.logo_icon)
    protected ImageView mLogoImageView;

    @BindView(R.id.textview_channel)
    protected TextView mChannel;

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
        mChannel.setText(String.valueOf(channel.getStbNumber()) + "#"+channel.getTitle());


        Event currentEvent = DateTimeUtils.getCurrentEvent(channel.getEvents());

        if (currentEvent==null){
            mEventTitle.setText(R.string.prompt_no_event_found);
        }else{
            mEventTitle.setText(currentEvent.getProgrammeTitle());
            try {
                mEventTime.setText(mContext.getString(R.string.prefix_event_time_on_air, DateTimeUtils.getFormatedDateTime(currentEvent)));
//                mEventTime.setText(mContext.getString(R.string.prefix_event_time_on_air, DateTimeUtils.convertDateToTime(currentEvent)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }



//        for (final Event event : channel.getEvents()) {
//            LayoutInflater inflater = LayoutInflater.from(mContext);
//            View inflatedLayout = inflater.inflate(R.layout.onnow_list_item_layout_inner, null, false);
//
//            TextView eventTitle = (TextView) inflatedLayout.findViewById(R.id.textview_eventTitle);
//            TextView eventTime = (TextView) inflatedLayout.findViewById(R.id.textview_eventTime);
//
//            eventTitle.setText(event.getProgrammeTitle());
//            eventTime.setText("Starts At:" + DateTimeUtils.getTime(event.getDisplayDateTime()));
//
//            inflatedLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, DetailActivity.class);
//                    intent.putExtra(DetailActivity.EVENT, event);
//                    mContext.startActivity(intent);
//                }
//            });
//
//            eventsLayout.addView(inflatedLayout);
//
//        }

        Glide.with(mContext)
                .load(channel.getLogoUrl())
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher_round)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap arg0, GlideAnimation<? super Bitmap> arg1) {
                        mLogoImageView.setImageBitmap(arg0);
                    }
                });
    }


}