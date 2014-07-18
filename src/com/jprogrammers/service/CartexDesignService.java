package com.jprogrammers.service;

import com.jprogrammers.model.CartexDesign;

import java.util.List;

/**
 * Created by EN20 on 5/8/14.
 */
public class CartexDesignService {

    static GenericDAO<CartexDesign> cartexDesignDao = new GenericDAOImpl<CartexDesign>();

    public static CartexDesign getCartexDesign(long id){
        String query = "from CartexDesign where id = " + id;
        return cartexDesignDao.findOne(query);
    }

    public static List<CartexDesign> getCartexDesigns(){
        String query = "from CartexDesign";
        return cartexDesignDao.findMany(query);
    }

    public static CartexDesign addCartexDesign(long userId, String information, String name1, String title1, String name2, String title2, byte[] image){

        CartexDesign cartexDesign = new CartexDesign(CounterService.increment(), userId, information, name1, title1, name2, title2, image);
        cartexDesignDao.save(cartexDesign);

        return cartexDesign;

    }

    public static CartexDesign editCartexDesign(long cartexDesignId, long userId, String information, String name1, String title1, String name2, String title2, byte[] image){

        CartexDesign cartexDesign = new CartexDesign(cartexDesignId, userId, information, name1, title1, name2, title2, image);
        cartexDesignDao.save(cartexDesign);

        return cartexDesign;

    }

    public static void editCartexDesign(CartexDesign cartexDesign){

        cartexDesignDao.save(cartexDesign);

    }

    public static void deleteCartexDesign(long id){
        CartexDesign cartexDesign = getCartexDesign(id);
        cartexDesignDao.delete(cartexDesign);
    }

    public static CartexDesign getCartexDesignByUserId(long userId) {
        String query = "from CartexDesign where userId = " + userId;
        return cartexDesignDao.findOne(query);
    }
}
