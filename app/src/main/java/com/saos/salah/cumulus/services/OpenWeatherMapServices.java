package com.saos.salah.cumulus.services;


import com.saos.salah.cumulus.model.City;
import com.saos.salah.cumulus.model.DailyForecast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Salah on 18/04/2018.
 */

/**
 * Service for OpenWeatherMap API
 */
public interface OpenWeatherMapServices {
    public static final String ENDPOINT = "http://api.openweathermap.org/data/2.5/forecast/";

    //units=metric to get temp in Celsius

    @GET("daily")
    Call<DailyForecast> searchDailyForcast(@Query("q") String queryCitynameCountrycode, @Query("cnt") String nbOfDay, @Query("units") String unitsFormat, @Query("appid") String appID);
}
