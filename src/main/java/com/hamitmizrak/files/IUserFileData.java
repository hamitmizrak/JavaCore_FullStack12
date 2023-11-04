package com.hamitmizrak.files;

import java.util.List;

public interface IUserFileData {
    // ----------------------------------------------------
    // userFileCreate
    String userFileCreate(String fileName);

    // userFileList
    List<String> userFileList();

    // userFileWriter
    String userFileWriter(String fileName);

    // userFileReader
    String userFileReader(String fileName);

    // userFileDelete
    String userFileDelete();

    // userFileProperties
    String userFileProperties();

} //end interface
