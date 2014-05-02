package com.jprogrammers.bean.users;

import com.jprogrammers.model.Role;
import com.jprogrammers.model.User;
import com.jprogrammers.service.UserService;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 25/04/14.
 */
public class TableBean implements Serializable {

    private List<User> filteredUsers;

    private List<User> allUsers;

    public TableBean() {
        allUsers = UserService.getUsers(Role.USER , User.ACTIVE);
    }

    public List<User> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }
}