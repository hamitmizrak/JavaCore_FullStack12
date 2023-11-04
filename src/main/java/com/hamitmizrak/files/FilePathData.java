package com.hamitmizrak.files;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class FilePathData implements IUserFileData,ILogData {

    // Variable
    private String id;
    private String pathFileName;
    private String pathDirectoryName;
    private String url;
    private File file;
    private Date systemCreatedDate;

    // Constructor (Parametresiz)
    public FilePathData() {
        this.id = UUID.randomUUID().toString();
        this.systemCreatedDate = new Date(System.currentTimeMillis());
        this.pathFileName="\\log.txt";
        this.pathDirectoryName=FilePathUrl.MY_FILE_PATH_URL;
        this.url=pathDirectoryName.concat(pathFileName);    // C:\io\ecodation\log.txt
        this.file=new File(url);
        try {
            //exists: eğer log.txt adında bir dosya yoksa oluştur
            if(file.exists()){
                file.createNewFile();
                System.out.println(url+" dosyanız oluşturuldu");
            }else{
                System.out.println(url+"Böyle bir dosya mevcut oluşturulmadı !!!");
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    // toString
    @Override
    public String toString() {
        return "FilePathData{" +
                "id='" + id + '\'' +
                ", pathFileName='" + pathFileName + '\'' +
                ", pathDirectoryName='" + pathDirectoryName + '\'' +
                ", url='" + url + '\'' +
                ", file=" + file +
                ", systemCreatedDate=" + systemCreatedDate +
                '}';
    }

    // METOTLAR
    // ----------------------------------------------------
    // localDateTime
    @Override
    public String logLocalDateTime(){
        Locale locale=new Locale("tr","TR");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MMMM/yyy HH:mm:ss",locale);
        Date date=new Date();
        String changeToString=simpleDateFormat.format(date).toString();
        return changeToString;
    }
    // logFileWriter
    @Override
    public void logFileWriter(String email, String password){
    }

    // logFileReader
    @Override
    public void logFileReader(String email, String password){
    }

    // log dosyası bir sene sonra silinsin.
    @Override
    public void logDateDelete(){
    }

    // ----------------------------------------------------
    // userFileCreate
    @Override
    public String userFileCreate(String fileName){
        return null;
    }
    // userFileList
    @Override
    public List<String> userFileList(){
        return null;
    }

    // userFileWriter
    @Override
    public String userFileWriter(String fileName){
        return null;
    }

    // userFileReader
    @Override
    public String userFileReader(String fileName){
        return null;
    }
    // userFileDelete
    @Override
    public String userFileCreate(){
        return null;
    }
    // userFileProperties
    @Override
    public String userFileProperties(){
        return null;
    }

    /////////////////////////////////////////////////////////////
    //GETTER AND SETTER
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPathFileName() {
        return pathFileName;
    }

    public void setPathFileName(String pathFileName) {
        this.pathFileName = pathFileName;
    }

    public String getPathDirectoryName() {
        return pathDirectoryName;
    }

    public void setPathDirectoryName(String pathDirectoryName) {
        this.pathDirectoryName = pathDirectoryName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Date getSystemCreatedDate() {
        return systemCreatedDate;
    }

    public void setSystemCreatedDate(Date systemCreatedDate) {
        this.systemCreatedDate = systemCreatedDate;
    }
} // end File
