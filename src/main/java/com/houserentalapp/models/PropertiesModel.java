package com.houserentalapp.models;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.List;

@Getter
@Document(collection = "properties")
public class PropertiesModel {
    public String _id;
    public String ownerId;
    public String ownerName;
    public String description;
    public String carouselImage1;
    public String carouselImage2;
    public String carouselImage3;
    public int rent;
    public int area;
    public int deposit;
    public int agreementDuration;
    public String availableFor;
    public String furnished;
    public String address;
    public String tenantId;
    public String tenantName;
    public String rentStartDate;
    public String status;
    public List<String> applicantsList;

    public PropertiesModel() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCarouselImage1() {
        return carouselImage1;
    }

    public void setCarouselImage1(String carousel1) {
        this.carouselImage1 = carousel1;
    }

    public String getCarouselImage2() {
        return carouselImage2;
    }

    public void setCarouselImage2(String carousel2) {
        this.carouselImage2 = carousel2;
    }

    public String getCarouselImage3() {
        return carouselImage3;
    }

    public void setCarouselImage3(String carousel3) {
        this.carouselImage3 = carousel3;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getAgreementDuration() {
        return agreementDuration;
    }

    public void setAgreementDuration(int agreementDuration) {
        this.agreementDuration = agreementDuration;
    }

    public String getAvailableFor() {
        return availableFor;
    }

    public void setAvailableFor(String availableFor) {
        this.availableFor = availableFor;
    }

    public String getFurnished() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getApplicantsList() {
        return applicantsList;
    }

    public void setApplicantsList(List<String> applicantsList) {
        this.applicantsList = applicantsList;
    }

    @Override
    public String toString() {
        return "PropertiesModel{" +
                "_id='" + _id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", description='" + description + '\'' +
                ", carousel1='" + carouselImage1 + '\'' +
                ", carousel2='" + carouselImage2 + '\'' +
                ", carousel3='" + carouselImage3 + '\'' +
                ", availableFor='" + availableFor + '\'' +
                ", furnished='" + furnished + '\'' +
                ", address='" + address + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", rentStartDate='" + rentStartDate + '\'' +
                ", Status='" + status + '\'' +
                '}';
    }
}