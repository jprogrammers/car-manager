package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.Customer;
import com.jprogrammers.service.CustomerService;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 09/05/14.
 */
@ManagedBean
public class CustomerBean extends Customer {

    List<Customer> filteredCustomers;

    public List<Customer> getAllCustomers() {
        return CustomerService.getCustomers();
    }

    public void setFilteredCustomers(List<Customer> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void onEdit(RowEditEvent event) {
        Customer customer = (Customer) event.getObject();

        CustomerService.updateCustomer(customer);

        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteCustomer(long id) {
        CustomerService.deleteCustomer(id);
    }

    public void addCustomer() {
        CustomerService.addCustomer(getFirstName() , getLastName() , getNationalCode() , getNationalId() , getTell() , getMobile() ,
                getWorkTell() , getJobTitle() , getHomeAddress() , getWorkAddress() , getFatherName() , getCompany() , getProvince() ,
                getBirthday() , getZipCode());

        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("customer_added_successfully"));
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, ""));
    }

}
