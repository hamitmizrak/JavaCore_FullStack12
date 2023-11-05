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

    /////////////////////////////////////////////////
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
    private RegisterDto login() {
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

    } // end adminProcess


} //end class
