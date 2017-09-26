package com.astro.guide.webapi;

import com.astro.guide.model.response.event.EventsResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 25/9/2017
 */

public interface EventsApiService {

    @GET("/ams/v3/getEvents")
    Observable<EventsResponse> getEventsList(@Query("periodStart") String periodStart, @Query("periodEnd") String periodEnd, @Query("channelId") String channelIds);
}
