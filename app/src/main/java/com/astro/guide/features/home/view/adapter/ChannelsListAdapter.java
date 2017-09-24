package com.astro.guide.features.home.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astro.guide.R;
import com.astro.guide.features.home.view.holder.ChannelHolder;
import com.astro.guide.model.AppUser;
import com.astro.guide.model.Channel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 23/9/2017
 */

public class ChannelsListAdapter extends RecyclerView.Adapter<ChannelHolder> {

    private static final String TAG = ChannelsListAdapter.class.getSimpleName();
    private List<Channel> mChannelList = new ArrayList<>();

    @Inject
    protected AppUser mAppUser;

    @Inject
    public ChannelsListAdapter() {
    }

    @Override
    public ChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);
        return new ChannelHolder(view, mAppUser);
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
        Timber.e(mAppUser.toString());
        mChannelList.addAll(channels);
        notifyDataSetChanged();
    }
}
