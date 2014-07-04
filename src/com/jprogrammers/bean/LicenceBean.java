package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.*;
import com.jprogrammers.service.CarTypeService;
import com.jprogrammers.service.CartexService;
import com.jprogrammers.service.LicenceService;
import com.jprogrammers.service.UserService;
import com.jprogrammers.util.Validator;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EN20 on 5/8/14.
 */
@ManagedBean
@ViewScoped
public class LicenceBean extends Licence {

    List<User> users;
    List<CarType> carTypes;

    List<Licence> licences;
    List<Licence> filteredLicences;

    User user;

    public LicenceBean(){

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User)session.getAttribute("user");

        users = UserService.getUsers(User.ADMINISTRATOR, User.ACTIVE);

        carTypes = CarTypeService.getCarTypes();

        init();
    }

    private void init(){
        if(user.getRoleId() == User.GOD)
            setLicences(LicenceService.getLicences());
        else
            setLicences(LicenceService.getLicences(user.getUserId()));
    }

    public void addLicence(){
        if(Validator.isNullOrEmpty(getLicenceCode())){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_insert_valid_parameter"));
        } else {
            LicenceService.addLicence(getLicenceCode(), getCarTypeId(), getUserId());
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("licence_added_successfully"));
        }
        init();
    }

    public void editLicence(RowEditEvent event){
        Licence licence = (Licence)event.getObject();
        LicenceService.editLicence(licence);
        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("licence_edited_successfully"));
        init();
    }

    public void deleteLicence(long id){
        List<Cartex> licenceCartexes = CartexService.getLicenceCartexes(id);
        if(licenceCartexes != null && licenceCartexes.size() > 0){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("there_is_cartex_with_this_licence"));
        } else {
            LicenceService.deleteLicence(id);
        }

    }

    public List<User> getUsers() {
        return users;
    }

    public List<CarType> getCarTypes() {
        return carTypes;
    }

    public List<Licence> getLicences() {
        return licences;
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
