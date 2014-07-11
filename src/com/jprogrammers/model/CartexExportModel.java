package com.jprogrammers.model;

import java.io.InputStream;

/**
 * Created by EN20 on 7/11/14.
 */
public class CartexExportModel {

    String companyName;
    String registrationNumber;
    String companyInformation;
    InputStream cartexImage;

    public CartexExportModel(String companyName, String registerNumber, String companyInformation, InputStream cartexImage){
        setCompanyName(companyName);
        setRegistrationNumber(registerNumber);
        setCompanyInformation(companyInformation);
        setCartexImage(cartexImage);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCompanyInformation() {
        return companyInformation;
    }

    public void setCompanyInformation(String companyInformation) {
        this.companyInformation = companyInformation;
    }

    public InputStream getCartexImage() {
        return cartexImage;
    }

    public void setCartexImage(InputStream cartexImage) {
        this.cartexImage = cartexImage;
    }
}
