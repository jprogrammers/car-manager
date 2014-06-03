package com.jprogrammers.bean;

import javax.faces.bean.ManagedBean;

/**
 * Created by EN20 on 5/22/14.
 */
@ManagedBean
public class PriceBean {
    double stuffDollarPrice;
    double dollarPrice;
    double stuffPrice;
    double insurance;
    double insurancePercent = 0.005;
    double shipmentPrice;
    double stuffSafe;
    double customsLaw;
    double customsLawPercent;
    double declaration;
    double declarationPercent = 0.005;
    double tax1;
    double tax2;
    double tax;
    double taxPercent1;
    double taxPercent2;
    double customsToll;
    double marking;
    double markingPercent = 0.1;
    double helalAhmar;
    double helalAhmarPercent = 0.05;
    double standard;
    double standardPercent = 0.008;

    double inspection;
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
        stuffPrice = stuffDollarPrice * dollarPrice;
        insurance = stuffPrice * insurancePercent;
        stuffSafe = stuffPrice + insurance + shipmentPrice;
        customsLaw = stuffSafe * (customsLawPercent / 100.0);
        declaration = customsLaw * declarationPercent;
        tax1 = (stuffSafe + customsLaw) * (taxPercent1 / 100.0);
        tax2 = (stuffSafe + customsLaw) * (taxPercent2 / 100.0);
        tax = tax1 + tax2;
        customsToll = customsLaw + declaration + tax;
        marking = stuffSafe * markingPercent;
        helalAhmar = stuffPrice * helalAhmarPercent;
        standard = stuffPrice * standardPercent;

        totalPrice = customsToll + marking + helalAhmar + standard +
                inspection + note + assetSide + taxOther + markingInsurance +
                municipal + plaque + vehicleCarrier + licence + other;
    }

    public double getInspection() {
        return inspection;
    }

    public void setInspection(double inspection) {
        this.inspection = inspection;
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

    public double getStuffDollarPrice() {
        return stuffDollarPrice;
    }

    public void setStuffDollarPrice(double stuffDollarPrice) {
        this.stuffDollarPrice = stuffDollarPrice;
    }

    public double getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(double dollarPrice) {
        this.dollarPrice = dollarPrice;
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
