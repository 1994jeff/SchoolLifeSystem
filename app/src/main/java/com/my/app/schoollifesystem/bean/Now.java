package com.my.app.schoollifesystem.bean;

import java.io.Serializable;

/**
 * Created by yf on 18-5-23.
 */

public class Now implements Serializable{

    String text;
    String code;
    String temperature;
    String feels_like;
    String pressure;
    String humidity;
    String visibility;
    String wind_direction;
    String wind_direction_degree;
    String wind_speed;
    String wind_scale;
    String clouds;
    String dew_point;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(String feels_like) {
        this.feels_like = feels_like;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_direction_degree() {
        return wind_direction_degree;
    }

    public void setWind_direction_degree(String wind_direction_degree) {
        this.wind_direction_degree = wind_direction_degree;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWind_scale() {
        return wind_scale;
    }

    public void setWind_scale(String wind_scale) {
        this.wind_scale = wind_scale;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getDew_point() {
        return dew_point;
    }

    public void setDew_point(String dew_point) {
        this.dew_point = dew_point;
    }

    @Override
    public String toString() {
        return "Now{" +
                "text='" + text + '\'' +
                ", code='" + code + '\'' +
                ", temperature='" + temperature + '\'' +
                ", feels_like='" + feels_like + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", visibility='" + visibility + '\'' +
                ", wind_direction='" + wind_direction + '\'' +
                ", wind_direction_degree='" + wind_direction_degree + '\'' +
                ", wind_speed='" + wind_speed + '\'' +
                ", wind_scale='" + wind_scale + '\'' +
                ", clouds='" + clouds + '\'' +
                ", dew_point='" + dew_point + '\'' +
                '}';
    }
}
