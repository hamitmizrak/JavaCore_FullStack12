package com.hamitmizrak.files;

public interface ILogData {
    // METOTLAR
    // ----------------------------------------------------
    // localDateTime
    String logLocalDateTime();

    // logFileWriter
    void logFileWriter(String email, String password);

    // logFileReader
    void logFileReader();

    // log dosyası bir sene sonra silinsin.
    void logDateDelete();
} //end interface
