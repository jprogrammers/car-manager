package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.CarType;
import com.jprogrammers.model.Licence;
import com.jprogrammers.service.CarTypeService;
import com.jprogrammers.service.LicenceService;
import com.jprogrammers.util.Validator;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by EN20 on 5/8/14.
 */
@ManagedBean
@ViewScoped
public class CarTypeBean extends CarType implements Serializable {

    List<CarType> carTypes;
    List<CarType> filteredCarTypes;

    public CarTypeBean(){
        init();
    }

    private void init(){
        setCarTypes(CarTypeService.getCarTypes());
    }

    public void addCarType(){

        if(Validator.isNullOrEmpty(getCountry()) || Validator.isNullOrEmpty(getFactory()) || Validator.isNullOrEmpty(getUsecaseType()) ||
                Validator.isNullOrEmpty(getUsecaseType2()) || Validator.isNullOrEmpty(getSystem()) || Validator.isNullOrEmpty(getTip()) ||
                Validator.isNullOrEmpty(getFuelType())){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_insert_valid_parameter"));
        } else {
            CarTypeService.addCarType(getCountry(), getFactory(), getUsecaseType(), getUsecaseType2(), getSystem(), getTip(), getDefCount(),
                    getWheelsCount(), getFuelType(), getCylinderCount(), getCapacity(), getCylinderSize());
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("car_type_added_successfully"));
        }
        emptyFields();
        init();
    }

    private void emptyFields(){
        setCountry("");
        setFactory("");
        setUsecaseType("");
        setUsecaseType2("");
        setSystem("");
        setTip("");
        setDefCount(0);
        setWheelsCount(0);
        setFuelType("");
        setCylinderCount(0);
        setCapacity(0);
        setCylinderSize(0);
    }

    public void editCarType(RowEditEvent event){
        CarType carType = (CarType)event.getObject();
        CarTypeService.editCarType(carType);
        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("car_type_edited_successfully"));
        init();
    }

    public void deleteCarType(long id){
        List<Licence> carTypeLicences = LicenceService.getCarTypeLicence(id);
        if(carTypeLicences != null && carTypeLicences.size() > 0){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("there_is_licence_with_this_car_type"));
        } else {
            CarTypeService.deleteCarType(id);
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("car_type_deleted_successfully"));
            init();
        }

    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, ""));
    }

    public List<CarType> getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(List<CarType> carTypes) {
        this.carTypes = carTypes;
    }

    public List<CarType> getFilteredCarTypes() {
        return filteredCarTypes;
    }

    public void setFilteredCarTypes(List<CarType> filteredCarTypes) {
        this.filteredCarTypes = filteredCarTypes;
    }
}
