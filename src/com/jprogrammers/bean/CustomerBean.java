package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.Customer;
import com.jprogrammers.model.User;
import com.jprogrammers.service.CartexService;
import com.jprogrammers.service.CustomerService;
import com.jprogrammers.util.Validator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.jws.soap.SOAPBinding;
import javax.print.attribute.standard.Severity;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 09/05/14.
 */
@ManagedBean
@ViewScoped
public class CustomerBean extends Customer implements Serializable {

    List<Customer> customers;

    List<Customer> filteredCustomers;

    User user;

    public CustomerBean() {
        init();
    }

    public void init(){

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User)session.getAttribute("user");

        if(user != null) {
            if (user.getRoleId() == User.GOD)
                setCustomers(CustomerService.getCustomers());
            else
                setCustomers(CustomerService.getCustomers(user.getUserId()));
        }

    }

    public void onEdit(RowEditEvent event) {

        Customer customer = (Customer) event.getObject();
        CustomerService.updateCustomer(customer);

        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("customer_edited_successfully"));

    }

    public void deleteCustomer(long id) {

        CustomerService.deleteCustomer(id);
        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("customer_deleted_successfully"));
        init();

    }

    public boolean addCustomer() {

        Customer customer = CustomerService.getCustomerByNationalCode(getNationalCode());

        if(customer != null){
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("there_is_customer_with_this_national_code"));
            return false;
        }else if(isCustomerFieldsValid() ){

            CustomerService.addCustomer(getFirstName() , getLastName() , getNationalCode() , getNationalId() , getTell() , getMobile() ,
                    getWorkTell() , getJobTitle() , getHomeAddress() , getWorkAddress() , getFatherName() , getCompany() , getProvince() ,
                    getBirthday() , getZipCode(), user.getUserId());
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("customer_added_successfully"));
            emptyFields();
            init();

            return true;

        }else{
            addMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_insert_valid_parameter"));

            return false;
        }

    }

    protected boolean isCustomerFieldsValid() {

        if (Validator.isNullOrEmpty(getFirstName())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_first_name"));
            return false;
        }
        if (Validator.isNullOrEmpty(getLastName())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_last_name"));
            return false;
        }
        if (!Validator.isNationalCode(getNationalCode())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_national_code"));
            return false;
        }
        if (!Validator.isNumber(getNationalId())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_national_id"));
            return false;
        }
        if (!Validator.isNumber(getTell())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_work_tel"));
            return false;
        }
        if ( !Validator.isNumber(getMobile())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_mobile"));
            return false;
        }
        if (!Validator.isNumber(getWorkTell())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_work_tel"));
            return false;
        }
        if (Validator.isNullOrEmpty(getHomeAddress())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_home_address"));
            return false;
        }
        if (Validator.isNullOrEmpty(getFatherName())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_father_name"));
            return false;
        }
        if (Validator.isNullOrEmpty(getProvince())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_province"));
            return false;
        }
        if (!Validator.isNumber(getZipCode())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_zip_code"));
            return false;
        }
        if (Validator.isNullOrEmpty(getBirthday())) {
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("please_fill_a_valid_value_for_birthday"));
            return false;
        }

        return true;
    }

    public void emptyFields() {

        setFirstName("");
        setLastName("");
        setCreateDate(null);
        setModifiedDate(null);
        setNationalCode("");
        setNationalId("");
        setTell("");
        setMobile("");
        setWorkTell("");
        setJobTitle("");
        setHomeAddress("");
        setWorkAddress("");
        setFatherName("");
        setCompany("");
        setProvince("");
        setBirthday("");
        setZipCode("");
    }

    protected void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, ""));
    }

    public void doExport() {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Customers");

        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        createRowCell(row , 1 , LanguageUtil.get("first_name"));
        createRowCell(row , 2 , LanguageUtil.get("last_name"));
        createRowCell(row , 3 , LanguageUtil.get("father_name"));
        createRowCell(row , 4 , LanguageUtil.get("birthday"));
        createRowCell(row , 5 , LanguageUtil.get("national_code"));
        createRowCell(row , 6 , LanguageUtil.get("national_id"));
        createRowCell(row , 7 , LanguageUtil.get("tell"));
        createRowCell(row , 8 , LanguageUtil.get("mobile"));
        createRowCell(row , 9 , LanguageUtil.get("work_tell"));
        createRowCell(row , 10 , LanguageUtil.get("job_title"));
        createRowCell(row , 11 , LanguageUtil.get("home_address"));
        createRowCell(row , 12 , LanguageUtil.get("work_address"));
        createRowCell(row , 13 , LanguageUtil.get("company"));
        createRowCell(row , 14 , LanguageUtil.get("province"));
        createRowCell(row , 15 , LanguageUtil.get("zipcode"));

        for (Customer customer : CustomerService.getCustomers()) {

            row = sheet.createRow(++rowNum);

            int cellNum = 0;
            createRowCell(row , cellNum , String.valueOf(rowNum));
            createRowCell(row , ++cellNum , customer.getFirstName());
            createRowCell(row , ++cellNum , customer.getLastName());
            createRowCell(row , ++cellNum , customer.getFatherName());
            createRowCell(row , ++cellNum , customer.getBirthday());
            createRowCell(row , ++cellNum , customer.getNationalCode());
            createRowCell(row , ++cellNum , customer.getNationalId());
            createRowCell(row , ++cellNum , customer.getTell());
            createRowCell(row , ++cellNum , customer.getMobile());
            createRowCell(row , ++cellNum , customer.getWorkTell());
            createRowCell(row , ++cellNum , customer.getJobTitle());
            createRowCell(row , ++cellNum , customer.getHomeAddress());
            createRowCell(row , ++cellNum , customer.getWorkAddress());
            createRowCell(row , ++cellNum , customer.getCompany());
            createRowCell(row , ++cellNum , customer.getProvince());
            createRowCell(row , ++cellNum , customer.getZipCode());

        }
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance(); //Get the context ONCE
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            ServletOutputStream servletOutputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment; filename=customers");
            facesContext.responseComplete();

            workbook.write(servletOutputStream);

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("your_request_was_failed"));
        }

    }

    private void createRowCell(Row row ,int cellNum , String value) {
        Cell cell = row.createCell(cellNum);
        cell.setCellValue(value);
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setFilteredCustomers(List<Customer> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

}
