package com.hamitmizrak.dao;

import com.hamitmizrak.database.DatabaseConnection;
import java.sql.Connection;
import java.util.ArrayList;

public interface IDaoGenerics <T> {

    public String speedData(Long id);
    public String allDelete();

    /////////////////////////////////////////////////////////
    // C R U D
    // CREATE
    public T create(T t);

    // FIND BY ID
    public T findById(Long id);
    public T findByEmail(String email);

    // LIST
    public ArrayList<T> list();

    // UPDATE
    public T update (Long id, T t);
    public T updateRemaing(Long id, T t);

    // DELETE
    public T deleteById(T t);

    /////////////////////////////////////////////////////////
    default Connection getInterfaceConnection(){
        return DatabaseConnection.getInstance().getConnection();
    }

} //end interface
