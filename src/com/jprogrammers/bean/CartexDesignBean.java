package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.CartexDesign;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Created by EN20 on 7/3/14.
 */
@ManagedBean
public class CartexDesignBean extends CartexDesign {

    UploadedFile file;

    public void addCartexDesign(){
        //CartexDesignService.addCartexDesign(getUserId(), getInformation(), getName1(), getTitle1(), getName2(), getTitle2(), getImage());
        //System.out.println(getFile().getFileName());
        System.out.println(getName1());
        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_design_added_successfully"));
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, "ane sag"));
    }

    public void uploadFile(FileUploadEvent event) {
        setFile(event.getFile());
        System.out.println(event.getFile().getFileName());
        System.out.println(getName1());
        System.out.println(getTitle1());
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
