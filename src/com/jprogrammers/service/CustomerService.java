package com.jprogrammers.service;

import com.jprogrammers.model.Customer;

import java.util.Date;
import java.util.List;

/**
 * @author Ali Reza Akbarian, EN20
 */
public class CustomerService extends GenericDAOImpl<Customer> {

    static GenericDAOImpl<Customer> customerDao = new GenericDAOImpl<Customer>();

    public static List<Customer> getCustomers(){

        return customerDao.findMany("from Customer order by createDate desc");
    }

    public static List<Customer> getCustomers(long userId){

        return customerDao.findMany("from Customer where userId = " + userId + " order by createDate desc");
    }

    public static void updateCustomer(Customer customer) {
        customerDao.save(customer);
    }

    public static void deleteCustomer(long customerId) {
        Customer customer = getCustomer(customerId);
        if (customer != null)
            customerDao.delete(customer);
    }

    public static Customer getCustomer(long customerId) {
        return customerDao.findOne("from Customer where id = " + customerId);
    }

    public static Customer addCustomer(String firstName, String lastName, String nationalCode, String nationalId, String tell, String mobile, String workTell, String jobTitle, String homeAddress, String workAddress, String fatherName, String company, String province, String birthday, String zipCode , long userId) {

        Customer customer = new Customer();

        customer.setId(CounterService.increment());
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setNationalCode(nationalCode);
        customer.setNationalId(nationalId);
        customer.setTell(tell);
        customer.setMobile(mobile);
        customer.setWorkTell(workTell);
        customer.setJobTitle(jobTitle);
        customer.setHomeAddress(homeAddress);
        customer.setWorkAddress(workAddress);
        customer.setFatherName(fatherName);
        customer.setCompany(company);
        customer.setProvince(province);
        customer.setBirthday(birthday);
        customer.setZipCode(zipCode);
        customer.setCreateDate(new Date());
        customer.setUserId(userId);

        customerDao.save(customer);

        return customer;
    }


    public static Customer getCustomerByNationalCode(String nationalCode){
        return customerDao.findOne("from Customer where nationalCode = '" + nationalCode + "'");
    }

}
