package com.hamitmizrak.database;

import java.sql.Connection;

public class DatabaseConnection extends DatabaseInformation{

    // Variable
    private String url=super.url;
    private String user=super.user;
    private String password=super.password;
    private String forNameData=super.forNameData;

    // Connection
    private Connection connection; // java.sql.Connection

    // Singleton desing pattern (Instance)
    // Singleton desing pattern (constructor)
    // Singleton desing pattern (GET)

    // PSVM
    public static void main(String[] args) {
        DatabaseConnection databaseConnection=new DatabaseConnection();
    }

} //end class
