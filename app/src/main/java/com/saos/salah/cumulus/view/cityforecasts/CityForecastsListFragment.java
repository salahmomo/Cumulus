package com.saos.salah.cumulus.view.cityforecasts;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saos.salah.cumulus.R;
import com.saos.salah.cumulus.cache.CacheManager;
import com.saos.salah.cumulus.model.DailyForecast;
import com.saos.salah.cumulus.model.Forecast;
import com.saos.salah.cumulus.services.OpenWeatherMapSingleton;
import com.saos.salah.cumulus.view.forecast.ForecastActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Salah on 18/04/2018.
 */

public class CityForecastsListFragment extends Fragment {

    private static String CITY_NAME_CODE_ARG = "CITY_NAME_CODE_ARG";

    private String cityNameCode;

    private DailyForecast dailyForecast;

    @BindView(R.id.forecast_list)
    protected RecyclerView mRecyclerView;

    private CityForecastListAdapter cityForecastListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static String TAG = "CityForecastsListFragment";

    public CityForecastsListFragment() {
    }


    public static CityForecastsListFragment newInstance(String cityNameCode) {
        CityForecastsListFragment fragment = new CityForecastsListFragment();
        Bundle args = new Bundle();
        args.putString(CITY_NAME_CODE_ARG, cityNameCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cityforecastlist, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            cityNameCode = getArguments().getString(CITY_NAME_CODE_ARG);
        }

        // initialize recycler view and adapter
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        cityForecastListAdapter = new CityForecastListAdapter(getContext());
        cityForecastListAdapter.setOnItemClickListener(new CityForecastListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Forecast forecast) {
                Intent intent = new Intent(getContext(), ForecastActivity.class);
                intent.putExtra(ForecastActivity.CITY_ARG, cityNameCode);
                intent.putExtra(ForecastActivity.FORECAST_ARG, forecast);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(cityForecastListAdapter);

        //check if we already have the city dailyForecast in appCache
        DailyForecast dailyForecastFromMemory = CacheManager.getInstance(getContext()).getFromMemoryCache(this.cityNameCode);

        if (dailyForecast != null)
            this.dailyForecast = dailyForecastFromMemory;
        else {
            OpenWeatherMapSingleton.getInstance()
                    .openWeatherMapServices.searchDailyForcast(cityNameCode, "14", getString(R.string.celcius_unitsformat), getString(R.string.app_id))
                    .enqueue(new Callback<DailyForecast>() {

                        @Override
                        public void onResponse(Call<DailyForecast> call, Response<DailyForecast> response) {
                            CityForecastsListFragment.this.dailyForecast = response.body();
                            if (CityForecastsListFragment.this.dailyForecast != null && CityForecastsListFragment.this.dailyForecast.getList() != null) {

                                //Check if we have internet access
                                ConnectivityManager connMgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                if (networkInfo != null && networkInfo.isConnected()) {
                                    CacheManager.getInstance(getContext()).putToMemoryCache(CityForecastsListFragment.this.cityNameCode, CityForecastsListFragment.this.dailyForecast);
                                    CityForecastsListFragment.this.cityForecastListAdapter.updateForecastList(CityForecastsListFragment.this.dailyForecast.getList());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DailyForecast> call, Throwable t) {

                        }

                    });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }
}
