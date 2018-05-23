package com.my.app.schoollifesystem.bean;

import java.io.Serializable;

/**
 * Created by yf on 18-5-23.
 */

public class Location implements Serializable {

    String id;
    String name;
    String country;
    String timezone;
    String timezone_offset;

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", timezone='" + timezone + '\'' +
                ", timezone_offset='" + timezone_offset + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(String timezone_offset) {
        this.timezone_offset = timezone_offset;
    }
}
