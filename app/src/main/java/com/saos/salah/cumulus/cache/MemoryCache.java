package com.saos.salah.cumulus.cache;

/**
 * Created by Salah on 22/04/2018.
 */

import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.saos.salah.cumulus.model.DailyForecast;

/**
 * Manage all operation on memory cache
 */

public class MemoryCache{

    public static int CACHE_INITIALIZED = 10;
    public static int NOT_ENOUGHT_MEMORY = 11;

    private LruCache<String, DailyForecast> dailyForecastCache;
    private int cacheSize;


    private Context context;


    public MemoryCache(Context context){
        this.context = context;
        initializedWithDefaultCacheSize();
    }

    public MemoryCache(Context context, int size){
        this.context = context;
        initializedWithCacheSize(size);
    }


    private int initializedWithCacheSize(int cacheSize) {
        Log.i("CacheManager", "initializedWithCacheSize");
        return createLruCacheWithCacheSize(cacheSize);
    }

    public int initializedWithDefaultCacheSize() {
        Log.i("CacheManager", "initializedWithDefaultCacheSize");
        int tmpCacheSize = getDefaultCacheSize();

        return createLruCacheWithCacheSize(tmpCacheSize);
    }

    private int createLruCacheWithCacheSize(int cacheSize) {
        if (isEnoughtCacheSize(cacheSize)) {
            Log.i("CacheManager", "cacheInitialize with " + cacheSize);
            this.cacheSize = cacheSize;
            dailyForecastCache = new LruCache<>(cacheSize);
            return CACHE_INITIALIZED;
        }
        else {
            Log.i("CacheManager", "cache not Initialize with " + cacheSize);
            return NOT_ENOUGHT_MEMORY;
        }
    }

    public void put(String url, DailyForecast dailyForecast) {
        Log.i("CacheManager", "putDailyForecastOnCache");
        synchronized (this) {
            dailyForecastCache.put(url, dailyForecast);
        }
    }

    public boolean contain(String cityName) {
        synchronized (this) {
            return !(dailyForecastCache.get(cityName) == null);
        }
    }

    public DailyForecast get(String cityName){
        Log.i("CacheManager", "getDailyForecastFromCache");
        synchronized (this) {
            return dailyForecastCache.get(cityName);
        }
    }

    private int getDefaultCacheSize() {
        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int tmpCacheSize = 1024 * 1024 * memClass / 16;
        return tmpCacheSize;
    }

    private boolean isEnoughtCacheSize(int cacheSize) {
        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

        return !(cacheSize >= 1024 * 1024 * memClass);
    }
}
