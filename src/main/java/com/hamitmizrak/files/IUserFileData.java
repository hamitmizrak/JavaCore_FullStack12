package com.hamitmizrak.files;

import java.io.File;
import java.util.List;

public interface IUserFileData {

    // Find File
    File fileFind(String fileName);

    // userFileCreate
    String userFileCreate(String fileName);

    // userFileList
    List<String> userFileList();

    // userFileWriter
    String userFileWriter(String fileName,String email, String password);

    // userFileReader
    String userFileReader(String fileName);

    // userFileDelete
    String userFileDelete(String fileName);

    // userFileProperties
    String userFileProperties(String fileName);

} //end interface
