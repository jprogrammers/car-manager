package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.*;
import com.jprogrammers.service.*;
import com.jprogrammers.util.Validator;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EN20 on 5/15/14.
 */
@ManagedBean
public class CartexBean extends Cartex {

    List<Customer> customers;
    List<Licence> licences;
    List<Cartex> cartexes;
    List<Cartex> filteredCartexes;
    User user;

    public CartexBean(){

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User)session.getAttribute("user");

        customers = CustomerService.getCustomers();

        if(user.getRoleId() == Role.ADMINISTRATOR)
            licences = LicenceService.getLicences();
        else
            licences = LicenceService.getLicences(user.getId());

        if(user.getRoleId() == Role.ADMINISTRATOR)
            setCartexes(CartexService.getCartexes());
        else
            setCartexes(CartexService.getCartexes(user.getId()));
    }

    public void addCartex(){
        if(Validator.isNullOrEmpty(getColor()) || Validator.isNullOrEmpty(getEngineNumber()) || Validator.isNullOrEmpty(getBodyNumber()) ||
                Validator.isNullOrEmpty(getModel()) || getBodyNumber().length() != 17){
            FacesContext.getCurrentInstance().addMessage(null ,new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_insert_valid_parameter"),""));
        } else {
            CartexService.addCartex(user.getId(), getCustomerId(), getLicenceId(), getColor(), getEngineNumber(), getBodyNumber(), getVINNumber(),
                    getModel(), getBoughtDate(), getPlateNumber(), getEconomicCode());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_added_successfully"), ""));
        }
    }

    public void editCartex(RowEditEvent event){
        Cartex cartex = (Cartex)event.getObject();
        CartexService.editCartex(cartex);

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        User user = (User) session.getAttribute("user");

        CartexEditRequest cartexRequest = CartexEditRequestService.getEditRequestByCartexAndUser(cartex.getId(), user.getId());

        if (cartexRequest != null) {
            CartexEditRequestService.deleteCartexEditRequest(cartexRequest);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_edited_successfully"), ""));
    }

    public void deleteCartex(long id){
        CartexService.deleteCartex(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_deleted_successfully"), ""));
    }

    public void requestForEdit(long cartexId , long userId){
        CartexEditRequestService.updateCartextEditRequest(CounterService.increment() , userId , cartexId , CartexEditRequest.STATUS_PENDING);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("edit_request_successfully_sent_wait_for_response"), ""));
    }

    public boolean hasPendingRequest(long cartexId , long userId){

        User user = UserService.getUser(userId);

        CartexEditRequest editRequest = CartexEditRequestService.getEditRequestByCartexAndUser(cartexId, userId);

        if (editRequest != null && editRequest.getStatus() == CartexEditRequest.STATUS_PENDING && user.getRoleId() != Role.ADMINISTRATOR) {
            return true;
        }

        return false;
    }
     public boolean hasEditPermission(long cartexId , long userId) {

         CartexEditRequest editRequest = CartexEditRequestService.getEditRequestByCartexAndUser(cartexId, userId);

         return ((editRequest != null && editRequest.getStatus() == CartexEditRequest.STATUS_ALLOWED) || UserService.getUser(userId).getRoleId() == Role.ADMINISTRATOR);

    }

    public boolean canRequest(long cartexId , long userId) {
        CartexEditRequest editRequest = CartexEditRequestService.getEditRequestByCartexAndUser(cartexId, userId);

        return (editRequest == null  && UserService.getUser(userId).getRoleId() != Role.ADMINISTRATOR);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Licence> getLicences() {
        return licences;
    }

    public void setLicences(List<Licence> licences) {
        this.licences = licences;
    }

    public List<Cartex> getCartexes() {
        return cartexes;
    }

    public void setCartexes(List<Cartex> cartexes) {
        this.cartexes = cartexes;
    }

    public List<Cartex> getFilteredCartexes() {
        return filteredCartexes;
    }

    public void setFilteredCartexes(List<Cartex> filteredCartexes) {
        this.filteredCartexes = filteredCartexes;
    }
}
