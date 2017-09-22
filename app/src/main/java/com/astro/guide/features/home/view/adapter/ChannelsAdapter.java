package com.astro.guide.features.home.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astro.guide.R;
import com.astro.guide.features.home.view.holder.ChannelHolder;
import com.astro.guide.model.Channel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 23/9/2017
 */

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelHolder> {

    private static final String TAG = ChannelsAdapter.class.getSimpleName();
    private boolean mHideFav;
    private List<Channel> mChannelList = new ArrayList<>();

    @Inject
    public ChannelsAdapter() {
        mHideFav = false;
    }

    @Override
    public ChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);
        return new ChannelHolder(view, mHideFav);
    }

    @Override
    public void onBindViewHolder(ChannelHolder holder, int position) {
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

    public void hideFavoriteIcon() {
        mHideFav = true;
    }
}
