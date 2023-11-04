package com.hamitmizrak.roles;

// final verirsem;
// 1-) Değişkende: sabit değer
// 2-) Metotda: Override edilemez
// 3-) Classta: Kalıtımlanamaz, edilemez

// 1-) Değişkende final verirsek;
// 1-a_) Değeri sabit yapar.
// 2-a_) Sadece Get metodunu eklemeye yarar
// 3-a_) Bizi Constructur yazmamıza zorlar
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
