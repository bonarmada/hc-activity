package com.bonarmada.hc_activity.data.api;

import com.bonarmada.hc_activity.Constants;
import com.bonarmada.hc_activity.data.vo.WeatherData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by bonbonme on 1/30/2018.
 */

public interface WeatherDataRemote {
    @GET("group")
    Single<Response<ApiResponse>> getGroupWeather(
            @Query("id") String groupIds);

    @GET("weather")
    Single<Response<WeatherData>> getWeatherById(@Query("id") int id);

    @GET("weather")
    Single<Response<WeatherData>> getWeatherByQuery(@Query("q") String query);
}
