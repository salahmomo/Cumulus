package com.saos.salah.cumulus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Salah on 22/04/2018.
 */

public class Temp implements Parcelable{
    private float day;
    private float min;
    private float max;
    private float night;
    private float eve;
    private float morn;

    protected Temp(Parcel in) {
        day = in.readFloat();
        min = in.readFloat();
        max = in.readFloat();
        night = in.readFloat();
        eve = in.readFloat();
        morn = in.readFloat();
    }

    public static final Creator<Temp> CREATOR = new Creator<Temp>() {
        @Override
        public Temp createFromParcel(Parcel in) {
            return new Temp(in);
        }

        @Override
        public Temp[] newArray(int size) {
            return new Temp[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(day);
        dest.writeFloat(min);
        dest.writeFloat(max);
        dest.writeFloat(night);
        dest.writeFloat(eve);
        dest.writeFloat(morn);
    }

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getNight() {
        return night;
    }

    public void setNight(float night) {
        this.night = night;
    }

    public float getEve() {
        return eve;
    }

    public void setEve(float eve) {
        this.eve = eve;
    }

    public float getMorn() {
        return morn;
    }

    public void setMorn(float morn) {
        this.morn = morn;
    }
}
