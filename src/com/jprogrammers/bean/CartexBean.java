package com.jprogrammers.bean;

import com.jprogrammers.language.LanguageUtil;
import com.jprogrammers.model.*;
import com.jprogrammers.service.*;
import com.jprogrammers.util.Validator;
import com.sahandrc.calendar.PersianCalendar;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EN20 on 5/15/14.
 */
@ManagedBean
public class CartexBean extends Cartex {

    List<Customer> customers;
    List<Licence> licences;
    List<Cartex> cartexes;
    List<Cartex> filteredCartexes;
    StreamedContent cartexPdfFile;
    User user;

    PersianCalendar persianCalendar = new PersianCalendar();

    public CartexBean(){

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User)session.getAttribute("user");

        customers = CustomerService.getCustomers();

        licences = LicenceService.getLicences();

        init();
    }

    private void init(){
        if(user.getRoleId() == User.GOD)
            setCartexes(CartexService.getCartexes());
        else
            setCartexes(CartexService.getUserCartexes(user.getUserId()));
    }

    public void addCartex(){

        Cartex cartex = CartexService.getCartexByBodyNumber(getBodyNumber());

        if(cartex != null) {
            FacesContext.getCurrentInstance().addMessage(null ,new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("there_is_cartex_with_this_body_number"),""));

        }else if(Validator.isNullOrEmpty(getColor()) || Validator.isNullOrEmpty(getEngineNumber()) || Validator.isNullOrEmpty(getBodyNumber()) ||
                Validator.isNullOrEmpty(getModel()) || getBodyNumber().length() != 17){
            FacesContext.getCurrentInstance().addMessage(null ,new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("please_insert_valid_parameter"),""));
        } else {
            CartexService.addCartex(user.getUserId(), getCustomerId(), getLicenceId(), getColor(), getEngineNumber(), getBodyNumber(), getVINNumber(),
                    getModel(), getBoughtDate(), getPlateNumber());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_added_successfully"), ""));
            emptyFields();
            init();
        }

    }

    private void emptyFields(){
        setUserId(0);
        setCustomerId(0);
        setLicenceId(0);
        setColor("");
        setEngineNumber("");
        setBodyNumber("");
        setVINNumber("");
        setModel("");
        setBoughtDate("");
        setPlateNumber("");
    }

    public void editCartex(RowEditEvent event){
        Cartex cartex = (Cartex)event.getObject();
        CartexService.editCartex(cartex);

        CartexEditRequest cartexRequest = CartexEditRequestService.getEditRequestByCartexAndUser(cartex.getId(), user.getId());

        if (cartexRequest != null) {
            CartexEditRequestService.deleteCartexEditRequest(cartexRequest);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_edited_successfully"), ""));
    }

    public void deleteCartex(long id){
        CartexService.deleteCartex(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("cartex_deleted_successfully"), ""));
        init();
    }

    public void exportCartex(long cartexId) throws JRException {

        Cartex cartex = CartexService.getCartex(cartexId);
        User user = UserService.getUser(cartex.getUserId());
        Customer customer = CustomerService.getCustomer(cartex.getCustomerId());
        CartexDesign cartexDesign = CartexDesignService.getCartexDesignByUserId(cartex.getUserId());
        if(cartexDesign == null){
            cartexDesign = new CartexDesign(0, 0, "", "", "", "", user.getFirstName() + " " + user.getLastName(), "", "", "", null);
        }
        Licence licence = LicenceService.getLicence(cartex.getLicenceId());
        CarType carType = CarTypeService.getCarType(licence.getCarTypeId());

        Map<String, Object> parameters = new HashMap<String, Object>();
        List<CartexExportModel> cartexExportModels = new ArrayList<CartexExportModel>();

        cartexExportModels.add(new CartexExportModel(user.getFirstName() + " " + user.getLastName(),
                "", cartexDesign.getInformation(), cartexDesign.getCity(),
                cartexDesign.getImage() != null ? new ByteArrayInputStream(cartexDesign.getImage()) : null,
                cartex.getBoughtDate(), getDocumentNumber(cartex), cartexDesign.getFileNumber(), licence.getLicenceCode(), cartexDesign.getEconomicCode(), cartexDesign.getName1(), cartexDesign.getTitle1(),
                cartexDesign.getName2(), cartexDesign.getTitle2(), carType.getUsecaseType(), carType.getSystem(),
                carType.getTip(), cartex.getModel(), cartex.getColor(), String.valueOf(carType.getCapacity()), String.valueOf(carType.getDefCount()),
                carType.getFuelType(), String.valueOf(carType.getCylinderCount()), String.valueOf(carType.getWheelsCount()),
                String.valueOf(carType.getCylinderSize()), carType.getCountry(), cartex.getBodyNumber(), cartex.getEngineNumber(),
                cartex.getVINNumber(), customer.getFirstName() + " " + customer.getLastName(), customer.getBirthday(), customer.getBirthday(),
                customer.getHomeAddress(), customer.getWorkAddress(), customer.getFatherName(), customer.getProvince(),
                customer.getNationalCode(), customer.getNationalId(), customer.getZipCode(), customer.getProvince(), customer.getTell(), customer.getMobile()));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cartexExportModels);
        String fileAddress = this.getClass().getResource(cartexDesign.getImage() != null ? "../../../../carManager.jrxml" : "../../../../carManagerFull.jrxml").getPath();
        JasperReport jasperReport = JasperCompileManager.compileReport(fileAddress);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource); // you can use jasperReport instead of source string
        byte[] pdfFile = JasperExportManager.exportReportToPdf(print);
        cartexPdfFile = new DefaultStreamedContent(new ByteArrayInputStream(pdfFile), "", getDocumentNumber(cartex) + ".pdf");

    }

    private String getDocumentNumber(Cartex cartex){
        persianCalendar.setTimeInMillis(cartex.getCreateDate());
        return persianCalendar.getPersianShortDate().replaceAll("/", "").substring(2) + cartex.getId() % 1000;
    }

    public StreamedContent getExportCartexFile(){
        return cartexPdfFile;
    }

    public void requestForEdit(long cartexId , long userId){
        CartexEditRequestService.updateCartextEditRequest(CounterService.increment() , userId , cartexId , CartexEditRequest.STATUS_PENDING);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LanguageUtil.get("edit_request_successfully_sent_wait_for_response"), ""));
    }

    public boolean hasPendingRequest(long cartexId , long userId){

        User user = UserService.getUser(userId);

        CartexEditRequest editRequest = CartexEditRequestService.getEditRequestByCartexAndUser(cartexId, userId);

        return  (editRequest != null && editRequest.getStatus() == CartexEditRequest.STATUS_PENDING && user.getRoleId() != User.ADMINISTRATOR);

    }
     public boolean hasEditPermission(long cartexId , long userId) {

         CartexEditRequest editRequest = CartexEditRequestService.getEditRequestByCartexAndUser(cartexId, userId);
         User user = UserService.getUser(userId);

         return ((editRequest != null && editRequest.getStatus() == CartexEditRequest.STATUS_ALLOWED) || user.getRoleId() == User.GOD || user.getRoleId() == User.ADMINISTRATOR);

    }

    public boolean canRequest(long cartexId , long userId) {
        CartexEditRequest editRequest = CartexEditRequestService.getEditRequestByCartexAndUser(cartexId, userId);
        User user = UserService.getUser(userId);

        return (editRequest == null  && user.getRoleId() == User.USER);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Licence> getLicences() {
        return licences;
    }

    public void setLicences(List<Licence> licences) {
        this.licences = licences;
    }

    public List<Cartex> getCartexes() {
        return cartexes;
    }

    public void setCartexes(List<Cartex> cartexes) {
        this.cartexes = cartexes;
    }

    public List<Cartex> getFilteredCartexes() {
        return filteredCartexes;
    }

    public void setFilteredCartexes(List<Cartex> filteredCartexes) {
        this.filteredCartexes = filteredCartexes;
    }
}
