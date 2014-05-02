package com.jprogrammers.service;

import com.jprogrammers.model.User;
import com.jprogrammers.util.PWDEncryption;

import java.util.Date;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */
public class UserService extends GenericDAOImpl<User> {

    static GenericDAOImpl<User> userDao;

    static {
        userDao = new GenericDAOImpl<User>();
    }

    public static User getUser(String emailAddress , String password) {
        String query = "from User where emailAddress = '"+ emailAddress + "' and password='" + password + "'";

        return userDao.findOne(query , null);

    }

    public static User addUser (String firstName ,String lastName , String emailAddress , String password) {

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        user.setPassword(password);
        user.setCreateDate(new Date());
        user.setId(CounterService.increment());
        user.setStatus(User.ACTIVE);

        userDao.save(user , null);

        return user;
    }

    public static User login(String email, String password){

        String query = "FROM User WHERE emailAddress = '" + email + "' AND status = " + User.ACTIVE;

        User user = userDao.findOne(query, null);

        if(user != null && PWDEncryption.isEqual(password, user.getPassword())){
            return user;
        }

        return null;
    }

    public static List<User> getUsers(long roleId , int status) {

        String query = "FROM User where status = " + User.ACTIVE + " and roleId = " + roleId;

        return userDao.findMany(query , null);
    }
}
