package com.emidev.firstclient;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Place {

    private String name;
    private String country;
    private double lat;
    private double lng;
    private LocalDateTime sunset;
    private LocalDateTime sunrise;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Place(String name, String country, double lat, double lng, String sunset, String sunrise){
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.country = country;
        sunset.replace("T", " ");
        this.sunset = LocalDateTime.parse(sunset, formatter);
        sunrise.replace("T", " ");
        this.sunrise = LocalDateTime.parse(sunrise, formatter);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getSunset() {
        return this.sunset.format(formatter);
    }

    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getSunrise() {
        return this.sunrise.format(formatter);
    }

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ";\r\n  country='" + getCountry() + "'" +
                ";\r\n  lat='" + getLat() + "'" +
                ";\r\n  lng='" + getLng() + "'" +
                ";\r\n  sunset='" + getSunset() + "'" +
                ";\r\n  sunrise='" + getSunrise() + "'" +
                "}";
    }

}

