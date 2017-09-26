package com.astro.guide.webapi;

import com.astro.guide.model.AppUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 26/9/2017
 */

public interface AppUserApiService {

    @GET("/api")
    Observable<AppUser> get(@Query("email") String email);

    @FormUrlEncoded
    @POST("/api")
    Call<String> post(@Field("email") String email, @Field("data") String data);
}
