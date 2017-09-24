package com.astro.guide.webapi;


import com.astro.guide.model.response.channel.ChannelsResponse;

import retrofit2.http.GET;
import rx.Observable;


public interface ChannelsApiService {

    @GET("/ams/v3/getChannelList")
    Observable<ChannelsResponse> getChannelsList();

    @GET("/ams/v3/getChannels")
    Observable<ChannelsResponse> getChannels();
}
