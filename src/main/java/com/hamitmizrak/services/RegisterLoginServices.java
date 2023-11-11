package com.hamitmizrak.services;

import com.hamitmizrak.controller.RegisterController;
import com.hamitmizrak.dao.RegisterDao;
import com.hamitmizrak.dto.RegisterDto;
import com.hamitmizrak.files.FilePathData;
import com.hamitmizrak.roles.ERoles;
import java.util.Scanner;

public class RegisterLoginServices {

    // Injection
    private RegisterController registerController = new RegisterController();
    private FilePathData filePathData = new FilePathData();

    ////////////////////////////////////////////////////////////////////////
    // REGISTER
    private RegisterDto register() {
        Scanner klavye = new Scanner(System.in);
        RegisterDto registerDto = new RegisterDto();
        String uNickName, uEmailAddress, uPassword, rolles;
        Long remaingNumber;
        Boolean isPassive;
        System.out.println("\nREGISTER SAYFASI");
        System.out.println("Takma adınızı giriniz");
        uNickName = klavye.nextLine();
        System.out.println("Email adresinizi giriniz");
        uEmailAddress = klavye.nextLine();
        System.out.println("Şifrenizi giriniz");
        uPassword = klavye.nextLine();
        rolles = ERoles.USER.getValue().toString(); // defaultta: roller
        remaingNumber = 5L;
        isPassive = true;
        //****
        registerDto.setuNickName(uNickName);
        registerDto.setuEmailAddress(uEmailAddress);
        registerDto.setuPassword(uPassword);
        registerDto.setRolles(rolles);
        registerDto.setRemaingNumber(remaingNumber);
        registerDto.setPassive(isPassive);
        // CREATE
        return registerController.create(registerDto);
    }

    // LOGIN
    public RegisterDto login() {
        Scanner klavye = new Scanner(System.in);
        RegisterDto registerDto = new RegisterDto();
        String uEmailAddress, uPassword;
        Long remainingNumber = null;

        System.out.println("\nLOGIN SAYFASI");
        System.out.println("Emaili giriniz");
        uEmailAddress = klavye.nextLine();
        System.out.println("Şifreyi giriniz");
        uPassword = klavye.nextLine();

        // Email Find
        RegisterDto registerEmailFindDto = registerController.findByEmail(uEmailAddress);
        // Eğer Kullanıcı yoksa Register olsun
        if (registerEmailFindDto == null) {
            // Önce Kayıt olsun
            register();
            // Sonra tekrar Login sayfasına gelsin
            login();
        } else {
            //  Kullanıcı aktif mi ?
            if (registerEmailFindDto.getPassive() == false) {
                System.out.println("Üyeliğiniz Pasiftir Lütfen Admin'e başvurunuz");
                logout();
            } else {
                RegisterDao registerDao = new RegisterDao();
                String currentPassword = uPassword;
                String rawPassword = registerDao.generateBCryptoPasswordEncoder(currentPassword);
                // Database ile kullanıcı şifresini karşılaştırmak
                boolean result = registerDao.matchCrytoPassword(currentPassword, registerEmailFindDto.getuPassword());
                if (uEmailAddress.equals(registerEmailFindDto.getuEmailAddress()) && result) {
                    adminProcess(registerEmailFindDto);
                } else {
                    // Kullanıcı kalan hakkını azaltmak
                    remainingNumber = registerEmailFindDto.getRemaingNumber();
                    remainingNumber--;
                    registerEmailFindDto.setRemaingNumber(remainingNumber);
                    System.out.println("Kalan Hakkınız: " + registerEmailFindDto.getRemaingNumber());
                    System.out.println("Şifreyi veya Emaili yanlış girdiniz");
                    registerController.updateRemaing(remainingNumber, registerEmailFindDto);
                    // File
                    filePathData.logFileWriter(uEmailAddress, uPassword);
                    // Eğer kullanıcının giriş hakkı kalmazsa
                    if (remainingNumber == 0) {
                        System.out.println("Giriş hakkınız kalmadı, hesabınız bloke oldu");
                        System.out.println("Admin'e başvurunuz. ");
                        logout();
                    } else if (remainingNumber >= 1) {
                        login();
                    }
                }
            }
        } //common else
        return registerEmailFindDto;
    }

    // LOGOUT
    private void logout() {
        System.out.println("Çıkış yapılıyor...");
        System.exit(0);
    }

