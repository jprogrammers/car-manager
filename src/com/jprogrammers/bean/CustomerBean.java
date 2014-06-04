package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.Customer;
import com.jprogrammers.service.CustomerService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.print.attribute.standard.Severity;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author Ali Reza Akbarian
 *         created on 09/05/14.
 */
@ManagedBean
@ViewScoped
public class CustomerBean extends Customer implements Serializable {

    List<Customer> filteredCustomers;

    public CustomerBean() {
        this.filteredCustomers = CustomerService.getCustomers();
    }

    public List<Customer> getAllCustomers() {
        return this.filteredCustomers;
    }

    public void setFilteredCustomers(List<Customer> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void onEdit(RowEditEvent event) {
        Customer customer = (Customer) event.getObject();

        CustomerService.updateCustomer(customer);

        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteCustomer(long id) {
        CustomerService.deleteCustomer(id);
        this.filteredCustomers = CustomerService.getCustomers();
    }

    public void addCustomer() {
        CustomerService.addCustomer(getFirstName() , getLastName() , getNationalCode() , getNationalId() , getTell() , getMobile() ,
                getWorkTell() , getJobTitle() , getHomeAddress() , getWorkAddress() , getFatherName() , getCompany() , getProvince() ,
                getBirthday() , getZipCode());

        addMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("customer_added_successfully"));

        this.filteredCustomers = CustomerService.getCustomers();
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
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

}
