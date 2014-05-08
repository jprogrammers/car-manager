package com.jprogrammers.service;

import com.jprogrammers.model.CarType;

import java.util.List;

/**
 * Created by EN20 on 5/8/14.
 */
public class CarTypeService {

    static GenericDAO<CarType> carTypeDao = new GenericDAOImpl<CarType>();

    public static CarType getCarType(long id){
        String query = "from CarType where id = " + id;
        return carTypeDao.findOne(query);
    }

    public static List<CarType> getCarTypes(){
        String query = "from CarType";
        return carTypeDao.findMany(query);
    }

    public static CarType addCarType(String country, String factory, String usecaseType, String usecaseType2, String system,
                           String tip, int defCount, int wheelsCount, String fuelType, int cylinderCount, int capacity,
                           int cylinderSize){
        CarType carType = new CarType(CounterService.increment(), country, factory, usecaseType, usecaseType2, system,
                tip, defCount, wheelsCount, fuelType, cylinderCount, capacity, cylinderSize);
        carTypeDao.save(carType);

        return carType;

    }

    public static CarType editCarType(long id, String country, String factory, String usecaseType, String usecaseType2, String system,
                              String tip, int defCount, int wheelsCount, String fuelType, int cylinderCount, int capacity,
                              int cylinderSize){

        CarType carType = new CarType(id, country, factory, usecaseType, usecaseType2, system,
                tip, defCount, wheelsCount, fuelType, cylinderCount, capacity, cylinderSize);
        carTypeDao.save(carType);

        return carType;

    }

    public static void editCarType(CarType carType){

        carTypeDao.save(carType);

    }

    public static void deleteCarType(long id){
        CarType carType = getCarType(id);
        carTypeDao.delete(carType);
    }

}
