package com.ce.processor;

import com.ce.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Created by raop on 3/13/18.
 */
@Component
public class HotelProcessor {

    @Autowired
    FloorProcessor floorProcessor;

    public Predicate<LocalDateTime> isNightSlot = (currentTime) ->
            (
                    currentTime.compareTo(LocalDateTime.now().withHour(18)) > 0 &&
                            currentTime.compareTo(LocalDateTime.now().plusDays(1).withHour(6)) < 0
            );


    public Hotel setUpHotel(HotelRequirements hotelRequirements){
        Hotel hotel = null;
        if(hotelRequirements.getFloors() == 0 || hotelRequirements.getNumMainCorridors() == 0 || hotelRequirements.getNumSubCorridors() == 0){
            System.out.println("Error : Hotel should have atleast 1 floor | 1 Corridor | 1 Sub Corridor");
        }else{
            hotel = new Hotel();
            floorProcessor.setUpFloor(hotelRequirements,hotel);
        }
        return hotel;
    }

    public Hotel handleMotionDetection(HotelRequirements hotelRequirements,SensorData sensorData){
        Hotel hotel = new Hotel();
        floorProcessor.setUpFloor(hotelRequirements,hotel);
        if(sensorData.getFloor() > hotel.getFloors().size()){
            System.out.println("Error : Entered floor number does not exist");
        }else{
            hotel.getFloors().get(sensorData.getFloor()-1).getSubCorridors().get(sensorData.getSubCorridorId()-1).setMotionDetected(true);
            if(isNightSlot.test(hotelRequirements.getCurrentTime())){
                hotel.getFloors().get(sensorData.getFloor()-1).getSubCorridors().
                        get(sensorData.getSubCorridorId()-1).getLightEquipments().get(0).setPowerOn(true);
                managePowerUsage(hotel.getFloors().get(sensorData.getFloor()-1));
            }
        }

        return hotel;
    }

    private void managePowerUsage(Floor floor) {
        int maxAllocatedPower = floor.getMainCorridors().size() * 15 + floor.getSubCorridors().size() * 10;
        int currentConsumption = getCurrentConsumption(floor);
        System.out.println("Consumption Before Managing : "+currentConsumption + " Max Allocated Power : "+maxAllocatedPower);
        for (SubCorridor subCorridor:floor.getSubCorridors()) {
            while(currentConsumption > maxAllocatedPower){
                subCorridor.getAcEquipments().get(0).setPowerOn(false);
                currentConsumption = getCurrentConsumption(floor);
            }
        }
        System.out.println("Floor Consumption After Managing : "+currentConsumption + " Max Allocated Power : "+maxAllocatedPower);
    }

    private int getCurrentConsumption(Floor floor) {
        int powerConsumed[] = {0,0};
        floor.getSubCorridors().forEach(subCorridor -> {
            powerConsumed[0] = powerConsumed[0] +calculateCorridorPowerUsage(subCorridor);
        });
        floor.getMainCorridors().forEach(mainCorridor -> {
            powerConsumed[1] = powerConsumed[1] + calculateCorridorPowerUsage(mainCorridor);
        });
        return powerConsumed[0] + powerConsumed[1];
    }

    private int calculateCorridorPowerUsage( Corridor corridor) {
        int powerConsumed[] = {0,0};
        corridor.getLightEquipments().forEach(lightEquipment -> {
            if(lightEquipment.isPowerOn())
                powerConsumed[0]=powerConsumed[0]+ LightEquipment.consumptionUnits;
        });
        corridor.getAcEquipments().forEach(acEquipment -> {
            if(acEquipment.isPowerOn())
                powerConsumed[1]=powerConsumed[1]+ ACEquipment.consumptionUnits;
        });
        return powerConsumed[0] + powerConsumed[1];
    }

}
