package com.hamitmizrak.files;

public interface ILogData {
    // METOTLAR
    // ----------------------------------------------------
    // localDateTime
    String logLocalDateTime();

    // logFileWriter
    void logFileWriter(String email, String password);

    // logFileReader
    void logFileReader(String email, String password);

    // log dosyası bir sene sonra silinsin.
    void logDateDelete();

}
