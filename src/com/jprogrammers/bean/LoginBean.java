package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.User;
import com.jprogrammers.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vahid Forghani on 1/25/14.
 */

@ManagedBean
public class LoginBean {

    String email;
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void doLogin() throws IOException {
        User user = UserService.login(getEmail(), getPassword());
        if(user != null){
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("login", true);
            session.setAttribute("user", user);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/page/index.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("invalid_username_or_email"), ""));
        }
    }

    public void doLogout() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/page/login.xhtml");
    }

}
