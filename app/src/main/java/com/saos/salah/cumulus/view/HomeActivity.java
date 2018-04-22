package com.saos.salah.cumulus.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.saos.salah.cumulus.R;
import com.saos.salah.cumulus.view.cityforecasts.CityForecastsListFragment;
import com.saos.salah.cumulus.view.citys.CityListFragment;

import butterknife.BindView;

public class HomeActivity extends AppCompatActivity {

    private CityListFragment cityListFragment = null;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setActionBarTitle("Citys Forecast");

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            cityListFragment = (CityListFragment) fragmentManager.findFragmentByTag(CityListFragment.TAG);
            if (cityListFragment == null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                cityListFragment = new CityListFragment();
                fragmentTransaction.add(R.id.fragment, cityListFragment, CityListFragment.TAG);
                fragmentTransaction.commit();
            }
        }
    }

    public void gotToForecastListFragment(String cityName){
        FragmentManager fragmentManager = getSupportFragmentManager();

        CityForecastsListFragment cityForecastsListFragment = (CityForecastsListFragment) fragmentManager.findFragmentByTag(CityForecastsListFragment.TAG);
        if (cityForecastsListFragment == null) {
            setActionBarTitle(cityName);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            cityForecastsListFragment = CityForecastsListFragment.newInstance(cityName);
            fragmentTransaction.replace(R.id.fragment, cityForecastsListFragment, CityForecastsListFragment.TAG);
            fragmentTransaction.addToBackStack(CityForecastsListFragment.TAG);
            fragmentTransaction.commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            setActionBarTitle("Citys Forecast");
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
