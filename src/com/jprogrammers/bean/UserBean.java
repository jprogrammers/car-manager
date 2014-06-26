package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageFa;
import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.Role;
import com.jprogrammers.model.User;
import com.jprogrammers.service.CartexService;
import com.jprogrammers.service.UserService;
import com.jprogrammers.util.PWDEncryption;
import com.jprogrammers.util.Validator;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 25/04/14.
 */

@ManagedBean
public class UserBean extends User implements Serializable {

    private List<User> filteredUsers;
    private List<User> allUsers;

    private SelectItem[] roleOptions;
    private SelectItem[] statusOptions;

    private int[] statuses = new int[]{0 , 1};
    private int[] roleIds = Role.roleIds;

    private String oldPassword;
    private String confirmPassword;

    public SelectItem[] getRoleOptions() {
        return roleOptions;
    }

    public UserBean() {
        createRoleOptions();
        createStatusOptions();
        init();
    }

    private void init(){
        setAllUsers(UserService.getUsers());
    }

    private void createStatusOptions() {
        statusOptions = new SelectItem[3];

        statusOptions[0] = new SelectItem("" , LanguageUtil.get("everything"));
        statusOptions[1] = new SelectItem(1 , LanguageUtil.get("active"));
        statusOptions[2] = new SelectItem(0 , LanguageUtil.get("inactive"));

    }

    private void createRoleOptions() {
        String[] roleNames = Role.roleNames;
        roleOptions = new SelectItem[roleNames.length + 1];

        roleOptions[0] = new SelectItem("" , LanguageUtil.get("everything"));

        for ( int i = 0 ; i < roleNames.length ; i++ ) {
            roleOptions[i + 1] = new SelectItem(Role.roleIds[i] , LanguageUtil.get(Role.roleNames[i]));
        }
    }

    public List<User> filterByRoles(String roleName) {
        return UserService.getUsers(Role.USER , User.ACTIVE);
    }

    public List<User> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    public String getRoleName(int roleId) {
        return LanguageUtil.get(Role.roleNames[roleId]);
    }

    public SelectItem[] getStatusOptions() {
        return statusOptions;
    }

    public String getStatusName(int status) {
        return (status == 1)? LanguageUtil.get("active") : LanguageUtil.get("inactive");
    }

    public void onEdit(RowEditEvent event) {
        User user = (User) event.getObject();

        UserService.updateUser(user);

        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("user_edited_successfully"));
    }

    public int[] getStatuses() {
        return statuses;
    }

    public int[] getRoleIds() {
        return roleIds;
    }

    public void deleteUser(long id) {

        if (CartexService.getCartexes(id).size() == 0) {
            UserService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("user_deleted_successfully"));
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("you_cant_delete_this_user_because_he_has_cartex"));
        }
        init();
    }

    public void addUser() throws IOException {
        UserService.addUser(getFirstName() , getLastName() , getEmailAddress() , getPassword() , getTell() , getAddress());

        addMessage(FacesMessage.SEVERITY_INFO , LanguageUtil.get("your_request_was_completed"));
        emptyFields();
        init();
    }

    private void emptyFields(){
        setFirstName("");
        setLastName("");
        setEmailAddress("");
        setPassword("");
        setTell("");
        setAddress("");
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, ""));
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void selectUser(User user){
        setId(user.getId());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmailAddress(user.getEmailAddress());
        setPassword(user.getPassword());
        setTell(user.getTell());
        setAddress(user.getAddress());
        setCreateDate(user.getCreateDate());
        setModifiedDate(user.getModifiedDate());
        setRoleId(user.getRoleId());
        setStatus(user.getStatus());
    }

    public void editUserInfo(){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        User user =(User)session.getAttribute("user");

        if(!user.getPassword().equals(PWDEncryption.encrypt(getOldPassword()))){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("user_old_password_not_match"));
        } else if(!getPassword().equals(getConfirmPassword())){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("user_new_and_confirm_password_not_match"));
        } else if(!Validator.isEmailAddress(getEmailAddress())){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_enter_valid_email_address"));
        } else {
            UserService.editUser(user.getId(), getFirstName(), getLastName(), getEmailAddress(),
                    getPassword(),getTell(), getAddress());

            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("user_edited_successfully"));
        }
    }

}