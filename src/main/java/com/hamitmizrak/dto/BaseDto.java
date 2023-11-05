package com.hamitmizrak.dto;

import java.io.Serializable;
import java.util.Date;

// abstract BaseDto
// NOT: interface abstract interface IDao yazarsanız bütün metotları çağırmak zorunda kalmazsınız.
abstract public class BaseDto  implements Serializable {
    //Serileştirme
    public static final Long serialVersionUID=1L;

    // Variable
    protected Long id;
    protected Date systemCreatedDate;

    // Constructor (Parametresiz)
    public BaseDto() {
        System.out.println("Hash Code: "+BaseDto.class.hashCode());
    }

    // GETTER AND SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSystemCreatedDate() {
        return systemCreatedDate;
    }

    public void setSystemCreatedDate(Date systemCreatedDate) {
        this.systemCreatedDate = systemCreatedDate;
    }
} //end class
