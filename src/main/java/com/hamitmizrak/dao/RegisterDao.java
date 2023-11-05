package com.hamitmizrak.dao;

import com.hamitmizrak.crypto.ICrypto;
import com.hamitmizrak.dto.RegisterDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = bCryptPasswordEncoder.encode(currentPassword);
        return rawPassword;
    }

    // Match
    @Override
    public Boolean matchCrytoPassword(String currentPassword, String rawPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // eğer database şifre ile kullancının girdiği şifre aynı ise true döner
        boolean isMatch = bCryptPasswordEncoder.matches(currentPassword, rawPassword);
        return isMatch;
    }

    /////////////////////////////////////////////////////////
    // C R U D
    // INSERT INTO register (`nick_name`, `email_address`, `password`, `roles`, `remaining_number`,
    // `is_passive`) VALUES ('computer', 'hamitmizrak@gmail.com', '123456','admin','5','1');
    // CREATE
    @Override
    public RegisterDto create(RegisterDto registerDto) {
        try (Connection connection = getInterfaceConnection()) {
            // Manipulation:  executeUpdate() [create,delete,update]
            // Manipulation:  executeQuery()  [list Find]
            // Transaction:   Create, Delete, Update
            connection.setAutoCommit(false);
            String sql = "INSERT INTO register (`nick_name`, `email_address`, `password`, `roles`, " +
                    "`remaining_number`, `is_passive`) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, registerDto.getuNickName());
            preparedStatement.setString(2, registerDto.getuEmailAddress());
            preparedStatement.setString(3, registerDto.getuPassword());
            preparedStatement.setString(4, registerDto.getRolles());
            preparedStatement.setString(5, registerDto.getRemaingNumber());
            preparedStatement.setBoolean(6, registerDto.getPassive());

            // Manipulation:  executeUpdate() [create,delete,update]
            int rowsEffected = preparedStatement.executeUpdate();
            // Eğer ekleme yapılmamışsa -1 döner yoksa 0 büyük rakam girer.
            if (rowsEffected > 0) { //Başarılı
                connection.commit();
            } else {
                connection.rollback(); //Başarısız
            }
            return registerDto;
        }catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; //eğer ekleme yapmazsa null dönder
    }

    // FIND BY ID
    // SELECT * FROM register where id=1;
    @Override
    public RegisterDto findById(Long id) {
        RegisterDto registerDto=null;
        try (Connection connection = getInterfaceConnection()) {
            String sql = "SELECT * FROM register where id="+id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                registerDto=new RegisterDto();
                registerDto.setId(resultSet.getLong("id"));
                registerDto.setuNickName(resultSet.getString("nick_name"));
                registerDto.setuEmailAddress(resultSet.getString("email_address"));
                registerDto.setuPassword(resultSet.getString("password"));
                registerDto.setRolles(resultSet.getString("roles"));
                registerDto.setRemaingNumber(resultSet.getString("remaining_number"));
                registerDto.setPassive(resultSet.getBoolean("is_passive"));
                registerDto.setSystemCreatedDate(resultSet.getDate("system_created_date"));
            }
            return registerDto;

        }catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // FIND BY EMAIL
    // SELECT * FROM register where email_address="hamitmizrak@gmail.com";
    @Override
    public RegisterDto findByEmail(String email) {
        return null;
    }

    // LIST
    // select * from cars;
    @Override
    public ArrayList list() {
        return null;
    }

    // UPDATE (Object)
    // UPDATE register SET `nick_name`="nick44", `email_address`="email44@gmail.com", `password`="12345644", `roles`="user", `remaining_number`=44, `is_passive`=1 WHERE (`id` = '1');
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
    // DELETE FROM register WHERE (`id` = '1');
    @Override
    public RegisterDto deleteById(RegisterDto registerDto) {
        return null;
    }

} //end class
