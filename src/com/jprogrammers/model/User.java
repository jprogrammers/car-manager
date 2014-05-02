package com.jprogrammers.model;

import com.jprogrammers.service.UserService;
import org.primefaces.model.SelectableDataModel;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */

@Entity
public class User implements SelectableDataModel<User> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;


    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private Date createDate;
    private Date modifiedDate;
    private int roleId;
    private int status;

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getId() {

        return id;
    }

    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 500)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "roleId", nullable = true, insertable = true, updatable = true, length = 4)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "emailAddress", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true, length = 1)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public Object getRowKey(User user) {
        return Long.valueOf(user.getId());
    }

    @Override
    public User getRowData(String s) {
        return UserService.getUser(Long.valueOf(id));
    }
}
