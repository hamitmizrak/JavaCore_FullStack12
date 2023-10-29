package com.hamitmizrak.javase;

import java.io.IOException;

public class _09_Exception_TryCatch {
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        try{
            int result=5/0;
            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("database.close()");
        }
        System.out.println("son satır");

        throw  new _09_HamitMizrak("hata varrr bilerek yazdım");

    }
}
