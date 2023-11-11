package com.hamitmizrak.files;

import java.io.File;
import java.util.List;

public interface IUserFileData {

    // Find File
    File specialFileFind(String fileName);

    // userFileCreate
    String specialUserFileCreate(String fileName);

    // userFileList
    List<String> specialUserFileList();

    // userFileWriter
    String specialUserFileWriter(String fileName, String email, String password);

    // userFileReader
    String specialUserFileReader(String fileName);

    // userFileDelete
    String specialUserFileDelete(String fileName);

    // userFileProperties
    String specialUserFileProperties(String fileName);

} //end interface
