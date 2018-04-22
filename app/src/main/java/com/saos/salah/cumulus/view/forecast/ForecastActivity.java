package com.saos.salah.cumulus.view.forecast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.saos.salah.cumulus.R;
import com.saos.salah.cumulus.model.Forecast;
import com.saos.salah.cumulus.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Salah on 22/04/2018.
 */

public class ForecastActivity extends AppCompatActivity {
    public static String IMG_URL = "http://openweathermap.org/img/w/";

    public static String CITY_ARG = "CITY_ARG";
    public static String FORECAST_ARG = "FORECAST_ARG";

    @BindView(R.id.day_textview)
    protected TextView dayTextView;

    @BindView(R.id.daytemps_textview)
    protected TextView tempTextView;

    @BindView(R.id.mintemps_textview)
    protected TextView minTextView;

    @BindView(R.id.maxtemps_textview)
    protected TextView maxTextView;

    @BindView(R.id.nighttemps_textview)
    protected TextView nightTextView;

    @BindView(R.id.evetemps_textview)
    protected TextView eveTextView;

    @BindView(R.id.morntemps_textview)
    protected TextView mornTextView;

    @BindView(R.id.pressure_textview)
    protected TextView pressureTextView;

    @BindView(R.id.humidity_textview)
    protected TextView humidityTextView;

    @BindView(R.id.weather_textview)
    protected TextView weatherTextView;

    @BindView(R.id.weather_imageview)
    protected ImageView weatherImageView;

    @BindView(R.id.speed_textview)
    protected TextView speedTextView;

    @BindView(R.id.deg_textview)
    protected TextView degTextView;

    @BindView(R.id.cloud_textview)
    protected TextView cloudTextView;


    private Forecast forecast;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);

        cityName =  getIntent().getStringExtra(CITY_ARG);
        if (cityName != null)
            getSupportActionBar().setTitle(cityName);
        forecast = (Forecast) getIntent().getExtras().get(FORECAST_ARG);

        if (forecast != null) {
            Date date = new Date(forecast.getDt() * 1000);
            dayTextView.setText(String.format("%tD", date));
            tempTextView.setText(String.format("%f°", forecast.getTemp().getDay()));
            minTextView.setText(String.format("%f°", forecast.getTemp().getMin()));
            maxTextView.setText(String.format("%f°", forecast.getTemp().getMax()));
            nightTextView.setText(String.format("%f°", forecast.getTemp().getNight()));
            eveTextView.setText(String.format("%f°", forecast.getTemp().getEve()));
            mornTextView.setText(String.format("%f°", forecast.getTemp().getMorn()));
            pressureTextView.setText(String.format("%f", forecast.getPressure()));
            humidityTextView.setText(String.format("%f", forecast.getHumidity()));

            Weather weather = forecast.getWeather().get(0);
            weatherTextView.setText(String.format("%s", weather.getDescription()));
            Picasso.get().load(String.format("%s%s.png", IMG_URL, weather.getIcon())).into(weatherImageView);

            speedTextView.setText(String.format("%f meter/sec", forecast.getSpeed()));
            degTextView.setText(String.format("%d°", forecast.getDeg()));
            cloudTextView.setText(String.format("%d", forecast.getClouds()));
        }
    }
}
