package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.CartexDesign;
import com.jprogrammers.model.User;
import com.jprogrammers.service.CartexDesignService;
import org.apache.poi.util.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by EN20 on 7/3/14.
 */
@ManagedBean
public class CartexDesignBean extends CartexDesign {

    User user;
    CartexDesign cartexDesign;

    public CartexDesignBean(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User)session.getAttribute("user");
        init();
    }

    private void init(){
        cartexDesign = CartexDesignService.getCartexDesignByUserId(user.getId());
        if(cartexDesign != null){
            setInformation(cartexDesign.getInformation());
            setFileNumber(cartexDesign.getFileNumber());
            setCity(cartexDesign.getCity());
            setName1(cartexDesign.getName1());
            setTitle1(cartexDesign.getTitle1());
            setName2(cartexDesign.getName2());
            setTitle2(cartexDesign.getTitle2());
        }
    }

    public void addCartexDesign(){
        CartexDesign cartexDesign = CartexDesignService.getCartexDesignByUserId(user.getId());
        if(cartexDesign != null){
            cartexDesign.setInformation(getInformation());
            cartexDesign.setFileNumber(getFileNumber());
            cartexDesign.setCity(getCity());
            cartexDesign.setName1(getName1());
            cartexDesign.setTitle1(getTitle1());
            cartexDesign.setName2(getName2());
            cartexDesign.setTitle2(getTitle2());
            CartexDesignService.editCartexDesign(cartexDesign);
        } else {
            CartexDesignService.addCartexDesign(user.getId(), getInformation(), getFileNumber(), getCity(), getName1(), getTitle1(), getName2(), getTitle2(), null);
        }
        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_design_added_successfully"));
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, ""));
    }

    public void uploadFile(FileUploadEvent event) throws IOException {
        CartexDesign cartexDesign = CartexDesignService.getCartexDesignByUserId(user.getId());
        if(cartexDesign != null){
            cartexDesign.setImage(IOUtils.toByteArray(event.getFile().getInputstream()));
            CartexDesignService.editCartexDesign(cartexDesign);
        } else {
            CartexDesignService.addCartexDesign(user.getId(), "", "", "", "", "", "", "", IOUtils.toByteArray(event.getFile().getInputstream()));
        }
        init();
    }

    public StreamedContent getCartexImage() {
        if(cartexDesign != null && cartexDesign.getImage() != null)
            return new DefaultStreamedContent(new ByteArrayInputStream(cartexDesign.getImage()));
        return null;
    }
}
