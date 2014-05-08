package com.jprogrammers.service;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by Vahid Forghani
 */

public interface GenericDAO<T> {
		 
    public void save(T entity);
 
    public void delete(T entity);
    
    public List<T> findMany(String query);
 
    public T findOne(String query);
 
}
