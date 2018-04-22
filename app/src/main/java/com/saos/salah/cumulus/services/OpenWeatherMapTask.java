package com.saos.salah.cumulus.services;

import android.os.AsyncTask;

import com.saos.salah.cumulus.listener.OpenWeatherMapListener;
import com.saos.salah.cumulus.model.DailyForecast;

/**
 * Created by Salah on 22/04/2018.
 */

public class OpenWeatherMapTask extends AsyncTask<String, Void, DailyForecast> {

    protected OpenWeatherMapListener openWeatherMapListener;

    public OpenWeatherMapTask(OpenWeatherMapListener openWeatherMapListener) {
        this.openWeatherMapListener = openWeatherMapListener;
    }

    /**
     * Get a bitmap image from cache or network
     *      get image from MemoryCache, if yes return it otherwise
     *      get from DiskCache, if yes save in MemoryCache and return it otherwise
     *      get from network, save in MemoryCache and DiskCache and return it
     * @param urls
     * @return
     */
    @Override
    protected DailyForecast doInBackground(String... urls) {

        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
