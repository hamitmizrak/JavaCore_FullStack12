package com.hamitmizrak.database;

import java.io.Serializable;

abstract  public class DatabaseInformation implements Serializable {
    // Const
    public final static Long serialVersionUID=1L;

    // Variable
    protected String url;
    protected String user;
    protected String password;
    protected String forNameData;

    // Constructor (Parametresiz)
    public  DatabaseInformation(){
        // Default MYSQL RDBMS
        this.url="jdbc:mysql://localhost:3306/university".concat(DatabaseInfoUrl.MYSQL_SPECIAL_URL);
        this.user="root";
        this.password="root";
        this.forNameData="com.mysql.cj.jdbc.Driver";
        // H2DB
    }

    // Constructor (Parametreli)
    // Postgresql
    public DatabaseInformation(String url, String user, String password, String forNameData) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.forNameData = forNameData;
    }

    //toString
    @Override
    public String toString() {
        return "DatabaseInformation{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", forNameData='" + forNameData + '\'' +
                '}';
    }

    //GETTER AND SETTER
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForNameData() {
        return forNameData;
    }

    public void setForNameData(String forNameData) {
        this.forNameData = forNameData;
    }
} //end class
