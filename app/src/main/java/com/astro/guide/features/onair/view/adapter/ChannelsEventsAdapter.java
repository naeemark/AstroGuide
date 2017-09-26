package com.astro.guide.features.onair.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astro.guide.R;
import com.astro.guide.features.onair.view.holder.ChannelEventHolder;
import com.astro.guide.model.Channel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 25/9/2017
 */

public class ChannelsEventsAdapter extends RecyclerView.Adapter<ChannelEventHolder> {

    private static final String TAG = ChannelsEventsAdapter.class.getSimpleName();
    private List<Channel> mChannelList = new ArrayList<>();

    @Inject
    public ChannelsEventsAdapter() {
    }

    @Override
    public ChannelEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onair_list_item_layout, parent, false);
        return new ChannelEventHolder(view);
    }

    @Override
    public void onBindViewHolder(ChannelEventHolder holder, int position) {
        holder.setValues(mChannelList.get(position));
    }

    @Override
    public int getItemCount() {
        return mChannelList.size();
    }


    public void clearList() {
        mChannelList.clear();
        notifyDataSetChanged();
    }

    public void addChannels(List<Channel> channels) {
        mChannelList.addAll(channels);
        notifyDataSetChanged();
    }
}

