package com.saos.salah.cumulus.view.cityforecasts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.saos.salah.cumulus.R;
import com.saos.salah.cumulus.model.Forecast;
import com.saos.salah.cumulus.model.Weather;
import com.saos.salah.cumulus.view.citys.CityListAdapter;
import com.saos.salah.cumulus.view.forecast.ForecastActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salah on 22/04/2018.
 */

public class CityForecastListAdapter extends RecyclerView.Adapter<CityForecastListAdapter.ViewHolder>{
    public interface OnItemClickListener {
        public void onItemClick(Forecast forecast);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView day;
        public TextView temp;
        public ImageView weather;

        public ViewHolder(View v) {
            super(v);
            day = (TextView) v.findViewById(R.id.day_textview);
            temp = (TextView) v.findViewById(R.id.temp_textview);
            weather = (ImageView) v.findViewById(R.id.weather_imageview);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (myOnItemClickListener != null)
                myOnItemClickListener.onItemClick(forecastsList.get(getAdapterPosition()));
        }
    }

    private Context context;
    private List<Forecast> forecastsList = new ArrayList<>();
    CityForecastListAdapter.OnItemClickListener myOnItemClickListener;

    public CityForecastListAdapter(Context context) {
        this.context = context;
        this.forecastsList = new ArrayList<>();
    }

    @Override
    public CityForecastListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forecastlist, parent, false);
        CityForecastListAdapter.ViewHolder vh = new CityForecastListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CityForecastListAdapter.ViewHolder holder, int position) {
        Forecast forecast = forecastsList.get(position);

        holder.day.setText(String.format("%tD", forecast.getDt()));
        holder.temp.setText(Float.toString(forecast.getTemp().getDay()) + "°");
        Weather weather = forecast.getWeather().get(0);
        Picasso.get().load(String.format("%s%s.png", ForecastActivity.IMG_URL, weather.getIcon())).into(holder.weather);
    }
    @Override
    public int getItemCount() {
        return forecastsList.size();
    }

    public void setOnItemClickListener(final CityForecastListAdapter.OnItemClickListener onItemClickListener){
        this.myOnItemClickListener = onItemClickListener;
    }

    public void updateForecastList(List<Forecast> forecastsList) {
        this.forecastsList = forecastsList;
        notifyDataSetChanged();
    }
}
