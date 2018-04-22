package com.saos.salah.cumulus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Salah on 22/04/2018.
 */

public class CityCoord implements Parcelable {
    private float lon;
    private float lat;

    protected CityCoord(Parcel in) {
        lon = in.readFloat();
        lat = in.readFloat();
    }

    public static final Creator<CityCoord> CREATOR = new Creator<CityCoord>() {
        @Override
        public CityCoord createFromParcel(Parcel in) {
            return new CityCoord(in);
        }

        @Override
        public CityCoord[] newArray(int size) {
            return new CityCoord[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(lon);
        dest.writeFloat(lat);
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
