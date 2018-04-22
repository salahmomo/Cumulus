package com.saos.salah.cumulus.cache;

import android.content.Context;

import com.saos.salah.cumulus.model.DailyForecast;

/**
 * Created by Salah on 22/04/2018.
 */

public class CacheManager {
    private MemoryCache memoryCache;

    public static String URL_KEY_INTENT = "URL_KEY";
    public static String BITMAP_KEY_INTENT = "BITMAP_KEY";

    private boolean isDiskCacheEnable = true;
    private Context context;

    private static CacheManager instance = null;

    /**
     * Initialize memoryCache and diskCache with default sizeCache
     * @param context
     */
     private CacheManager(Context context) {
        this.context = context;

        this.memoryCache = new MemoryCache(context);
    }


    /**
     * Initialize memoryCache with memorySize cache size
     * and dishCache with diskSize cache size
     * @param context
     * @param memorySize
     */
    private CacheManager(Context context, int memorySize) {
        this.context = context;

        this.memoryCache = new MemoryCache(context, memorySize);
    }

    public static CacheManager getInstance(Context context){
        if (instance == null){
            instance = new CacheManager(context);
        }

        return instance;
    }

    /**
     * get dailyForecast link with cityName in MemoryCache
     * @param cityName
     * @return
     */
    public DailyForecast getFromMemoryCache(String cityName) {
        return this.memoryCache.get(cityName);
    }


    /**
     * put in MemoryCache DailyForecast link with cityName
     * @param cityName
     * @param dailyForecast
     */
    public void putToMemoryCache(String cityName, DailyForecast dailyForecast) {
        this.memoryCache.put(cityName, dailyForecast);
    }

}
