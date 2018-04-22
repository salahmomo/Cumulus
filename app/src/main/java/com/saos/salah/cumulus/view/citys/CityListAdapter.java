package com.saos.salah.cumulus.view.citys;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saos.salah.cumulus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salah on 18/04/2018.
 */

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder>{

    public interface OnItemClickListener {
        public void onItemClick(String cityName);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView cityName;

        public ViewHolder(View v) {
            super(v);
            cityName = (TextView) v.findViewById(R.id.cityName);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (myOnItemClickListener != null)
                myOnItemClickListener.onItemClick(citysNames.get(getAdapterPosition()));
        }
    }

    private Context context;
    private List<String> citysNames = new ArrayList<>();
    OnItemClickListener myOnItemClickListener;

    public CityListAdapter(Context context, List<String> citysNames) {
        this.context = context;
        this.citysNames = citysNames;
    }

    @Override
    public CityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_citylist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String cityName = citysNames.get(position);

        holder.cityName.setText(cityName);
    }
    @Override
    public int getItemCount() {
        return citysNames.size();
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.myOnItemClickListener = onItemClickListener;
    }
}