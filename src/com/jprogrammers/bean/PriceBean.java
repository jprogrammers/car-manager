package com.jprogrammers.bean;

import javax.faces.bean.ManagedBean;

/**
 * Created by EN20 on 5/22/14.
 */
@ManagedBean
public class PriceBean {
    double stuffPrice1;
    double stuffPrice2;
    double stuffPrice;
    double insurance;
    double insurancePercent = 0.005;
    double shipmentPrice;
    double stuffSafe;
    double customsLaw;
    double customsLawPercent;
    double declaration;
    double declarationPercent = 0.005;
    double tax;
    double taxPercent;
    double customsToll;
    double marking;
    double markingPercent = 0.1;
    double helalAhmar;
    double helalAhmarPercent = 0.05;
    double standard;
    double standardPercent = 0.008;
    double totalPrice;

    public void calculatePrice(){
        stuffPrice = stuffPrice1 * stuffPrice2;
        insurance = stuffPrice * insurancePercent;
        stuffSafe = stuffPrice + insurance + shipmentPrice;
        customsLaw = stuffSafe * customsLawPercent;
        declaration = customsLaw * declarationPercent;
        tax = (customsLaw + declaration) * taxPercent;
        customsToll = customsLaw + declaration + tax;
        marking = customsLaw * markingPercent;
        helalAhmar = stuffPrice * helalAhmarPercent;
        standard = stuffPrice * standardPercent;

        totalPrice = customsToll + marking + helalAhmar + standard;
    }

    public double getStuffPrice1() {
        return stuffPrice1;
    }

    public void setStuffPrice1(double stuffPrice1) {
        this.stuffPrice1 = stuffPrice1;
    }

    public double getStuffPrice2() {
        return stuffPrice2;
    }

    public void setStuffPrice2(double stuffPrice2) {
        this.stuffPrice2 = stuffPrice2;
    }

    public double getStuffPrice() {
        return stuffPrice;
    }

    public void setStuffPrice(double stuffPrice) {
        this.stuffPrice = stuffPrice;
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

    public double getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(double shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public double getStuffSafe() {
        return stuffSafe;
    }

    public void setStuffSafe(double stuffSafe) {
        this.stuffSafe = stuffSafe;
    }

    public double getCustomsLaw() {
        return customsLaw;
    }

    public void setCustomsLaw(double customsLaw) {
        this.customsLaw = customsLaw;
    }

    public double getCustomsLawPercent() {
        return customsLawPercent;
    }

    public void setCustomsLawPercent(double customsLawPercent) {
        this.customsLawPercent = customsLawPercent;
    }

    public double getDeclaration() {
        return declaration;
    }

    public void setDeclaration(double declaration) {
        this.declaration = declaration;
    }

    public double getDeclarationPercent() {
        return declarationPercent;
    }

    public void setDeclarationPercent(double declarationPercent) {
        this.declarationPercent = declarationPercent;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public double getCustomsToll() {
        return customsToll;
    }

    public void setCustomsToll(double customsToll) {
        this.customsToll = customsToll;
    }

    public double getMarking() {
        return marking;
    }

    public void setMarking(double marking) {
        this.marking = marking;
    }

    public double getMarkingPercent() {
        return markingPercent;
    }

    public void setMarkingPercent(double markingPercent) {
        this.markingPercent = markingPercent;
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

    public double getStandard() {
        return standard;
    }

    public void setStandard(double standard) {
        this.standard = standard;
    }

    public double getStandardPercent() {
        return standardPercent;
    }

    public void setStandardPercent(double standardPercent) {
        this.standardPercent = standardPercent;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
