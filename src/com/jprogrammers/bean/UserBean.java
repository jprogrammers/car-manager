package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.User;
import com.jprogrammers.service.CartexService;
import com.jprogrammers.service.UserService;
import com.jprogrammers.util.PWDEncryption;
import com.jprogrammers.util.Validator;
import com.sun.faces.lifecycle.Phase;
import org.apache.poi.util.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 25/04/14.
 */

@ManagedBean
@RequestScoped
public class UserBean extends User implements Serializable {

    private List<User> filteredUsers;
    private List<User> allUsers;

    private String tempPassword;

    private UploadedFile userLogo;

    User user;

    private SelectItem[] roleOptions;
    private SelectItem[] statusOptions;

    private int[] statuses = new int[]{0 , 1};
    private int[] roleIds;
    String[] roleNames;

    private String oldPassword;
    private String confirmPassword;

    public SelectItem[] getRoleOptions() {
        return roleOptions;
    }

    public UserBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User)session.getAttribute("user");
        if(user.getRoleId() == User.GOD){
            roleIds = new int[]{User.ADMINISTRATOR};
            roleNames = new String[]{"administrator"};
        } else {
            roleIds = new int[]{User.USER};
            roleNames = new String[]{"user"};
        }
        createRoleOptions();
        createStatusOptions();
        init();
    }


    public StreamedContent getUserLogo(long id) {

        User _user = UserService.getUser(id);

        FacesContext context = FacesContext.getCurrentInstance();

        if ( FacesContext.getCurrentInstance().getRenderResponse()) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            if (_user != null && _user.getLogo() != null) {

                return new DefaultStreamedContent(new ByteArrayInputStream(_user.getLogo()) , "image/jpg");
            }
        }

        return new DefaultStreamedContent();


    }

    public UploadedFile getUserLogo() {
        /*FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getRequestParameterMap().get("id");
        User user = UserService.getUser(id);
        return new DefaultStreamedContent(new ByteArrayInputStream(user.getLogo()));*/

        return this.userLogo;
    }

    public void setUserLogo(UploadedFile userLogo) {
        this.userLogo = userLogo;
    }

    private void init(){
        if(user.getRoleId() == User.GOD)
            setAllUsers(UserService.getUsers());
        else
            setAllUsers(UserService.getUsers(user.getUserId()));
    }

    private void createStatusOptions() {
        statusOptions = new SelectItem[3];

        statusOptions[0] = new SelectItem("" , LanguageUtil.get("everything"));
        statusOptions[1] = new SelectItem(1 , LanguageUtil.get("active"));
        statusOptions[2] = new SelectItem(0 , LanguageUtil.get("inactive"));

    }

    private void createRoleOptions() {
        roleOptions = new SelectItem[roleNames.length + 1];

        roleOptions[0] = new SelectItem("" , LanguageUtil.get("everything"));

        for ( int i = 0 ; i < roleNames.length ; i++ ) {
            roleOptions[i + 1] = new SelectItem(roleIds[i] , LanguageUtil.get(roleNames[i]));
        }
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
        return LanguageUtil.get(User.getRoleNameById(roleId));
    }

    public SelectItem[] getStatusOptions() {
        return statusOptions;
    }

    public String getStatusName(int status) {
        return (status == 1)? LanguageUtil.get("active") : LanguageUtil.get("inactive");
    }

    public void onEdit(RowEditEvent event) {
        User user = (User) event.getObject();

        if (tempPassword != null && !tempPassword.equals("")) {
            user.setPassword(PWDEncryption.encrypt(tempPassword));
            tempPassword = "";
        }
        UserService.editUser(user);

        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("user_edited_successfully"));
    }

    public int[] getStatuses() {
        return statuses;
    }

    public int[] getRoleIds() {
        return roleIds;
    }

    public void deleteUser(long id) {

        if (CartexService.getUserCartexes(id).size() == 0) {
            UserService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("user_deleted_successfully"));
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("you_cant_delete_this_user_because_he_has_cartex"));
        }
        init();
    }

    public void addUser() throws IOException {

        User user = UserService.getUserByEmail(getEmailAddress());
        if(user != null){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("user_exist_with_this_email_address"));
        } else {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            user = (User)session.getAttribute("user");
            User addedUser = UserService.addUser(getFirstName() , getLastName() , getEmailAddress() , getPassword() , getTell() , getAddress(), (user.getRoleId() == User.GOD) ? 0 : user.getUserId(), getRoleId());


            if ( session.getAttribute("UserLogo") != null ) {
              /*  InputStream is = userLogo.getInputstream();
                byte[] bytes = IOUtils.toByteArray(is);*/

                addedUser.setLogo((byte[]) session.getAttribute("UserLogo"));

                UserService.updateUser(addedUser);

            }
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("user_added_successfully"));
            emptyFields();
            session.removeAttribute("UserLogo");
        }
        init();

    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (event.getFile() != null) {
            session.setAttribute("UserLogo", IOUtils.toByteArray(event.getFile().getInputstream()));
        }

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
                    getPassword(), getTell(), getAddress(), user.getRoleId());


            setOldPassword("");
            setTempPassword("");
            setConfirmPassword("");

            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("user_edited_successfully"));
        }
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }
}