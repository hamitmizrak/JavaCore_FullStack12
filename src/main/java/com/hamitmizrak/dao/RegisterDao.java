package com.hamitmizrak.dao;

import com.hamitmizrak.crypto.ICrypto;
import com.hamitmizrak.dto.RegisterDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;

/*
    Transaction:  Create, Delete, Update
    connection.setAutoCommit(false) =>  Default:true
    connection.commit()             => Başarılı
    connection.rollback()           => Başarısız
 */

// RegisterDao
public class RegisterDao implements IDaoGenerics<RegisterDto>, ICrypto {

    // SPEED DATA
    @Override
    public String speedData(Long id) {
        return null;
    }

    // ALL DELETE
    @Override
    public String allDelete() {
        return null;
    }

    /////////////////////////////////////////////////////////
    // K R I P T O
    // Generate
    @Override
    public String generateBCryptoPasswordEncoder(String currentPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String rawPassword=bCryptPasswordEncoder.encode(currentPassword);
        return rawPassword;
    }

    // Match
    @Override
    public Boolean matchCrytoPassword(String currentPassword, String rawPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        // eğer database şifre ile kullancının girdiği şifre aynı ise true döner
        boolean isMatch=bCryptPasswordEncoder.matches(currentPassword,rawPassword);
        return isMatch;
    }

    /////////////////////////////////////////////////////////
    // C R U D
    // CREATE
    @Override
    public RegisterDto create(RegisterDto registerDto) {
        return null;
    }

    // FIND BY ID
    @Override
    public RegisterDto findById(Long id) {
        return null;
    }

    // FIND BY EMAIL
    @Override
    public RegisterDto findByEmail(String email) {
        return null;
    }

    // LIST
    @Override
    public ArrayList list() {
        return null;
    }

    // UPDATE (Object)
    @Override
    public RegisterDto update(Long id, RegisterDto registerDto) {
        return null;
    }

    // UPDATE (Remaning)
    @Override
    public RegisterDto updateRemaing(Long id, RegisterDto registerDto) {
        return null;
    }

    // DELETE
    @Override
    public RegisterDto deleteById(RegisterDto registerDto) {
        return null;
    }

} //end class
