package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.*;
import com.jprogrammers.service.CartexService;
import com.jprogrammers.service.CustomerService;
import com.jprogrammers.service.LicenceService;
import com.jprogrammers.util.Validator;

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
    }

    public void addCartex(){
        if(Validator.isNullOrEmpty(getColor()) || Validator.isNullOrEmpty(getEngineNumber()) || Validator.isNullOrEmpty(getBodyNumber()) ||
                Validator.isNullOrEmpty(getVINNumber()) || Validator.isNullOrEmpty(getModel()) || Validator.isNullOrEmpty(getEconomicCode())){
            FacesContext.getCurrentInstance().addMessage(null ,new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_insert_valid_parameter"),""));
        } else {
            CartexService.addCartex(user.getId(), getCustomerId(), getLicenceId(), getColor(), getEngineNumber(), getBodyNumber(), getVINNumber(),
                    getModel(), getBoughtDate(), getPlateNumber(), getEconomicCode());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_added_successfully"), ""));
        }
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
        if(user.getRoleId() == Role.ADMINISTRATOR)
            return CartexService.getCartexes();
        else
            return CartexService.getCartexes(user.getId());
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
