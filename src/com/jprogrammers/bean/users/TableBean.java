package com.jprogrammers.bean.users;

import com.jprogrammers.language.LanguageFa;
import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.Role;
import com.jprogrammers.model.User;
import com.jprogrammers.service.UserService;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 25/04/14.
 */

@ManagedBean(name = "userTableBean")
public class TableBean extends User implements Serializable {

    private List<User> filteredUsers;
    private List<User> allUsers;

    private SelectItem[] roleOptions;
    private SelectItem[] statusOptions;

    private int[] statuses = new int[]{0 , 1};
    private int[] roleIds = Role.roleIds;

    public SelectItem[] getRoleOptions() {
        return roleOptions;
    }

    public TableBean() {
        allUsers = UserService.getUsers();
        roleOptions = createRoleOptions();
        statusOptions = createStatusOptions();
    }

    private SelectItem[] createStatusOptions() {
        statusOptions = new SelectItem[3];

        statusOptions[0] = new SelectItem("" , LanguageUtil.get("everything"));
        statusOptions[1] = new SelectItem(1 , LanguageUtil.get("active"));
        statusOptions[2] = new SelectItem(0 , LanguageUtil.get("inactive"));

        return statusOptions;
    }

    private SelectItem[] createRoleOptions() {
        String[] roleNames = Role.roleNames;
        roleOptions = new SelectItem[roleNames.length + 1];

        roleOptions[0] = new SelectItem("" , LanguageUtil.get("everything"));

        for ( int i = 0 ; i < roleNames.length ; i++ ) {
            roleOptions[i + 1] = new SelectItem(Role.roleIds[i] , LanguageUtil.get(Role.roleNames[i]));
        }

        return roleOptions;
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


        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public int[] getStatuses() {
        return statuses;
    }

    public int[] getRoleIds() {
        return roleIds;
    }

    public void deleteUser(long id) {
        UserService.delete(id);
    }

    public void addUser() throws IOException {
        UserService.addUser(getFirstName() , getLastName() , getEmailAddress() , getPassword());

        ///FacesContext.getCurrentInstance().addMessage("app-message" ,  new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("your_request_was_completed"), ""));
        addMessage(FacesMessage.SEVERITY_INFO , LanguageUtil.get("your_request_was_completed"));

    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, ""));
    }

}