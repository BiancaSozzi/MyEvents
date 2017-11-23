package com.example.bianca.myevents;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bianc on 22/11/2017.
 */

public interface EventsService {

    String ENDPOINT = "https://www.eventbriteapi.com/v3/events/";


    @GET("search/")
    Call<EventResponse> listEvents(@Query("token")String token, @Query("q") String searchparam);


}
