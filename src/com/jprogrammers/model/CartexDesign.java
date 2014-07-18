package com.jprogrammers.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * Created by EN20 on 7/3/14.
 */
@Entity
public class CartexDesign {
    private long id;
    private long userId;
    private String information;
    private String name1;
    private String title1;
    private String name2;
    private String title2;
    private byte[] image;

    public CartexDesign(){

    }

    public CartexDesign(long id, long userId, String information, String name1, String title1, String name2, String title2, byte[] image){
        setId(id);
        setUserId(userId);
        setInformation(information);
        setName1(name1);
        setTitle1(title1);
        setName2(name2);
        setTitle2(title2);
        setImage(image);
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "information", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Basic
    @Column(name = "name1", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    @Basic
    @Column(name = "title1", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    @Basic
    @Column(name = "name2", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Basic
    @Column(name = "title2", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    @Basic
    @Column(name = "image", nullable = true, insertable = true, updatable = true, length = 1048575, precision = 0)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
