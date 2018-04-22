package com.saos.salah.cumulus.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salah on 22/04/2018.
 */

public class Forecast implements Parcelable{
    //Date in Log Format
    private long dt;

    private Temp temp;
    private float pressure;
    private float humidity;
    private List<Weather> weather;
    private float speed;
    private int deg;
    private int clouds;
    private float rain;

    protected Forecast(Parcel in) {
        dt = in.readLong();
        temp = in.readParcelable(Temp.class.getClassLoader());
        pressure = in.readFloat();
        humidity = in.readFloat();
        weather = new ArrayList<Weather>();
        in.readTypedList(weather, Weather.CREATOR);
        speed = in.readFloat();
        deg = in.readInt();
        clouds = in.readInt();
        rain = in.readFloat();
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dt);
        dest.writeParcelable(this.temp, flags);
        dest.writeFloat(pressure);
        dest.writeFloat(humidity);
        dest.writeTypedList(weather);
        dest.writeFloat(speed);
        dest.writeInt(deg);
        dest.writeInt(clouds);
        dest.writeFloat(rain);
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }
}
