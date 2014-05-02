package com.jprogrammers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */

@Entity
public class CarType {

    private long id;
    private long licenceId;
    private String usecaseType;
    private String color;
    private int defCount;
    private int cylinderCount;
    private String VINId;
    private String system;
    private String model;
    private int capacity;
    private String fuelType;
    private int wheelsCount;
    private String country;
    private String cylinderSize;
    private String tip;


    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "usecaseType", nullable = true, insertable = true, updatable = true, length = 500)
    public String getUsecaseType() {
        return usecaseType;
    }

    public void setUsecaseType(String usecaseType) {
        this.usecaseType = usecaseType;
    }

    @Column(name = "color", nullable = true, insertable = true, updatable = true, length = 100)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "defCount", nullable = true, insertable = true, updatable = true, length = 2)
    public int getDefCount() {
        return defCount;
    }

    public void setDefCount(int defCount) {
        this.defCount = defCount;
    }
    @Column(name = "cylinderCount", nullable = true, insertable = true, updatable = true, length = 2)
    public int getCylinderCount() {
        return cylinderCount;
    }

    public void setCylinderCount(int cylinderCount) {
        this.cylinderCount = cylinderCount;
    }

    @Column(name = "VINId", nullable = true, insertable = true, updatable = true, length = 200)
    public String getVINId() {
        return VINId;
    }

    public void setVINId(String VINId) {
        this.VINId = VINId;
    }

    @Column(name = "system", nullable = true, insertable = true, updatable = true, length = 500)
    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Column(name = "model", nullable = true, insertable = true, updatable = true, length = 500)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "capacity", nullable = true, insertable = true, updatable = true, length = 2)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Column(name = "fuelType", nullable = true, insertable = true, updatable = true, length = 200)
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Column(name = "wheelsCount", nullable = true, insertable = true, updatable = true, length = 2)
    public int getWheelsCount() {
        return wheelsCount;
    }

    public void setWheelsCount(int wheelsCount) {
        this.wheelsCount = wheelsCount;
    }
    @Column(name = "country", nullable = true, insertable = true, updatable = true, length = 400)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "cylinderSize", nullable = true, insertable = true, updatable = true, length = 200)
    public String getCylinderSize() {
        return cylinderSize;
    }

    public void setCylinderSize(String cylinderSize) {
        this.cylinderSize = cylinderSize;
    }

    @Column(name = "tip", nullable = true, insertable = true, updatable = true, length = 200)
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    @Column(name = "licenceId", nullable = true, insertable = true, updatable = true, length = 20)
    public long getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(long licenceId) {
        this.licenceId = licenceId;
    }
}
