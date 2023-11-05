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
                    failedLogin();
                    break;
                case 9:
                    System.out.println("Üye Rolunüz admin(+) writer(+) user(+)");
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
                    System.out.println("Dosya Listele admin(+) ");
                    specialFileDelete();
                    break;
                case 13:
                    System.out.println("Dosya Listele admin(+) writer(+) ");
                    specialFileInformation();
                    break;
                case 14:
                    logout();
                    break;
            }
        } //end while

    } // end adminProcess

    ////////////////////////////////////////////////////////////////////////
    private void specialHomePage() {
    }

    private void memberList() {
    }

    private RegisterDto memberCreate() {
        return null;
    }

    private RegisterDto memberFindById() {
        return null;
    }

    private RegisterDto memberFindEmail() {
        return null;
    }

    private RegisterDto memberUpdate() {
        return null;
    }

    private RegisterDto memberDelete() {
        return null;
    }

    private void failedLogin() {
    }

    private void specialFileCreate() {
    }

    private void specialFileList() {
    }

    private void specialFileDelete() {
    }

    private void specialFileInformation() {
    }

} //end class
