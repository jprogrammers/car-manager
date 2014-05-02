package com.jprogrammers.model;

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
    private long userId;
    private Date createDate;
    private Date modifiedDate;

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

    @Column(name = "userId", nullable = true, insertable = true, updatable = true, length = 20)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
}
