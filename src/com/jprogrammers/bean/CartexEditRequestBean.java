package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.Cartex;
import com.jprogrammers.model.CartexEditRequest;
import com.jprogrammers.model.User;
import com.jprogrammers.service.CartexEditRequestService;
import com.jprogrammers.service.CartexService;
import com.jprogrammers.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 13/06/14.
 */

@ManagedBean(name = "requestBean")
public class CartexEditRequestBean extends CartexEditRequest {

    public List<CartexEditRequest> pendingRequests;

    public List<CartexEditRequest> getPendingRequests() {
        return pendingRequests;
    }

    public CartexEditRequestBean () {
        pendingRequests = CartexEditRequestService.getByStatus(CartexEditRequest.STATUS_PENDING);
    }

    public String getCartexUser(long cartexId){

        Cartex cartex = CartexService.getCartex(cartexId);

        if (cartex != null) {
            return UserService.getUser(cartex.getUserId()).toString();
        } else {
            return LanguageUtil.get("cartex_dose_not_exist");
        }
    }

    public String getUserFullName(long userId) {
        User user = UserService.getUser(userId);

        if (user != null) {
            return ((user.getFirstName() != null)? user.getFirstName() : "" )+ " " + ((user.getLastName() != null)? user.getLastName() : "");

        } else {
            return LanguageUtil.get("user_dose_not_exist");
        }
    }

    public void acceptRequest(long requestId) {
        CartexEditRequest request = CartexEditRequestService.getCartexEditRequest(requestId);

        CartexEditRequestService.updateCartextEditRequest(requestId , request.getUserId() , request.getCartexId() , CartexEditRequest.STATUS_ALLOWED);

        pendingRequests = CartexEditRequestService.getByStatus(CartexEditRequest.STATUS_PENDING);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("your_request_was_completed"), ""));

    }

    public void denyRequest(long requestId) {
        CartexEditRequestService.deleteCartexEditRequest(requestId);

        pendingRequests = CartexEditRequestService.getByStatus(CartexEditRequest.STATUS_PENDING);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("your_request_was_completed"), ""));
    }


}
