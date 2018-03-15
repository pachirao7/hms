package com.ce.domain;

import java.util.List;

/**
 * Created by raop on 3/12/18.
 */
public class Hotel {
    private String name;
    private String address;
    private List<Floor> floors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", floors=" + floors +
                '}';
    }
}

