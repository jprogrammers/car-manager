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
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EN20 on 5/8/14.
 */
@ManagedBean
public class LicenceBean extends Licence {

    List<User> users;
    List<CarType> carTypes;

    List<Licence> licences;
    List<Licence> filteredLicences;

    User user;

    public LicenceBean(){

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User)session.getAttribute("user");

        users = UserService.getUsers(Role.USER, User.ACTIVE);

        carTypes = CarTypeService.getCarTypes();
    }

    public void addLicence(){
        if(Validator.isNullOrEmpty(getLicenceCode())){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_insert_valid_parameter"));
        } else {
            LicenceService.addLicence(getLicenceCode(), getCarTypeId(), getUserId());
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("licence_added_successfully"));
        }
    }

    public void editLicence(RowEditEvent event){
        Licence licence = (Licence)event.getObject();
        LicenceService.editLicence(licence);
        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("licence_edited_successfully"));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<CarType> getCarTypes() {
        return carTypes;
    }

    public List<Licence> getLicences() {
        if(user.getRoleId() == Role.ADMINISTRATOR)
            return LicenceService.getLicences();
        else
            return LicenceService.getLicences(user.getId());
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
