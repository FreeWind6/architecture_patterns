package ru.geekbrains.entities;

import java.util.Objects;

/**
 * Value object
 */
public class Coordinate {
    private String latitude;
    private String longitude;

    public Coordinate(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Coordinate setLatitude(String latitude) {
        Coordinate newCoordinate = new Coordinate(latitude, longitude);
        newCoordinate.latitude = latitude;
        return newCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return latitude.equals(that.latitude) &&
                longitude.equals(that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
