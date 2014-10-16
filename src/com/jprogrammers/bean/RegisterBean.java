package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.Customer;
import com.jprogrammers.model.Licence;
import com.jprogrammers.model.Role;
import com.jprogrammers.model.User;
import com.jprogrammers.service.CartexService;
import com.jprogrammers.service.CustomerService;
import com.jprogrammers.service.LicenceService;
import com.jprogrammers.service.UserService;
import com.jprogrammers.util.EmailUtil;
import com.jprogrammers.util.PWDGenerator;
import com.jprogrammers.util.Validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 04/07/14.
 */
@ManagedBean
public class RegisterBean extends CustomerBean {

    private long id;
    private long userId;
    private long customerId;
    private long licenceId;
    private String color;
    private String engineNumber;
    private String bodyNumber;
    private String VINNumber;
    private String model;
    private String boughtDate;
    private String plateNumber;
    private String economicCode;
    private List<User> adminUsers = new ArrayList<User>();

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;


    public RegisterBean(){
        adminUsers = UserService.getUsers(User.ADMINISTRATOR , User.ACTIVE);

    }

    public List<User> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<User> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(long licenceId) {
        this.licenceId = licenceId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getBodyNumber() {
        return bodyNumber;
    }

    public void setBodyNumber(String bodyNumber) {
        this.bodyNumber = bodyNumber;
    }

    public String getVINNumber() {
        return VINNumber;
    }

    public void setVINNumber(String VINNumber) {
        this.VINNumber = VINNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(String boughtDate) {
        this.boughtDate = boughtDate;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    public void addCartex(Customer customer){
        //todo get user licence to add licenceCode for cartex
        CartexService.addCartex(getUserId(), customer.getId(), getLicenceId(), getColor(), getEngineNumber(), getBodyNumber(), getVINNumber(),
                getModel(), getBoughtDate(), getPlateNumber());

    }

    public void register(){

        if (isCustomerFieldsValid()) {

            if (CustomerService.getCustomerByNationalCode(getNationalCode()) != null) {
                Customer customer = CustomerService.getCustomerByNationalCode(getNationalCode());

                if(isCartexDataValid()){
                    addCartex(customer);

                    emptyFields();
                    emptyCartexField();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_added_successfully"), ""));

                    return;
                }
            }

            String generatedPassword = PWDGenerator.generatePassword(6);
            User user = UserService.addUser(getFirstName() , getLastName(),getEmail() ,generatedPassword , getTell() , getHomeAddress() , 0 , Role.CUSTOMER);

            Customer customer = CustomerService.addCustomer(getFirstName(), getLastName(), getNationalCode(), getNationalId(), getTell(), getMobile(),
                    getWorkTell(), getJobTitle(), getHomeAddress(), getWorkAddress(), getFatherName(), getCompany(), getProvince(),
                    getBirthday(), getZipCode() , user.getId());

            if(isCartexDataValid()){

                addCartex(customer);

                EmailUtil.sendEmail(getEmail(), LanguageUtil.get("your_account_information"), LanguageUtil.get("your_account_information" , getEmail() ,generatedPassword));

                emptyFields();
                emptyCartexField();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_added_successfully"), ""));
            } else {
                CustomerService.deleteCustomer(customer.getId());
                UserService.delete(user);
            }
        }


    }

    private void emptyCartexField() {
        setEmail("");
        setBodyNumber("");
        setBoughtDate("");
        setModel("");
        setColor("");
        setEngineNumber("");
        setBodyNumber("");
        setVINNumber("");
    }

    protected boolean isCartexDataValid(){
        if (Validator.isNullOrEmpty(getColor())){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_enter_valid_color"));
            return false;
        }
        if (Validator.isNullOrEmpty(getEngineNumber())) {
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_enter_valid_engine_number"));
            return false;
        }
        if (Validator.isNullOrEmpty(getBodyNumber())) {
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_enter_valid_body_number"));
            return false;
        }
        if (Validator.isNullOrEmpty(getModel())) {
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_enter_valid_model"));
            return false;
        }

        if (CartexService.getCartexByBodyNumber(getBodyNumber()) != null){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("body_number_must_be_unique"));
            return false;
        }
        if (CartexService.getCartexByEngineNumber(getEngineNumber()) != null){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("engine_number_must_be_unique"));
            return false;
        }

        return true;
    }


    public String getEmail() {
        return email;
    }

    /*public void checkAvailableUserData(){

        User user = null;
        if (!Validator.isNullOrEmpty(getEmail())){
            user = UserService.getUserByEmailAndRole(getEmail() , Role.CUSTOMER);
            if (user != null) {
                Customer customer =  CustomerService.getCustomerByUserId(user.getId());
                if (customer != null) {
                    populateCustomerData(customer);
                }
            }
        }
        if (!Validator.isNullOrEmpty(getNationalCode())) {
            Customer customer =  CustomerService.getCustomerByNationalCode(getNationalCode());
            if (customer != null) {
                populateCustomerData(customer);
            }
        }
    }*/

    public void populateCustomerData(Customer customer){

        setFirstName(customer.getFirstName());
        setLastName(customer.getLastName());
        setNationalCode(customer.getNationalCode());
        setNationalId(customer.getNationalId());
        setTell(customer.getTell());
        setMobile(customer.getMobile());
        setWorkTell(customer.getWorkTell());
        setJobTitle(customer.getJobTitle());
        setHomeAddress(customer.getHomeAddress());
        setWorkAddress(customer.getWorkAddress());
        setFatherName(customer.getFatherName());
        setCompany(customer.getCompany());
        setProvince(customer.getProvince());
        setBirthday(customer.getBirthday());
        setZipCode(customer.getZipCode());

    }

}
