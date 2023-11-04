package com.hamitmizrak.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection extends DatabaseInformation {

    // Variable
    private String url = super.url;
    private String user = super.user;
    private String password = super.password;
    private String forNameData = super.forNameData;

    // Connection
    private Connection connection; // java.sql.Connection

    // Singleton desing pattern (Instance)
    private static DatabaseConnection instance;

    // Singleton desing pattern (Constructor)
    private DatabaseConnection() {
        try {
            // Driver
            Class.forName(this.forNameData);
            System.out.println("Driver Başarıyla yüklendi");
            // Database
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database bağlatısı başarılı");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Singleton desing pattern (GET)
    public static DatabaseConnection getInstance() {
        try {
            // Eğer instance yoksa veya bağlantı kapalıysa
           if(instance==null || instance.connection.isClosed()){
               instance=new DatabaseConnection();
           }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return instance;
    }

    //GETTER AND SETTER
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // PSVM
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
    }

} //end class
