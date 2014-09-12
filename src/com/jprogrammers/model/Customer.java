package com.jprogrammers.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */

@Entity
public class Customer {

    private long id;
    private String firstName;
    private String lastName;
    private Date createDate;
    private Date modifiedDate;
    private String nationalCode;
    private String nationalId;
    private String tell;
    private String mobile;
    private String workTell;
    private String jobTitle;
    private String homeAddress;
    private String workAddress;
    private String fatherName;
    private String company;
    private String province;
    private String birthday;
    private String zipCode;
    private long userId;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 20)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "nationalCode", nullable = true, insertable = true, updatable = true, length = 10)
    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Column(name = "nationalId", nullable = true, insertable = true, updatable = true, length = 20)
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @Column(name = "tell", nullable = true, insertable = true, updatable = true, length = 8)
    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    @Column(name = "mobile", nullable = true, insertable = true, updatable = true, length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "workTell", nullable = true, insertable = true, updatable = true, length = 8)
    public String getWorkTell() {
        return workTell;
    }

    public void setWorkTell(String workTell) {
        this.workTell = workTell;
    }

    @Column(name = "jobTitle", nullable = true, insertable = true, updatable = true, length = 500)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Column(name = "homeAddress", nullable = true, insertable = true, updatable = true, length = 5000)
    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Column(name = "workAddress", nullable = true, insertable = true, updatable = true, length = 5000)
    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    @Column(name = "fatherName", nullable = true, insertable = true, updatable = true, length = 500)
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Column(name = "company", nullable = true, insertable = true, updatable = true, length = 500)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Column(name = "province", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    @Column(name = "birthday", nullable = true, insertable = true, updatable = true, length = 50)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Column(name = "zipCode", nullable = true, insertable = true, updatable = true, length = 13)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "firstName", nullable = true, insertable = true, updatable = true, length = 500)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = true, insertable = true, updatable = true, length = 500)
    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return getNationalCode() + " - " + getFirstName() + " " + getLastName();
    }

    @Column(name = "userId", length = 20)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
