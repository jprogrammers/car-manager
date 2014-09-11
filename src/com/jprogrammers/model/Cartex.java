package com.jprogrammers.model;

import com.jprogrammers.service.CustomerService;
import com.jprogrammers.service.LicenceService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */

@Entity
public class Cartex {

    private long id;
    private long userId;
    private long customerId;
    private long licenceId;
    private String color;
    private String engineNumber;
    private String bodyNumber;
    private String VINNumber;
    private String model;
    private String boughtDate;
    private String plateNumber;
    private String economicCode;
    private long createDate;
    private long modifiedDate;

    public Cartex(){

    }

    public Cartex(long id,long userId, long customerId, long licenceId, String color, String engineNumber, String bodyNumber,
                  String VINNumber, String model, String boughtDate, String plateNumber, String economicCode){
        setId(id);
        setUserId(userId);
        setCustomerId(customerId);
        setLicenceId(licenceId);
        setColor(color);
        setEngineNumber(engineNumber);
        setBodyNumber(bodyNumber);
        setVINNumber(VINNumber);
        setModel(model);
        setBoughtDate(boughtDate);
        setPlateNumber(plateNumber);
        setEconomicCode(economicCode);
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "userId", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "customerId", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Column(name = "licenceId", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    public long getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(long licenceId) {
        this.licenceId = licenceId;
    }

    @Column(name = "color", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Column(name = "VINNumber", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
    public String getVINNumber() {
        return VINNumber;
    }

    public void setVINNumber(String VINNumber) {
        this.VINNumber = VINNumber;
    }

    @Column(name = "model", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    @Column(name = "createDate", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    @Column(name = "modifiedDate", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Transient
    public Licence getLicence(){
        return LicenceService.getLicence(getLicenceId());
    }

    @Transient
    public Customer getCustomer(){
        return CustomerService.getCustomer(getCustomerId());
    }
}
