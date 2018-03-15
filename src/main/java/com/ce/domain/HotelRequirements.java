package com.ce.domain;

import java.time.LocalDateTime;

/**
 * Created by raop on 3/15/18.
 */
public class HotelRequirements {
    private int floors;
    private int numSubCorridors;
    private int numMainCorridors;
    private LocalDateTime currentTime;

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getNumSubCorridors() {
        return numSubCorridors;
    }

    public void setNumSubCorridors(int numSubCorridors) {
        this.numSubCorridors = numSubCorridors;
    }

    public int getNumMainCorridors() {
        return numMainCorridors;
    }

    public void setNumMainCorridors(int numMainCorridors) {
        this.numMainCorridors = numMainCorridors;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "HotelRequirements{" +
                "floors=" + floors +
                ", numSubCorridors=" + numSubCorridors +
                ", numMainCorridors=" + numMainCorridors +
                ", currentTime=" + currentTime +
                '}';
    }
}
