package com.jifflenow.network;

import com.jifflenow.models.EventDetails;
import com.jifflenow.models.Events;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("demo/events")
    Call<List<Events>> getEvents();

    @GET("demo/{eventname}")
    Call<List<EventDetails>> getEventDetails(@Path("eventname") String eventName);

}
