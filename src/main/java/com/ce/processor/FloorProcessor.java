package com.ce.processor;

import com.ce.domain.Floor;
import com.ce.domain.Hotel;
import com.ce.domain.HotelRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raop on 3/13/18.
 */
@Component
public class FloorProcessor {

    @Autowired
    CorridorProcessor corridorProcessor;

    public void setUpFloor(HotelRequirements hotelRequirements, Hotel hotel){
        createFloors(hotelRequirements.getFloors(),hotel,hotelRequirements);
    }

    private void createFloors(int numFloors, Hotel hotel,HotelRequirements hotelRequirements) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < numFloors; i++) {
            Floor floor = new Floor();
            corridorProcessor.setUpCorridors(hotelRequirements,floor);
            floors.add(floor);
        }
        hotel.setFloors(floors);
    }

}
