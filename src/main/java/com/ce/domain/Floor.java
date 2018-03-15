package com.ce.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raop on 3/12/18.
 */
public class Floor {
    private List<MainCorridor> mainCorridors = new ArrayList<>();
    private List<SubCorridor> subCorridors = new ArrayList<>();

    public List<MainCorridor> getMainCorridors() {
        return mainCorridors;
    }

    public void setMainCorridors(List<MainCorridor> mainCorridors) {
        this.mainCorridors = mainCorridors;
    }

    public List<SubCorridor> getSubCorridors() {
        return subCorridors;
    }

    public void setSubCorridors(List<SubCorridor> subCorridors) {
        this.subCorridors = subCorridors;
    }
}
