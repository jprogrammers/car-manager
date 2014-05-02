package com.jprogrammers.service;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by Vahid Forghani
 */

public interface GenericDAO<T> {
		 
    public void save(T entity, Session session);
 
    public void delete(T entity, Session session);
    
    public List<T> findMany(String query, Session session);
 
    public T findOne(String query, Session session);
 
}