    ////////////////////////////////////////////////////////////////////////
    // ADMIN
    private void adminProcess(RegisterDto registerDto) {
        while(true){
            Scanner klavye=new Scanner(System.in);
            System.out.println("\nADMIN SAYFASINA HOŞGELDINIZ");
            System.out.println("Lütfen Seçiminizi yapınız");
            System.out.println("1-) Anasayfa\n2-) Kayıtlı Üyeleri Listele\n3-) Üye Ekle");
            System.out.println("4-) Kayıtlı Üye ID bul \n5-)Kayıtlı Üye Email bul\n6-) Kayıtlı Üye Güncelle ");
            System.out.println("7-) Kayıtlı Üye Sil \n8-) Hatalı Giriş Logları\n9-) Rolünüz");
            System.out.println("10-) Dosya Ekle \n11-) Dosya Listele\n12-) Dosya Sil");
            System.out.println("13-) Dosya Bilgileri \n14-) Çıkış");
            int chooise=klavye.nextInt();
            switch (chooise){
                case 1:
                    System.out.println("Anasayfa Sadece Kayıtlı üyeler Giriş yapabilir.");
                    specialHomePage();
                    break;
                case 2:
                    System.out.println("Üye Listesi admin(+) writer(+) user(+)");
                    memberList();
                    break;
                case 3:
                    System.out.println("Üye Ekle admin(+)");
                     RegisterDto createDto=  memberCreate();
                    break;
                case 4:
                    System.out.println("Üye Bul ID admin(+) writer(+)");
                    RegisterDto findByIdDto=  memberFindById();
                    break;
                case 5:
                    System.out.println("Üye Bul EMAIL admin(+) writer(+)");
                    RegisterDto findByEmailDto=  memberFindEmail();
                    break;
                case 6:
                    System.out.println("Üye Güncelle admin(+)");
                    RegisterDto findDto=  memberUpdate();
                    break;
                case 7:
                    System.out.println("Üye Sil admin(+)");
                    RegisterDto deleteDto=  memberDelete();
                    break;
                case 8:
                    System.out.println("Hatalı Giriş Logları admin(+) writer(+) user(+)");
                    logFailedLogin();
                    break;
                case 9:
                    System.out.println("Üye Rolunüz admin(+) writer(+) user(+)");
                    String rolles=userRoles(registerDto.getRolles());
                    break;
                case 10:
                    System.out.println("Dosya ekle admin(+) writer(+) ");
                    specialFileCreate();
                    break;
                case 11:
                    System.out.println("Dosya Listele admin(+) writer(+) ");
                    specialFileList();
                    break;
                case 12:
                    System.out.println("Dosya Silme admin(+) ");
                    specialFileDelete();
                    break;
                case 13:
                    System.out.println("Dosya Bilgileri admin(+) writer(+) ");
                    specialFileInformation();
                    break;
                case 14:
                    logout();
                    break;
            }
        } //end while
    } // end adminProcess


    /////
    // ROLLES
    private String userRoles(String rolles) {
        return rolles;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // METHOD
    // just member login
    private void specialHomePage() {
        System.out.println("Sadece Üye olmuş kişiler bu sayfayı görebilir");
    }

    // CRUD
    // USER LIST
    private void memberList() {
        registerController.list().forEach(System.out::println);
    }

    // CREATE
    private RegisterDto memberCreate() {
        return register();
    }

    // USER FIND ID
    private RegisterDto memberFindById() {
        System.out.println("Lütfen Bulmak istediğiniz Kullanıcı ID'sini yazınız");
        return registerController.findById(new Scanner(System.in).nextLong());
    }

    // USER FIND EMAIL
    private RegisterDto memberFindEmail() {
        System.out.println("Lütfen Bulmak istediğiniz Kullanıcı email adresini yazınız");
        return registerController.findByEmail(new Scanner(System.in).nextLine());
    }

    // UPDATE
    private RegisterDto memberUpdate() {
        Scanner klavye=new Scanner(System.in);
        RegisterDto registerDto=new RegisterDto();
        String uNickName,uEmailAddress, uPassword, uRolles;
        Long remaingNumber,id;
        Boolean isPassive;
        System.out.println("Güncellemek istediğiniz ID giriniz");
        id=klavye.nextLong();
        // NOT: Scannerda tam sayılardan sonra eğer String geliyorsa bir alt satıra geçmek için
        // mutlaka klavye.nextLine() yazmak zorundasınız.
        klavye.nextLine();

        System.out.println("Güncellemek istediğiniz takma adını giriniz");
        uNickName=klavye.nextLine();
        System.out.println("Güncellemek istediğiniz email adresiniz giriniz");
        uEmailAddress=klavye.nextLine();
        System.out.println("Güncellemek istediğiniz şifreyi giriniz");
        uPassword=klavye.nextLine();
        System.out.println("Güncellemek istediğiniz kalan hak sayısını giriniz");
        remaingNumber=klavye.nextLong();
        System.out.println("Güncellemek istediğiniz kullanıcı rol bilgisiniz yapınız.");
        uRolles=klavye.nextLine();
        System.out.println("Güncellemek istediğiniz kullanıcı aktif/pasif yapınız.");
        isPassive=klavye.nextBoolean();
        ////////////////////////////////
        registerDto.setId(id);
        registerDto.setuNickName(uNickName);
        registerDto.setuEmailAddress(uEmailAddress);
        registerDto.setuPassword(uPassword);
        registerDto.setRemaingNumber(remaingNumber);
        registerDto.setPassive(isPassive);
        return registerController.update(id,registerDto);
    }

    // DELETE
    private RegisterDto memberDelete() {
        Scanner klavye=new Scanner(System.in);
        RegisterDto registerDto=new RegisterDto();
        Long id;
        System.out.println("Silmek istediğiniz ID giriniz");
        id=klavye.nextLong();
        registerDto.setId(id);
        return registerController.deleteById(registerDto);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // FILE PROCESS
    // Kullanıcının girdiği yanlış denemeleri
    private void logFailedLogin() {
        filePathData.logFileReader();
    }

    // FILE CREATE
    private void specialFileCreate() {
    }

    // FILE LIST
    private void specialFileList() {
    }

    // FILE DELETE
    private void specialFileDelete() {
    }

    // FILE INFORMATION
    private void specialFileInformation() {
    }

} //end class
