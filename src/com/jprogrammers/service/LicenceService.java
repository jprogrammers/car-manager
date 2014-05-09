package com.jprogrammers.service;

import com.jprogrammers.model.Licence;

import java.util.Date;
import java.util.List;

/**
 * Created by EN20 on 5/8/14.
 */
public class LicenceService {

    static GenericDAO<Licence> licenceDao = new GenericDAOImpl<Licence>();

    public static List<Licence> getLicences(){
        String query = "from Licence";
        return licenceDao.findMany(query);
    }

    public static Licence getLicence(long id){
        String query = "from Licence where id = " + id;
        return licenceDao.findOne(query);
    }

    public static void addLicence(String licenceCode, long carTypeId, long userId){
        Licence licence = new Licence(CounterService.increment(), licenceCode, carTypeId, userId, new Date(), null);
        licenceDao.save(licence);
    }

}