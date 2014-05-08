package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.CarType;
import com.jprogrammers.model.Licence;
import com.jprogrammers.model.Role;
import com.jprogrammers.model.User;
import com.jprogrammers.service.CarTypeService;
import com.jprogrammers.service.LicenceService;
import com.jprogrammers.service.UserService;
import com.jprogrammers.util.Validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.util.List;

/**
 * Created by EN20 on 5/8/14.
 */
@ManagedBean
public class LicenceBean extends Licence {

    SelectItem[] users;
    SelectItem[] carTypes;

    List<Licence> licences;
    List<Licence> filteredLicences;

    public LicenceBean(){
        List<User> legalUsers = UserService.getUsers(Role.USER, User.ACTIVE);
        User user;
        users = new SelectItem[legalUsers.size()];
        for(int i = 0; i < legalUsers.size(); i++){
            user = legalUsers.get(i);
            users[i] = new SelectItem(user.getId(), user.getFirstName());
        }

        List<CarType> cars = CarTypeService.getCarTypes();
        CarType carType;
        carTypes = new SelectItem[cars.size()];
        for(int i = 0; i < cars.size(); i++){
            carType = cars.get(i);
            carTypes[i] = new SelectItem(carType.getId(), carType.toString());
        }
    }

    public void addLicence(){
        if(Validator.isNullOrEmpty(getLicenceCode())){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_insert_valid_parameter"));
        } else {
            LicenceService.addLicence(getLicenceCode(), getCarTypeId(), getUserId());
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("licence_added_successfully"));
        }
    }

    public SelectItem[] getUsers() {
        return users;
    }

    public SelectItem[] getCarTypes() {
        return carTypes;
    }

    public List<Licence> getLicences() {
        return LicenceService.getLicences();
    }

    public void setLicences(List<Licence> licences) {
        this.licences = licences;
    }

    public List<Licence> getFilteredLicences() {
        return filteredLicences;
    }

    public void setFilteredLicences(List<Licence> filteredLicences) {
        this.filteredLicences = filteredLicences;
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, ""));
    }
}
