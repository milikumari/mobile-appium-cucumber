package com.acceptance.test.testDataModel;

public class User {


    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getWhatsAppNumberToRegister() {
        return whatsAppNumberToRegister;
    }

    public void setWhatsAppNumberToRegister(String whatsAppNumberToRegister) {
        this.whatsAppNumberToRegister = whatsAppNumberToRegister;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNewContactPersonNumber() {
        return newContactPersonNumber;
    }

    public void setNewContactPersonNumber(String newContactPersonNumber) {
        this.newContactPersonNumber = newContactPersonNumber;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    String profileName;
    String whatsAppNumberToRegister;
    String countryCode;
    String newContactPersonNumber;
    String attachmentType;

    public String getNewContactPersonName() {
        return newContactPersonName;
    }

    public void setNewContactPersonName(String newContactPersonName) {
        this.newContactPersonName = newContactPersonName;
    }

    String newContactPersonName;
}
