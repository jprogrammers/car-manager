package com.jprogrammers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */

@Entity
public class CarType implements Serializable {

    private long id;
    private String country;
    private String factory;
    private String usecaseType;
    private String usecaseType2;
    private String system;
    private String tip;
    private int defCount;
    private int wheelsCount;
    private String fuelType;
    private int cylinderCount;
    private int capacity;
    private int cylinderSize;

    public CarType(){

    }

    public CarType(long id, String country, String factory, String usecaseType, String usecaseType2, String system,
                   String tip, int defCount, int wheelsCount, String fuelType, int cylinderCount, int capacity,
                   int cylinderSize){
        setId(id);
        setCountry(country);
        setFactory(factory);
        setUsecaseType(usecaseType);
        setUsecaseType2(usecaseType2);
        setSystem(system);
        setTip(tip);
        setDefCount(defCount);
        setWheelsCount(wheelsCount);
        setFuelType(fuelType);
        setCylinderCount(cylinderCount);
        setCapacity(capacity);
        setCylinderSize(cylinderSize);
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "usecaseType", nullable = true, insertable = true, updatable = true, length = 100)
    public String getUsecaseType() {
        return usecaseType;
    }

    public void setUsecaseType(String usecaseType) {
        this.usecaseType = usecaseType;
    }

    @Column(name = "usecaseType2", nullable = true, insertable = true, updatable = true, length = 100)
    public String getUsecaseType2() {
        return usecaseType2;
    }

    public void setUsecaseType2(String usecaseType2) {
        this.usecaseType2 = usecaseType2;
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

    @Column(name = "system", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Column(name = "capacity", nullable = true, insertable = true, updatable = true, length = 2)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Column(name = "fuelType", nullable = true, insertable = true, updatable = true, length = 100)
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

    @Column(name = "country", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "factory", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    @Column(name = "cylinderSize", nullable = true, insertable = true, updatable = true, length = 2)
    public int getCylinderSize() {
        return cylinderSize;
    }

    public void setCylinderSize(int cylinderSize) {
        this.cylinderSize = cylinderSize;
    }

    @Column(name = "tip", nullable = true, insertable = true, updatable = true, length = 200)
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return getFactory() + " " + getTip();
    }
}
