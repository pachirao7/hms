package com.ce.unit

import com.ce.domain.Hotel
import com.ce.domain.HotelRequirements
import com.ce.domain.SensorData
import com.ce.processor.CorridorProcessor
import com.ce.processor.FloorProcessor
import com.ce.processor.HotelProcessor

import java.time.LocalDateTime

/**
 * Created by raop on 3/15/18.
 */
class HotelProcessorSpec extends UnitTestsBaseSpec {

    HotelProcessor hotelProcessor
    FloorProcessor floorProcessor
    CorridorProcessor corridorProcessor

    def setup(){
        corridorProcessor = new CorridorProcessor()
        floorProcessor = new FloorProcessor(
                corridorProcessor: corridorProcessor
        )
        hotelProcessor = new HotelProcessor(
                floorProcessor: floorProcessor
        )


    }

    def "should fail hotel creation if one of floor|corridor is 0"(){
        given : "Num of floors is 0"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 0)

        when:"Creating hotel"
        Hotel hotel =hotelProcessor.setUpHotel(hotelRequirements)

        then:
        hotel == null

    }

    def "should create hotel with 2 floors | 1 main | 2 sub corrdidors "(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().withHour(15))

        when:"Creating hotel"
        Hotel hotel =hotelProcessor.setUpHotel(hotelRequirements)

        then:
        hotel != null &&
                hotel.floors.size() == 2 &&
                hotel.floors.get(0).mainCorridors.size() == 1 &&
                hotel.floors.get(0).subCorridors.size() == 2

    }

    def "should create hotel with 2 floors | 1 main | 2 sub corrdidor and all ACs should be on"(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().withHour(15))

        when:"Creating hotel"
        Hotel hotel =hotelProcessor.setUpHotel(hotelRequirements)

        then:
        hotel != null &&
                hotel.floors.get(0).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).acEquipments.get(0).powerOn

    }

    def "should create hotel with 2 floors | 1 main | 2 sub corrdidor | ACs should be on | Main Corridor Lights should be off"(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().withHour(15))

        when:"Creating hotel"
        Hotel hotel =hotelProcessor.setUpHotel(hotelRequirements)

        then:
        hotel != null &&
                hotel.floors.get(0).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                ! hotel.floors.get(0).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(1).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(0).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(0).subCorridors.get(1).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(1).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(1).subCorridors.get(1).lightEquipments.get(0).powerOn

    }

    def "should create hotel with 2 floors | 1 main | 2 sub corrdidor | ACs should be on | Main corridor Lights should be on"(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().withHour(19))

        when:"Creating hotel"
        Hotel hotel =hotelProcessor.setUpHotel(hotelRequirements)

        then:
        hotel != null &&
                hotel.floors.get(0).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(0).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(0).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(0).subCorridors.get(1).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(1).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(1).subCorridors.get(1).lightEquipments.get(0).powerOn

    }

    def "should create hotel with 2 floors | 1 main | 2 sub corrdidor | ACs should be on | Main corridor Lights should be on next day 5AM"(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().plusDays(1).withHour(5))

        when:"Creating hotel"
        Hotel hotel =hotelProcessor.setUpHotel(hotelRequirements)

        then:
        hotel != null &&
                hotel.floors.get(0).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(0).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(0).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(0).subCorridors.get(1).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(1).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(1).subCorridors.get(1).lightEquipments.get(0).powerOn

    }

    def "should create hotel with 2 floors | 1 main | 2 sub corrdidor | ACs should be on | Main corridor Lights should be off next day 7AM"(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().plusDays(1).withHour(5))

        when:"Creating hotel"
        Hotel hotel =hotelProcessor.setUpHotel(hotelRequirements)

        then:
        hotel != null &&
                hotel.floors.get(0).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(0).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(0).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(0).subCorridors.get(1).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(1).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                ! hotel.floors.get(1).subCorridors.get(1).lightEquipments.get(0).powerOn

    }

    def "should handle motion detection on floor 1 sub corridor 2"(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2 | Motion Detected | Floor 1 | Sub corridor 2"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().withHour(19))
        SensorData sensorData = new SensorData(floor: 1,subCorridorId: 2)
        int floor = sensorData.floor-1
        int subCorridorId = sensorData.subCorridorId-1
        when:"Detecting movement during night slot"
        Hotel hotel =hotelProcessor.handleMotionDetection(hotelRequirements,sensorData)

        then:
        //Floor 1
        hotel.floors.get(floor).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(floor).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.getFloors().get(floor).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                hotel.getFloors().get(floor).subCorridors.get(subCorridorId).lightEquipments.get(0).powerOn &&

                //Floor 2

                hotel.floors.get(floor).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.floors.get(1).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.floors.get(1).subCorridors.get(1).lightEquipments.get(0).powerOn

    }

    def "should handle motion detection on floor 1 sub corridor 2 and reduce power consumption"(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2 | Motion Detected | Floor 1 | Sub corridor 2"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().withHour(19))
        SensorData sensorData = new SensorData(floor: 1,subCorridorId: 2)
        int floor = sensorData.floor-1
        int subCorridorId = sensorData.subCorridorId-1
        when:"Detecting movement during night slot"
        Hotel hotel =hotelProcessor.handleMotionDetection(hotelRequirements,sensorData)

        then:
        //Floor 1
        hotel.floors.get(floor).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(floor).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.getFloors().get(floor).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                hotel.getFloors().get(floor).subCorridors.get(subCorridorId).lightEquipments.get(0).powerOn &&
                !hotel.getFloors().get(floor).subCorridors.get(0).acEquipments.get(0).powerOn
                //Floor 2

                hotel.floors.get(1).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.floors.get(1).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.floors.get(1).subCorridors.get(1).lightEquipments.get(0).powerOn &&
                hotel.floors.get(1).subCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).subCorridors.get(1).acEquipments.get(0).powerOn

    }

    def "should handle motion detection on floor 1 sub corridor 1 and reduce power consumption"(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2 | Motion Detected | Floor 1 | Sub corridor 1"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().withHour(19))
        SensorData sensorData = new SensorData(floor: 1,subCorridorId: 1)
        int floor = sensorData.floor-1
        int subCorridorId = sensorData.subCorridorId-1
        when:"Detecting movement during night slot"
        Hotel hotel =hotelProcessor.handleMotionDetection(hotelRequirements,sensorData)

        then:
        //Floor 1
        hotel.floors.get(floor).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(floor).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                hotel.getFloors().get(floor).subCorridors.get(subCorridorId).lightEquipments.get(0).powerOn &&
                !hotel.getFloors().get(floor).subCorridors.get(1).lightEquipments.get(0).powerOn &&
                !hotel.getFloors().get(floor).subCorridors.get(0).acEquipments.get(0).powerOn
        //Floor 2

        hotel.floors.get(1).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.floors.get(1).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.floors.get(1).subCorridors.get(1).lightEquipments.get(0).powerOn &&
                hotel.floors.get(1).subCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(1).subCorridors.get(1).acEquipments.get(0).powerOn

    }

    def "should handle motion detection on floor 2 sub corridor 1 and reduce power consumption"(){
        given : "Num of floors is 2| Main Corridor 1 | Sub Corridor 2 | Motion Detected | Floor 2 | Sub corridor 1"
        HotelRequirements hotelRequirements = new HotelRequirements(floors: 2,numMainCorridors: 1,numSubCorridors: 2,currentTime: LocalDateTime.now().withHour(19))
        SensorData sensorData = new SensorData(floor: 2,subCorridorId: 1)
        int floor = sensorData.floor-1
        int subCorridorId = sensorData.subCorridorId-1
        when:"Detecting movement during night slot"
        Hotel hotel =hotelProcessor.handleMotionDetection(hotelRequirements,sensorData)

        then:
        //Floor 1
        hotel.floors.get(0).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(0).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.getFloors().get(0).subCorridors.get(0).lightEquipments.get(0).powerOn &&
                !hotel.getFloors().get(0).subCorridors.get(1).lightEquipments.get(0).powerOn &&
                hotel.getFloors().get(0).subCorridors.get(0).acEquipments.get(0).powerOn
        //Floor 2

        hotel.floors.get(1).mainCorridors.get(0).acEquipments.get(0).powerOn &&
                hotel.floors.get(floor).mainCorridors.get(0).lightEquipments.get(0).powerOn &&
                hotel.floors.get(floor).subCorridors.get(subCorridorId).lightEquipments.get(0).powerOn &&
                !hotel.floors.get(floor).subCorridors.get(1).lightEquipments.get(0).powerOn &&
                !hotel.floors.get(floor).subCorridors.get(subCorridorId).acEquipments.get(0).powerOn &&
                hotel.floors.get(floor).subCorridors.get(1).acEquipments.get(0).powerOn

    }

}
