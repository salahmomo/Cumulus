package com.saos.salah.cumulus.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton instance To call OpenWeatherMap API
 */

public class OpenWeatherMapSingleton {
    private static OpenWeatherMapSingleton instance = null;

    public OpenWeatherMapServices openWeatherMapServices;

    public static OpenWeatherMapSingleton getInstance(){
        if (instance == null) {
            instance = new OpenWeatherMapSingleton();
        }

        return instance;
    }

    private OpenWeatherMapSingleton(){
        openWeatherMapServices = new Retrofit.Builder().baseUrl(OpenWeatherMapServices.ENDPOINT)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(OpenWeatherMapServices.class);
    }

}
