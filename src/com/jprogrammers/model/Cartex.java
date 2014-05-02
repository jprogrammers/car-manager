package com.jprogrammers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */

@Entity
public class Cartex {

    private long id;
    private long customerId;
    private long carTypeId;
    private String engineNumber;
    private String bodyNumber;
    private String boughtDate;
    private String plateNumber;
    private String economicCode;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "customerId", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Column(name = "carTypeId", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    public long getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(long carTypeId) {
        this.carTypeId = carTypeId;
    }

    @Column(name = "engineNumber", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    @Column(name = "bodyNumber", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    public String getBodyNumber() {
        return bodyNumber;
    }

    public void setBodyNumber(String bodyNumber) {
        this.bodyNumber = bodyNumber;
    }

    @Column(name = "boughtDate", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    public String getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(String boughtDate) {
        this.boughtDate = boughtDate;
    }

    @Column(name = "plateNumber", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Column(name = "economicCode", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    public String getEconomicCode() {
        return economicCode;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }
}
