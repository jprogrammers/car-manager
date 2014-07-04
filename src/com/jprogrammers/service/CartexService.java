package com.jprogrammers.service;

import com.jprogrammers.model.Cartex;

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

    public static List<Cartex> getCartexes(){
        String query = "from Cartex";
        return cartexDao.findMany(query);
    }

    public static List<Cartex> getUserCartexes(long userId){
        String query = "from Cartex where userId = " + userId;
        return cartexDao.findMany(query);
    }

    public static List<Cartex> getLicenceCartexes(long licenceId){
        String query = "from Cartex where licenceId = " + licenceId;
        return cartexDao.findMany(query);
    }

    public static Cartex addCartex(long userId, long customerId, long licenceId, String color, String engineNumber, String bodyNumber,
                                   String VINNumber, String model, String boughtDate, String plateNumber, String economicCode){
        Cartex cartex = new Cartex(CounterService.increment(), userId, customerId, licenceId, color, engineNumber, bodyNumber, VINNumber,
                model, boughtDate, plateNumber, economicCode);
        cartexDao.save(cartex);

        return cartex;

    }

    public static Cartex editCartex(long id, long userId, long customerId, long licenceId, String color, String engineNumber, String bodyNumber,
                                    String VINNumber, String model, String boughtDate, String plateNumber, String economicCode){

        Cartex cartex = new Cartex(id, userId, customerId, licenceId, color, engineNumber, bodyNumber, VINNumber,
                model, boughtDate, plateNumber, economicCode);
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
