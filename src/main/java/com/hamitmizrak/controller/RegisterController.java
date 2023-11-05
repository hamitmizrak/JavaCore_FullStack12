package com.hamitmizrak.controller;

import com.hamitmizrak.dao.IDaoGenerics;
import com.hamitmizrak.dao.RegisterDao;
import com.hamitmizrak.dto.RegisterDto;

import java.util.ArrayList;

public class RegisterController implements IDaoGenerics<RegisterDto> {

    // Injection
    private RegisterDao registerDao= new RegisterDao();

    // SPEED DATA
    @Override
    public String speedData(Long id) {
        return registerDao.speedData(id);
    }

    // DELETE ALL
    @Override
    public String allDelete() {
        return registerDao.allDelete();
    }

    // CERATE
    @Override
    public RegisterDto create(RegisterDto registerDto) {
        return registerDao.create(registerDto);
    }

    // FIND
    @Override
    public RegisterDto findById(Long id) {
        return registerDao.findById(id);
    }

    @Override
    public RegisterDto findByEmail(String email) {
        return registerDao.findByEmail(email);
    }

    // LIST
    @Override
    public ArrayList<RegisterDto> list() {
        return registerDao.list();
    }

    // UPDATE
    @Override
    public RegisterDto update(Long id, RegisterDto registerDto) {
        return registerDao.update(id,registerDto);
    }

    @Override
    public RegisterDto updateRemaing(Long id, RegisterDto registerDto) {
        return registerDao.updateRemaing(id,registerDto);
    }

    // DELETE
    @Override
    public RegisterDto deleteById(RegisterDto registerDto) {
        return registerDao.deleteById(registerDto);
    }
} //end class
