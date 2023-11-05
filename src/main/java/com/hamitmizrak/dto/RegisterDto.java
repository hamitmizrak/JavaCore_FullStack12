package com.hamitmizrak.dto;

import java.io.Serializable;

// Class RegisterDto
public class RegisterDto extends BaseDto implements Serializable {
    //Serileştirme
    public static final Long serialVersionUID=1L;

    // Variable
    private String uNickName;
    private String uEmailAddress;
    private String uPassword;
    private String remaingNumber; // kullanıcının kalan hakkı
    private String rolles;
    private Boolean isPassive;

    // toString

    @Override
    public String toString() {
        return "RegisterDto{" +
                ", id=" + id +
                "uNickName='" + uNickName + '\'' +
                ", uEmailAddress='" + uEmailAddress + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", remaingNumber='" + remaingNumber + '\'' +
                ", rolles='" + rolles + '\'' +
                ", isPassive=" + isPassive +
                ", systemCreatedDate=" + systemCreatedDate +
                "} " + super.toString();
    }  //end toString()

    // GETTER AND SETTER
    public String getuNickName() {
        return uNickName;
    }

    public void setuNickName(String uNickName) {
        this.uNickName = uNickName;
    }

    public String getuEmailAddress() {
        return uEmailAddress;
    }

    public void setuEmailAddress(String uEmailAddress) {
        this.uEmailAddress = uEmailAddress;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getRemaingNumber() {
        return remaingNumber;
    }

    public void setRemaingNumber(String remaingNumber) {
        this.remaingNumber = remaingNumber;
    }

    public String getRolles() {
        return rolles;
    }

    public void setRolles(String rolles) {
        this.rolles = rolles;
    }

    public Boolean getPassive() {
        return isPassive;
    }

    public void setPassive(Boolean passive) {
        isPassive = passive;
    }
} //end class
