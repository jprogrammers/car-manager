package com.jprogrammers.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Ali Reza Akbarian
 *         created on 20/04/14.
 */

@Entity
public class Counter {


    public static final String defaultCounter = "defaultCounter";

    private String name;
    private Long currentId;

    @Id
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "currentId", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    public Long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Long currentId) {
        this.currentId = currentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Counter counter = (Counter) o;

        if (currentId != null ? !currentId.equals(counter.currentId) : counter.currentId != null) return false;
        if (name != null ? !name.equals(counter.name) : counter.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (currentId != null ? currentId.hashCode() : 0);
        return result;
    }

}
