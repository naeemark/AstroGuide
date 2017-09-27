package com.astro.guide.utils.parser;

import com.astro.guide.model.Channel;
import com.astro.guide.model.Event;
import com.astro.guide.model.response.channel.ChannelsResponse;
import com.astro.guide.model.response.channel.ChannelsResponseChannel;
import com.astro.guide.model.response.event.EventsResponse;
import com.astro.guide.model.response.event.EventsResponseGetevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 23/9/2017
 */

/**
 * A utility to provide parser
 */
@Singleton
public class ChannelParser {

    @Inject
    public ChannelParser() {
    }

    /**
     * Returns list of parsed elements
     * @param response
     * @return
     */
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

    /**
     * sets data into given map
     * @param eventsResponse
     * @param channelMap
     */
    public void mapEventsToChannels(EventsResponse eventsResponse, Map<Integer, Channel> channelMap) {
        if (eventsResponse != null) {
            EventsResponseGetevent[] events = eventsResponse.getGetevent();
            if (events != null) {
                for (EventsResponseGetevent e : events) {
                    Event event = new Event(
                            e.getEventID(),
                            e.getDisplayDateTimeUtc(),
                            e.getDisplayDateTime(),
                            e.getDisplayDuration(),
                            e.getContentId(),
                            e.getShortSynopsis(),
                            e.getActors(),
                            e.getEpgEventImage(),
                            e.getProgrammeTitle()
                    );
                    channelMap.get(e.getChannelId()).getEvents().add(event);
                }
            }
        }

    }
}
