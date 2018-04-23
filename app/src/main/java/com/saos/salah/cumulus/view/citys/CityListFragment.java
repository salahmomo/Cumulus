package com.saos.salah.cumulus.view.citys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saos.salah.cumulus.R;
import com.saos.salah.cumulus.view.HomeActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Salah on 18/04/2018.
 */

public class CityListFragment extends Fragment {

    /**
     *     List of city we need to see his for cast for 14 next day
     *     pattern of cityName is : "[CityName], [CountryCode]"
     */
    private List<String> citysNames =  Arrays.asList("Paris, FR", "Istanbul, TR", "Tokyo, JP");

    @BindView(R.id.city_list)
    protected RecyclerView mRecyclerView;

    private CityListAdapter cityListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static String TAG = "CityListFragment";

    public CityListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_citylist, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //get All Movies fromjson

        cityListAdapter = new CityListAdapter(getContext(), citysNames);
        cityListAdapter.setOnItemClickListener(new CityListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String cityName) {

                //go to movie element
                HomeActivity mainActivity = (HomeActivity) getActivity();
                mainActivity.gotToForecastListFragment(cityName);
            }
        });

        mRecyclerView.setAdapter(cityListAdapter);

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
