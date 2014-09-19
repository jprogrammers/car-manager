package com.jprogrammers.model;

import com.jprogrammers.service.CarTypeService;
import com.jprogrammers.service.UserService;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */

@Entity
public class Licence {

    private long id;
    private String licenceCode;
    private long carTypeId;
    //private long userId;
    private Date createDate;
    private Date modifiedDate;

    public Licence(){

    }

    public Licence(long id, String licenceCode, long carTypeId, Date createDate, Date modifiedDate){
        setId(id);
        setLicenceCode(licenceCode);
        setCarTypeId(carTypeId);
        setCreateDate(createDate);
        setModifiedDate(modifiedDate);
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "licenceCode", nullable = true, insertable = true, updatable = true, length = 500)
    public String getLicenceCode() {
        return licenceCode;
    }

    public void setLicenceCode(String licenceCode) {
        this.licenceCode = licenceCode;
    }

    @Column(name = "carTypeId", nullable = true, insertable = true, updatable = true, length = 20)
    public long getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(long carTypeId) {
        this.carTypeId = carTypeId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "createDate", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "modifiedDate", nullable = true, insertable = true, updatable = true)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Transient
    public CarType getCarType(){
        return CarTypeService.getCarType(getCarTypeId());
    }

    @Override
    public String toString() {
        CarType carType = CarTypeService.getCarType(getCarTypeId());
        return getLicenceCode() + " - " + (carType != null ? carType : "");
    }
}
