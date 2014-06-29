package com.jprogrammers.service;

import com.jprogrammers.model.Role;
import com.jprogrammers.model.User;
import com.jprogrammers.util.PWDEncryption;
import com.jprogrammers.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */
public class UserService{

    static GenericDAOImpl<User> userDao;

    static {
        userDao = new GenericDAOImpl<User>();
    }

    public static User getUser(String emailAddress , String password) {
        String query = "from User where emailAddress = '"+ emailAddress + "' and password='" + password + "'";

        return userDao.findOne(query);

    }

    public static User addUser (String firstName ,String lastName , String emailAddress , String password, String tell ,String address ) {

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTell(tell);
        user.setAddress(address);
        user.setEmailAddress(emailAddress);
        user.setPassword(PWDEncryption.encrypt(password));
        user.setCreateDate(new Date());
        user.setId(CounterService.increment());
        user.setRoleId(Role.USER);
        user.setStatus(User.ACTIVE);

        userDao.save(user);

        return user;
    }

    public static User editUser(long id, String firstName ,String lastName , String emailAddress , String password, String tell ,String address){
        User user = getUser(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        if(!Validator.isNullOrEmpty(password))
            user.setPassword(PWDEncryption.encrypt(password));
        user.setTell(tell);
        user.setAddress(address);
        userDao.save(user);
        return user;
    }

    public static User login(String email, String password){

        String query = "FROM User WHERE emailAddress = '" + email + "' AND status = " + User.ACTIVE;

        User user = userDao.findOne(query);

        if(user != null && PWDEncryption.isEqual(password, user.getPassword())){
            return user;
        }

        return null;
    }

    public static List<User> getUsers(long roleId , int status) {

        String query = "FROM User where status = " + User.ACTIVE + " and roleId = " + roleId;

        return userDao.findMany(query);
    }

    public static void updateUser(User user) {

        userDao.save(user);
    }

    public static List<User> getUsers() {
        String query = "FROM User";

        return userDao.findMany(query);
    }

    public static void delete(User user) {
        userDao.delete(user);
    }

    public static User getUser(long id) {
        String query = "FROM User where id =" + id;

        return userDao.findOne(query);
    }

    public static void delete(long id) {
        userDao.delete(getUser(id));
    }

    public static User getUser(String emailAddress) {

        String query = "FROM User where emailAddress ='" + emailAddress + "'";

        return userDao.findOne(query);
    }
}
