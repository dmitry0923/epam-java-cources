package com.epam.university.java.core.task055;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dataset")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessingContextImpl implements ProcessingContext {
    @XmlElement(name = "passports_houses", type = HouseDefinitionImpl.class)
    private final Collection<HouseDefinition> houseDefinitions = new ArrayList<>();

    @Override
    public Collection<HouseDefinition> oldestHouses() {
        Collection<HouseDefinition> oldestHouses = new ArrayList<>();
        int minBuildingDate = Integer.MAX_VALUE;
        for (HouseDefinition house : houseDefinitions
        ) {
            if (house.getYear() > 0 && house.getYear() < minBuildingDate) {
                minBuildingDate = house.getYear();
            }
        }
        for (HouseDefinition house : houseDefinitions
        ) {
            if (house.getYear() == minBuildingDate) {
                oldestHouses.add(house);
            }
        }
        return oldestHouses;
    }

    @Override
    public int averageAge(String district) {
        Collection<Integer> ages = new ArrayList<>();
        for (HouseDefinition house : houseDefinitions
        ) {
            HouseDefinitionImpl res = (HouseDefinitionImpl) house;
            if (res.getDistrict().equals(district) && res.getYear() > 0) {
                ages.add(LocalDate.now().getYear() - res.getYear());

            }
        }

        if (ages.isEmpty()) {
            for (HouseDefinition house : houseDefinitions
            ) {
                HouseDefinitionImpl res = (HouseDefinitionImpl) house;
                if (res.getYear() > 0) {
                    ages.add(LocalDate.now().getYear() - res.getYear());
                }
            }
        }

        int agesSum = 0;
        for (Integer integer : ages
        ) {
            agesSum += integer;
        }

        return agesSum / ages.size();
    }

    @Override
    public HouseDefinition biggestTotalFloorSpace() {
        double maxArea = 0;
        for (HouseDefinition house : houseDefinitions
        ) {
            if (maxArea < house.getArea()) {
                maxArea = house.getArea();
            }
        }
        double condition = maxArea;
        return houseDefinitions.stream()
                .filter(x -> x.getArea() == condition)
                .findAny()
                .orElse(null);
    }

    @Override
    public HouseDefinition smallestTotalFloorSpace() {
        double minArea = Double.MAX_VALUE;
        for (HouseDefinition house : houseDefinitions
        ) {
            if (minArea > house.getArea() && house.getArea() > 0.0) {
                minArea = house.getArea();
            }
        }
        double condition = minArea;
        return houseDefinitions.stream()
                .filter(x -> x.getArea() == condition)
                .findAny()
                .orElseThrow();
    }

    @Override
    public int totalCountHouses() {
        return houseDefinitions.size();
    }

    @Override
    public int totalCountHousesWithCommunalFlats() {
        return (int) houseDefinitions.stream()
                .map(x -> (HouseDefinitionImpl) x)
                .filter(x -> x.getCommunalFlats().length() > 0)
                .count();
    }
}
