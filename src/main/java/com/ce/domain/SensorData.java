package com.ce.domain;

/**
 * Created by raop on 3/15/18.
 */
public class SensorData {
    private int floor;
    private int subCorridorId;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getSubCorridorId() {
        return subCorridorId;
    }

    public void setSubCorridorId(int subCorridorId) {
        this.subCorridorId = subCorridorId;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "floor=" + floor +
                ", subCorridorId=" + subCorridorId +
                '}';
    }
}
