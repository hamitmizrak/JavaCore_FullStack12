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
                System.out.println(url + " zaten böyle bir dosya var tekrardan oluşturulmadı!!!");
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
            String nowDate = logLocalDateTime();
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
        specialUserFileList();
        try {
            File fileDelete = new File(url);
            // Dosya varsa ?
            if (fileDelete.exists()) {
                //Long  nowLong=fileDelete.lastModified();
                //int currentYearDate=new Date(nowLong).getYear();
                int currentYearDate = 2023;
                int lastYearDate = 2023;
                if (currentYearDate == lastYearDate)
                    fileDelete.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Belirtilen zaman aralığında dosyanız silinmedi");
        }
    } //end logDateDelete

    // ----------------------------------------------------

    // Special File Find
    @Override
    public File specialFileFind(String fileName) {
        File fileList = new File(FilePathUrl.MY_FILE_PATH_URL);
        String fileName2 = fileName.concat(".txt");
        for (File temp : fileList.listFiles()) {
            if (temp.getName().toString().equals(fileName2)) {
                File fileFind = new File(temp.toString());
                System.out.println(fileFind);
                return temp;
            }
        }
        return null;
    }


    // Special userFileCreate
    // Dosya ekle admin(+) writer(+)
    @Override
    public String specialUserFileCreate(String fileName) {
        this.id = UUID.randomUUID().toString();
        this.systemCreatedDate = new Date(System.currentTimeMillis());
        pathFileName = "\\" + fileName.concat(".txt");
        pathDirectoryName = FilePathUrl.MY_FILE_PATH_URL;
        url = pathDirectoryName.concat(pathFileName);
        this.file = new File(url);
        try {
            // Böyle bir dosya var mı ?
            if (file.createNewFile()) {
                System.out.println(pathFileName + " Böyle bir dosya yoktur ve oluşturuldu");
            } else {
                String fileNameData = pathFileName + " zaten böyle bir dosya var tekrardan oluşturulmadı";
                System.out.println(fileNameData);
                return fileNameData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url + " oluşturuldu";
    }

    // Special userFileList
    // Dosya Listele admin(+) writer(+)
    @Override
    public List<String> specialUserFileList() {
        List<String> list = new ArrayList<>();
        File fileList = new File(FilePathUrl.MY_FILE_PATH_URL);
        System.out.println(fileList.getPath());
        System.out.println(fileList.getParent());
        for (File temp : fileList.listFiles()) {
            System.out.println(temp.getName());
            list.add(temp.getName());
        }
        return list;
    }

    // Special her kullanıcının kendisine ait dosya olsun
    // userFileWriter (Sadece o kullanıcının bilgileri yazdır.
    @Override
    public String specialUserFileWriter(String fileName, String email, String password) {
        // find
        File findData=  specialFileFind(fileName);
        String data="";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(findData.getPath(), true))) {
            String nowDate = logLocalDateTime();
             data = "[ " + logLocalDateTime() + " ]" + email + " " + password;
            //bufferedWriter.write(nowDate + "\n");
            bufferedWriter.write(data + "\n");
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    // Special userFileReader
    // her kullanıcının kendisine ait dosya olsun
    // userFileWriter (Sadece o kullanıcının bilgileri okusun.
    @Override
    public String specialUserFileReader(String fileName) {
        // find
        File findData=  specialFileFind(fileName);

        String rows; // okunan satır
        StringBuilder stringBuilder = new StringBuilder();
        String builderToString = null;
        pathDirectoryName = FilePathUrl.MY_FILE_PATH_URL;
        pathFileName = "\\" + fileName.concat(".txt");
        String url = pathDirectoryName.concat(pathFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(findData.getPath()))) {
            while ((rows = bufferedReader.readLine()) != null) {
                stringBuilder.append(rows).append("\n");
            }
            builderToString = stringBuilder.toString();
            System.out.println("LOGLAMA: \n" + builderToString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builderToString;
    }

    // Special userFileDelete
    // Dosya Silme admin(+)
    @Override
    public String specialUserFileDelete(String fileName) {
        Scanner klavye = new Scanner(System.in);
        specialUserFileList(); //dosyaları listelemek
        File findData=  specialFileFind(fileName);
        //System.out.println("Silmek istediğiniz dosya adını giriniz");
        //String fileName = klavye.nextLine().concat(".txt");
        pathDirectoryName = FilePathUrl.MY_FILE_PATH_URL;
        url = pathDirectoryName.concat("\\").concat(fileName);
        System.out.println("Dosya uzantısı: " + url);

        // Kullanıcıdan onay almak
        char chooise;
        System.out.println(fileName + " bu dosyayı silmek istiyor musunuz ? E / H");
        chooise = klavye.nextLine().charAt(0);
        if (chooise == 'E' || chooise == 'e') {
            try {
                File fileDelete = new File(findData.getPath());
                // exist: böyle bir doya var mı? yok mu?
                if (fileDelete.exists()) {
                    fileDelete.delete();
                    System.out.println(fileName + " adlı dosyanız silindi");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println("Dosyanız silinmedi");
        }
        return fileName;
    }

    // Special userFileProperties
    // Dosya Bilgileri admin(+) writer(+)
    @Override
    public String specialUserFileProperties(String fileName) {
        File findData=  specialFileFind(fileName);
        System.out.println("LENGHT: "+findData.length());
        System.out.println("-----");
        System.out.println("PATH: "+findData.getPath());
        System.out.println("PARENT: "+findData.getParent());
        System.out.println("-----");
        System.out.println("EXECUTE: "+findData.canExecute());
        System.out.println("READ: "+findData.canRead());
        System.out.println("WRITE: "+findData.canWrite());
        System.out.println("-----");
        System.out.println("IS DIRECTORY : "+findData.isDirectory());
        System.out.println("IS FILE : "+findData.isFile());
        System.out.println("IS HIDDEN : "+findData.isHidden());
        System.out.println("-----");
        System.out.println("TOTAL SPACE: "+findData.getTotalSpace());
        System.out.println("FREE SPACE: "+findData.getFreeSpace());

        return findData.getPath();
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
        FilePathData filePathData = new FilePathData();
        // File fileDelete = new File(filePathData.url);
        // System.out.println(new Date(fileDelete.lastModified()));
        //filePathData.fileFind("deneme");
        filePathData.specialUserFileCreate("Hamit");
        filePathData.specialUserFileReader("Hamit");
        filePathData.specialUserFileWriter("Hamit", "data44@gmail.com","şifre66");
        //filePathData.userFileDelete("Hamit");
        filePathData.specialUserFileProperties("Hamit");
    }
} // end File
