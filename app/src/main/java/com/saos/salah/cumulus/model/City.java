package com.saos.salah.cumulus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Salah on 18/04/2018.
 */

public class City implements Parcelable{
    private int id;
    private String name;
    private CityCoord coord;
    private String country;

    protected City(Parcel in) {
        id = in.readInt();
        name = in.readString();
        coord = in.readParcelable(CityCoord.class.getClassLoader());
        country = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeParcelable(this.coord, flags);
        dest.writeString(country);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityCoord getCoord() {
        return coord;
    }

    public void setCoord(CityCoord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
