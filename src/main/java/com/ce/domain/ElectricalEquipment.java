package com.ce.domain;


import java.time.LocalDateTime;

/**
 * Created by raop on 3/12/18.
 */
public class ElectricalEquipment {
    private boolean powerOn;


    public boolean isPowerOn() {
        return powerOn;
    }

    public void setPowerOn(boolean powerOn) {
        this.powerOn = powerOn;
    }

    @Override
    public String toString() {
        return "ElectricalEquipment{" +
                "powerOn=" + powerOn +
                '}';
    }
}
