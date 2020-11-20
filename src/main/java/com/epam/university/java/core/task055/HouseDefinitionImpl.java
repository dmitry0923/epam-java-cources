package com.epam.university.java.core.task055;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passports_houses")
@XmlAccessorType(XmlAccessType.FIELD)
public class HouseDefinitionImpl implements HouseDefinition {
    @XmlElement(name = "address")
    private String address;
    @XmlElement(name = "data_buildingdate")
    private String year;
    @XmlElement(name = "data_buildingarea")
    private String area;
    @XmlElement(name = "addr_district")
    private String district;
    @XmlElement(name = "comm_num")
    private String communalFlats;

    public String getCommunalFlats() {
        return communalFlats;
    }

    public void setCommunalFlats(String communalFlats) {
        this.communalFlats = communalFlats;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public int getYear() {
        if (year.matches("\\d+")) {
            return Integer.parseInt(year);
        } else {
            return 0;
        }
    }

    @Override
    public double getArea() {
        if (area.length() > 0) {
            return Double.parseDouble(area);
        }
        return 0.0;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setYear(int year) {
        this.year = String.valueOf(year);
    }

    @Override
    public void setArea(double area) {
        this.area = String.valueOf(area);
    }
}
