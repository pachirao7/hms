package com.ce.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raop on 3/12/18.
 */
public class Corridor {
    private List<LightEquipment> lightEquipments = new ArrayList<>();
    private List<ACEquipment> acEquipments = new ArrayList<>();
    private boolean isMotionDetected;

    public List<LightEquipment> getLightEquipments() {
        return lightEquipments;
    }

    public void setLightEquipments(List<LightEquipment> lightEquipments) {
        this.lightEquipments = lightEquipments;
    }

    public List<ACEquipment> getAcEquipments() {
        return acEquipments;
    }

    public void setAcEquipments(List<ACEquipment> acEquipments) {
        this.acEquipments = acEquipments;
    }

    public boolean isMotionDetected() {
        return isMotionDetected;
    }

    public void setMotionDetected(boolean motionDetected) {
        isMotionDetected = motionDetected;
    }

    @Override
    public String toString() {
        return "Corridor{" +
                "lightEquipments=" + lightEquipments +
                ", acEquipments=" + acEquipments +
                ", isMotionDetected=" + isMotionDetected +
                '}';
    }
}
