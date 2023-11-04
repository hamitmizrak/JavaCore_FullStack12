package com.hamitmizrak.roles;

public enum ERoles {
    SUPER_ADMIN(1,"super_admin"),ADMIN(2,"admin"),WRITER(3,"writer"),ACCOUNTING(4,"accouting"),USER(5,"user");
    // Key
    private final Integer key;

    // Value
    private final String value;

    // Constructor (Parametreli)
    private ERoles(Integer key,String value){
        this.key=key;
        this.value=value;
    }

    //GETTER
    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
} //end Enum
