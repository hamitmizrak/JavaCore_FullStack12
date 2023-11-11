package com.hamitmizrak.files;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FilePathData implements IUserFileData, ILogData {

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
        this.pathFileName = "\\log.txt";
        this.pathDirectoryName = FilePathUrl.MY_FILE_PATH_URL;
        this.url = pathDirectoryName.concat(pathFileName);    // C:\io\ecodation\log.txt
        this.file = new File(url);
        try {
            //!exists: eğer log.txt adında bir dosya yoksa oluştur
            if (!file.exists()) {
                file.createNewFile();
                System.out.println(url + " dosyanız oluşturuldu");
            } else {
                System.out.println(url + " Böyle bir dosya mevcut oluşturulmadı !!!");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // 1 yıl sonra dosya kendini silsin
        // logDateDelete();
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
    public String logLocalDateTime() {
        Locale locale = new Locale("tr", "TR");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyy HH:mm:ss", locale);
        Date date = new Date();
        String changeToString = simpleDateFormat.format(date).toString();
        return changeToString;
    }

    // logFileWriter
    @Override
    public void logFileWriter(String email, String password) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.url, true))) {
            String nowDate= logLocalDateTime();
            String data = "[ " + logLocalDateTime() + " ]" + email + " " + password;
            bufferedWriter.write(nowDate + "\n");
            bufferedWriter.write(data + "\n");
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //end method logFileWriter

    // logFileReader
    @Override
    public void logFileReader() {
        String rows; // okunan satır
        StringBuilder stringBuilder = new StringBuilder();
        String builderToString;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.url))) {
            while ((rows = bufferedReader.readLine()) != null) {
                stringBuilder.append(rows).append("\n");
            }
            builderToString = stringBuilder.toString();
            System.out.println("LOGLAMA: \n" + builderToString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //end logFileReader

    // log dosyası bir sene sonra silinsin.
    @Override
    public void logDateDelete() {
        // Bütün dosyalar
        userFileList();
        try {
            File fileDelete = new File(url);
            // Dosya varsa ?
            if (fileDelete.exists()) {
                //Long  nowLong=fileDelete.lastModified();
                //int currentYearDate=new Date(nowLong).getYear();
                int currentYearDate=2023;
                int lastYearDate=2023;
                if (currentYearDate==lastYearDate)
                    fileDelete.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Belirtilen zaman aralığında dosyanız silinmedi");
        }
    } //end logDateDelete

    // ----------------------------------------------------
    // userFileCreate
    // Dosya ekle admin(+) writer(+)
    @Override
    public String userFileCreate(String fileName) {
        this.id=UUID.randomUUID().toString();
        this.systemCreatedDate=new Date(System.currentTimeMillis());
        pathFileName="\\"+fileName.concat(".txt");
        pathDirectoryName=FilePathUrl.MY_FILE_PATH_URL;
        url=pathDirectoryName.concat(pathFileName);
        this.file=new File(url);

        try {
        // Böyle bir dosya var mı ?
        if(file.createNewFile()){
            System.out.println(pathFileName+" Böyle bir dosya yoktur ve oluşturuldu");
        }else{
            String fileNameData=pathFileName+" zaten böyle bir dosya var tekrardan oluşturulmadı";
            System.out.println(fileNameData);
            return fileNameData;
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return url+" oluşturuldu";
    }

    // userFileList
    // Dosya Listele admin(+) writer(+)
    @Override
    public List<String> userFileList() {
        List<String> list=new ArrayList<>();
        File fileList=new File(FilePathUrl.MY_FILE_PATH_URL);
        System.out.println(fileList.getPath());
        System.out.println(fileList.getParent());
        for( File temp: fileList.listFiles()){
            System.out.println(temp.getName());
            list.add(temp.getName());
        }
        return list;
    }

    // userFileWriter
    //
    @Override
    public String userFileWriter(String fileName) {
        return null;
    }

    // userFileReader
    @Override
    public String userFileReader(String fileName) {
        return null;
    }

    // userFileDelete
    // Dosya Silme admin(+)
    @Override
    public String userFileDelete() {
        return null;
    }

    // userFileProperties
    // Dosya Bilgileri admin(+) writer(+)
    @Override
    public String userFileProperties() {
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

    public static void main(String[] args) {
        FilePathData filePathData=new FilePathData();
        File fileDelete = new File(filePathData.url);
        System.out.println( new Date(fileDelete.lastModified()) );
    }
} // end File
