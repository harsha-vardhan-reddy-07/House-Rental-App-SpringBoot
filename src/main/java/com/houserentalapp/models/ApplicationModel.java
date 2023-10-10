package com.houserentalapp.models;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "applications")
public class ApplicationModel {
    public String _id;
    public String propertyId;
    public String ownerId;
    public String ownerName;
    public String carouselImage1;
    public String carouselImage2;
    public String carouselImage3;
    public String description;
    public int rent;
    public int deposit;
    public int agreementDuration;
    public String address;
    public String applicantId;
    public String applicantName;
    public String applicantEmail;
    public String status;

    public ApplicationModel() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApplicationModel{" +
                "_id='" + _id + '\'' +
                ", propertyId='" + propertyId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", carousel1='" + carouselImage1 + '\'' +
                ", carousel2='" + carouselImage2 + '\'' +
                ", carousel3='" + carouselImage3 + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", applicantId='" + applicantId + '\'' +
                ", applicantName='" + applicantName + '\'' +
                ", applicantEmail='" + applicantEmail + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
