package com.ce.processor;

import com.ce.domain.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * Created by raop on 3/13/18.
 */
@Component
public class CorridorProcessor {

    public Predicate<LocalDateTime> isNightSlot = (currentTime) ->
            (
                    currentTime.compareTo(LocalDateTime.now().withHour(18)) > 0 &&
                            currentTime.compareTo(LocalDateTime.now().plusDays(1).withHour(6)) < 0
            );


    public void setUpCorridors(HotelRequirements hotelRequirements,Floor floor){
        createMainCorridors(hotelRequirements, floor);
        createSubCorridors(hotelRequirements.getNumSubCorridors(), floor);
    }

    private void createMainCorridors(HotelRequirements hotelRequirements, Floor floor) {
        List<MainCorridor> corridors = new ArrayList<>();
        for (int i = 0; i < hotelRequirements.getNumMainCorridors(); i++) {
            MainCorridor mainCorridor = new MainCorridor();
            setLightEquipments(hotelRequirements, mainCorridor);
            mainCorridor.setAcEquipments(getAcEquipments());

            corridors.add(mainCorridor);
        }
        floor.setMainCorridors(corridors);
    }

    private void setLightEquipments(HotelRequirements hotelRequirements, MainCorridor mainCorridor) {
        List<LightEquipment> lightEquipments = getLightEquipments();
        if(isNightSlot.test(hotelRequirements.getCurrentTime())){
            lightEquipments.get(0).setPowerOn(true);
        }
        mainCorridor.setLightEquipments(lightEquipments);
    }

    private void createSubCorridors(int numCorridors, Floor floor) {
        List<SubCorridor> corridors = new ArrayList<>();
        for (int i = 0; i < numCorridors; i++) {
            SubCorridor subCorridor = new SubCorridor();
            subCorridor.setLightEquipments(getLightEquipments());
            subCorridor.setAcEquipments(getAcEquipments());
            corridors.add(subCorridor);
        }
        floor.setSubCorridors(corridors);
    }

    private List<LightEquipment> getLightEquipments() {
        LightEquipment lightEquipment = new LightEquipment();
        lightEquipment.setPowerOn(false);
        List<LightEquipment> lightEquipments = new ArrayList<>();
        lightEquipments.add(lightEquipment);
        return lightEquipments;
    }

    private List<ACEquipment> getAcEquipments() {
        ACEquipment acEquipment = new ACEquipment();
        acEquipment.setPowerOn(true);
        List<ACEquipment> acEquipments = new ArrayList<>();
        acEquipments.add(acEquipment);
        return acEquipments;
    }
}
