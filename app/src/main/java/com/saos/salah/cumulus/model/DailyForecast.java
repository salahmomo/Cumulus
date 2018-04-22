package com.saos.salah.cumulus.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salah on 22/04/2018.
 */

public class DailyForecast implements Parcelable{
    private City city;
    private String cod;
    private float message;
    private int cnt;
    private List<Forecast> list;

    protected DailyForecast(Parcel in) {
        city = in.readParcelable(City.class.getClassLoader());
        cod = in.readString();
        message = in.readFloat();
        cnt = in.readInt();
        list = new ArrayList<Forecast>();
        in.readTypedList(list, Forecast.CREATOR);
    }

    public static final Creator<DailyForecast> CREATOR = new Creator<DailyForecast>() {
        @Override
        public DailyForecast createFromParcel(Parcel in) {
            return new DailyForecast(in);
        }

        @Override
        public DailyForecast[] newArray(int size) {
            return new DailyForecast[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.city, flags);
        dest.writeString(cod);
        dest.writeFloat(message);
        dest.writeInt(cnt);
        dest.writeTypedList(list);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Forecast> getList() {
        return list;
    }

    public void setList(List<Forecast> list) {
        this.list = list;
    }
}
