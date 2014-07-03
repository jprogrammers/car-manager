package com.jprogrammers.service;

import com.jprogrammers.model.Cartex;
import com.jprogrammers.model.CartexEditRequest;

import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 13/06/14.
 */
public class CartexEditRequestService {

    static GenericDAO<CartexEditRequest> requestDao = new GenericDAOImpl<CartexEditRequest>();


    public static CartexEditRequest updateCartextEditRequest(long id , long userId , long cartexId , int status ) {

        CartexEditRequest editRequest = getCartexEditRequest(id);

        if (editRequest == null) {
            editRequest = new CartexEditRequest();
        }
        editRequest.setId(id);
        editRequest.setUserId(userId);
        editRequest.setCartexId(cartexId);
        editRequest.setStatus(status);

        requestDao.save(editRequest);

        return editRequest;
    }

    public static CartexEditRequest getCartexEditRequest(long id) {

        return requestDao.findOne("from CartexEditRequest where id = " + id);
    }


    public static CartexEditRequest getEditRequestByCartexAndUser(long cartexId, long userId) {

        return requestDao.findOne("from CartexEditRequest where cartexId = " + cartexId + " and userId = " + userId);
    }

    public static List<CartexEditRequest> getByStatus(int status) {

        return requestDao.findMany("from CartexEditRequest where status = " + status + " order by createDate desc");
    }

    public static void deleteCartexEditRequest(long requestId) {

        requestDao.delete(getCartexEditRequest(requestId));
    }

    public static void deleteCartexEditRequest(CartexEditRequest cartexRequest) {

        requestDao.delete(cartexRequest);
    }
}
