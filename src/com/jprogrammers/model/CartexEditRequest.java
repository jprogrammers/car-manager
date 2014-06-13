package com.jprogrammers.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Ali Reza Akbarian
 *         created on 13/06/14.
 */

@Entity
public class CartexEditRequest implements Serializable {

    @Transient
    public static final int STATUS_PENDING = 1;
    @Transient
    public static final int STATUS_ALLOWED = 2;

    private long id;
    private long userId;
    private long cartexId;
    private Date createDate;
    private int status;


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


    @Column(name = "cartexId", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getCartexId() {
        return cartexId;
    }

    public void setCartexId(long cartexId) {
        this.cartexId = cartexId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "createDate", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true, length = 1)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
