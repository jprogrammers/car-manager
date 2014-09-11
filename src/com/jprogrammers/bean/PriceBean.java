package com.jprogrammers.bean;

import javax.faces.bean.ManagedBean;

/**
 * Created by EN20 on 5/22/14.
 */
@ManagedBean
public class PriceBean {
    double fobPrice;
    double numberOfCar = 1;
    double shipmentPrice;
    double stuffPriceInDollar;
    double dollarPrice;
    double stuffPriceInRial;

    double insurance;
    double insurancePercent = 0.5;

    double customs;

    double entrance;
    double entrancePercent;

    double helalAhmar;
    double helalAhmarPercent = 0.5;

    double tax1;
    double tax2;
    double tax;
    double taxPercent1;
    double taxPercent2;

    double importDuties;
    double importDutiesPercent = 5.0;

    double customsPay;
    double entranceDuties = 2500000;

    double standard;

    double note;
    double assetSide;
    double taxOther;
    double markingInsurance;
    double municipal;
    double plaque;
    double vehicleCarrier;
    double licence;
    double other;

    double totalPrice;

    public void calculatePrice(){

        stuffPriceInDollar = (fobPrice * numberOfCar) + shipmentPrice;
        stuffPriceInRial = stuffPriceInDollar * dollarPrice;

        insurance = stuffPriceInRial * (insurancePercent / 100.0);

        customs = stuffPriceInRial + insurance;

        entrance = customs * (entrancePercent / 100.0);

        helalAhmar = entrance * (helalAhmarPercent / 100.0);

        tax1 = (entrance + customs) * (taxPercent1 / 100.0);
        tax2 = (entrance + customs) * (taxPercent2 / 100.0);
        tax = tax1 + tax2;

        importDuties = fobPrice * numberOfCar * dollarPrice * (importDutiesPercent / 100.0);

        customsPay = entrance + helalAhmar + tax + importDuties + entranceDuties;

        totalPrice = customsPay + note + assetSide + taxOther + markingInsurance +
                municipal + plaque + vehicleCarrier + licence + other;
    }

    public double getFobPrice() {
        return fobPrice;
    }

    public void setFobPrice(double fobPrice) {
        this.fobPrice = fobPrice;
    }

    public double getNumberOfCar() {
        return numberOfCar;
    }

    public void setNumberOfCar(double numberOfCar) {
        this.numberOfCar = numberOfCar;
    }

    public double getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(double shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public double getStuffPriceInDollar() {
        return stuffPriceInDollar;
    }

    public void setStuffPriceInDollar(double stuffPriceInDollar) {
        this.stuffPriceInDollar = stuffPriceInDollar;
    }

    public double getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(double dollarPrice) {
        this.dollarPrice = dollarPrice;
    }

    public double getStuffPriceInRial() {
        return stuffPriceInRial;
    }

    public void setStuffPriceInRial(double stuffPriceInRial) {
        this.stuffPriceInRial = stuffPriceInRial;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public double getInsurancePercent() {
        return insurancePercent;
    }

    public void setInsurancePercent(double insurancePercent) {
        this.insurancePercent = insurancePercent;
    }

    public double getCustoms() {
        return customs;
    }

    public void setCustoms(double customs) {
        this.customs = customs;
    }

    public double getEntrance() {
        return entrance;
    }

    public void setEntrance(double entrance) {
        this.entrance = entrance;
    }

    public double getEntrancePercent() {
        return entrancePercent;
    }

    public void setEntrancePercent(double entrancePercent) {
        this.entrancePercent = entrancePercent;
    }

    public double getHelalAhmar() {
        return helalAhmar;
    }

    public void setHelalAhmar(double helalAhmar) {
        this.helalAhmar = helalAhmar;
    }

    public double getHelalAhmarPercent() {
        return helalAhmarPercent;
    }

    public void setHelalAhmarPercent(double helalAhmarPercent) {
        this.helalAhmarPercent = helalAhmarPercent;
    }

    public double getTax1() {
        return tax1;
    }

    public void setTax1(double tax1) {
        this.tax1 = tax1;
    }

    public double getTax2() {
        return tax2;
    }

    public void setTax2(double tax2) {
        this.tax2 = tax2;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTaxPercent1() {
        return taxPercent1;
    }

    public void setTaxPercent1(double taxPercent1) {
        this.taxPercent1 = taxPercent1;
    }

    public double getTaxPercent2() {
        return taxPercent2;
    }

    public void setTaxPercent2(double taxPercent2) {
        this.taxPercent2 = taxPercent2;
    }

    public double getImportDuties() {
        return importDuties;
    }

    public void setImportDuties(double importDuties) {
        this.importDuties = importDuties;
    }

    public double getImportDutiesPercent() {
        return importDutiesPercent;
    }

    public void setImportDutiesPercent(double importDutiesPercent) {
        this.importDutiesPercent = importDutiesPercent;
    }

    public double getCustomsPay() {
        return customsPay;
    }

    public void setCustomsPay(double customsPay) {
        this.customsPay = customsPay;
    }

    public double getStandard() {
        return standard;
    }

    public void setStandard(double standard) {
        this.standard = standard;
    }

    public double getEntranceDuties() {
        return entranceDuties;
    }

    public void setEntranceDuties(double entranceDuties) {
        this.entranceDuties = entranceDuties;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public double getAssetSide() {
        return assetSide;
    }

    public void setAssetSide(double assetSide) {
        this.assetSide = assetSide;
    }

    public double getTaxOther() {
        return taxOther;
    }

    public void setTaxOther(double taxOther) {
        this.taxOther = taxOther;
    }

    public double getMarkingInsurance() {
        return markingInsurance;
    }

    public void setMarkingInsurance(double markingInsurance) {
        this.markingInsurance = markingInsurance;
    }

    public double getMunicipal() {
        return municipal;
    }

    public void setMunicipal(double municipal) {
        this.municipal = municipal;
    }

    public double getPlaque() {
        return plaque;
    }

    public void setPlaque(double plaque) {
        this.plaque = plaque;
    }

    public double getVehicleCarrier() {
        return vehicleCarrier;
    }

    public void setVehicleCarrier(double vehicleCarrier) {
        this.vehicleCarrier = vehicleCarrier;
    }

    public double getLicence() {
        return licence;
    }

    public void setLicence(double licence) {
        this.licence = licence;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
