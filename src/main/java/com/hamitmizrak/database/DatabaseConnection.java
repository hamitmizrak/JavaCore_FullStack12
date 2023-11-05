package com.hamitmizrak.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class DatabaseConnection
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
            Class.forName(this.forNameData); // Driver
            //System.out.println("Driver Başarıyla yüklendi");
            connection = DriverManager.getConnection(url, user, password); // Database
           // System.out.println("Database bağlatısı başarılı");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }catch (Exception exception) {
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
        //DatabaseConnection databaseConnection = new DatabaseConnection();
    }

} //end class
