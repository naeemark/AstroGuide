package com.astro.guide.utils.parser;

import com.astro.guide.model.Channel;
import com.astro.guide.model.response.channel.ChannelsResponse;
import com.astro.guide.model.response.channel.ChannelsResponseChannel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 23/9/2017
 */

@Singleton
public class ChannelParser {

    @Inject
    public ChannelParser() {
    }

    public List<Channel> parseChannels(ChannelsResponse response) {
        List<Channel> channelList = new ArrayList<>();

        if (response != null) {
            ChannelsResponseChannel[] responseChannels = (response.getChannels() == null) ? response.getChannel() : response.getChannels();
            if (responseChannels != null) {
                for (ChannelsResponseChannel ch : responseChannels) {
                    Channel channel = new Channel(ch.getChannelId(), ch.getChannelTitle(), ch.getChannelStbNumber(), ch.getChannelDescription());
                    channel.setLogoUrl((ch.getChannelExtRef() == null) ? null : ch.getChannelExtRef()[0].getValue());
                    channelList.add(channel);
                }
            }
        }
        return channelList;
    }
}
