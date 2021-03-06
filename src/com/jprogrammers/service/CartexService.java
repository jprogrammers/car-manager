package com.jprogrammers.service;

import com.jprogrammers.model.Cartex;

import java.util.Date;
import java.util.List;

/**
 * Created by EN20 on 5/8/14.
 */
public class CartexService {

    static GenericDAO<Cartex> cartexDao = new GenericDAOImpl<Cartex>();

    public static Cartex getCartex(long id){
        String query = "from Cartex where id = " + id;
        return cartexDao.findOne(query);
    }

    public static Cartex getCartexByBodyNumber(String bodyNumber){
        String query = "from Cartex where bodyNumber = '" + bodyNumber + "'";
        return cartexDao.findOne(query);
    }

    public static Cartex getCartexByEngineNumber(String engineNumber){
        String query = "from Cartex where engineNumber = '" + engineNumber + "'";
        return cartexDao.findOne(query);
    }

    public static List<Cartex> getCartexes(){
        String query = "from Cartex order by createDate desc";
        return cartexDao.findMany(query);
    }

    public static List<Cartex> getUserCartexes(long userId){
        String query = "from Cartex where userId = " + userId + " order by createDate desc";
        return cartexDao.findMany(query);
    }

    public static List<Cartex> getLicenceCartexes(long licenceId){
        String query = "from Cartex where licenceId = " + licenceId + " order by createDate desc";
        return cartexDao.findMany(query);
    }

    public static Cartex addCartex(long userId, long customerId, long licenceId, String color, String engineNumber, String bodyNumber,
                                   String VINNumber, String model, String boughtDate, String plateNumber){
        Cartex cartex = new Cartex(CounterService.increment(), userId, customerId, licenceId, color, engineNumber, bodyNumber, VINNumber,
                model, boughtDate, plateNumber);
        cartex.setCreateDate(new Date().getTime());
        cartexDao.save(cartex);

        return cartex;

    }

    public static Cartex editCartex(long id, long userId, long customerId, long licenceId, String color, String engineNumber, String bodyNumber,
                                    String VINNumber, String model, String boughtDate, String plateNumber){

        Cartex cartex = getCartex(id);
        cartex.setUserId(userId);
        cartex.setCustomerId(customerId);
        cartex.setLicenceId(licenceId);
        cartex.setColor(color);
        cartex.setEngineNumber(engineNumber);
        cartex.setBodyNumber(bodyNumber);
        cartex.setVINNumber(VINNumber);
        cartex.setModel(model);
        cartex.setBoughtDate(boughtDate);
        cartex.setPlateNumber(plateNumber);
        cartex.setModifiedDate(new Date().getTime());
        cartexDao.save(cartex);

        return cartex;

    }

    public static void editCartex(Cartex cartex){

        cartexDao.save(cartex);

    }

    public static void deleteCartex(long id){
        Cartex cartex = getCartex(id);
        cartexDao.delete(cartex);
    }

}
