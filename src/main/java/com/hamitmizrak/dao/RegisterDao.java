package com.hamitmizrak.dao;

import com.hamitmizrak.crypto.ICrypto;
import com.hamitmizrak.dto.RegisterDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

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
        id=Math.abs(id);
        for (int i = 1; i <=id ; i++) {
            try (Connection connection = getInterfaceConnection()) {
                // Manipulation:  executeUpdate() [create,delete,update]
                // Manipulation:  executeQuery()  [list Find]
                // Transaction:   Create, Delete, Update
                connection.setAutoCommit(false);
                String sql = "INSERT INTO register (`nick_name`, `email_address`, `password`, `roles`, " +
                        "`remaining_number`, `is_passive`) VALUES (?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "nickname "+i);
                preparedStatement.setString(2, "email_address "+ UUID.randomUUID().toString());
                preparedStatement.setString(3,    generateBCryptoPasswordEncoder("password"+i)  );
                preparedStatement.setString(4, "user");
                preparedStatement.setString(5, String.valueOf(4));
                preparedStatement.setBoolean(6, true);

                // Manipulation:  executeUpdate() [create,delete,update]
                int rowsEffected = preparedStatement.executeUpdate();
                // Eğer ekleme yapılmamışsa -1 döner yoksa 0 büyük rakam girer.
                if (rowsEffected > 0) { //Başarılı
                    connection.commit();
                    System.out.println(RegisterDao.class+ "  ekleme başarılı");
                } else {
                    connection.rollback(); //Başarısız
                    System.out.println(RegisterDao.class+ " !!! Ekleme başarısız");
                }
                return i+" Ekleme";
            }catch (SQLException sql) {
                sql.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null; //eğer ekleme yapmazsa null dönder
    }

    // ALL DELETE
    @Override
    public String allDelete() {
        try (Connection connection = getInterfaceConnection()) {
            // Manipulation:  executeUpdate() [create,delete,update]
            // Manipulation:  executeQuery()  [list Find]
            // Transaction:   Create, Delete, Update
            connection.setAutoCommit(false);
            String sql = "DELETE from  register";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Manipulation:  executeUpdate() [create,delete,update]
            int rowsEffected = preparedStatement.executeUpdate();
            // Eğer ekleme yapılmamışsa -1 döner yoksa 0 büyük rakam girer.
            if (rowsEffected > 0) { //Başarılı
                System.out.println(RegisterDao.class + " Silme başarılı" +list().size());
                connection.commit();
            } else {
                connection.rollback(); //Başarısız
                System.out.println(RegisterDao.class+ " !!!! Silme başarısız");
            }
            return list().size()+ " tane veri silindi";
        }catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            // Hashing Password
            preparedStatement.setString(3, generateBCryptoPasswordEncoder(registerDto.getuPassword()) );

            preparedStatement.setString(4, registerDto.getRolles());
            preparedStatement.setLong(5, registerDto.getRemaingNumber());
            preparedStatement.setBoolean(6, registerDto.getPassive());

            // Manipulation:  executeUpdate() [create,delete,update]
            int rowsEffected = preparedStatement.executeUpdate();
            // Eğer ekleme yapılmamışsa -1 döner yoksa 0 büyük rakam girer.
            if (rowsEffected > 0) { //Başarılı
                connection.commit();
                System.out.println(RegisterDao.class+ "  ekleme başarılı");
            } else {
                connection.rollback(); //Başarısız
                System.out.println(RegisterDao.class+ " !!! Ekleme başarısız");
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
                registerDto.setRemaingNumber(resultSet.getLong("remaining_number"));
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
        RegisterDto registerDto=null;
        try (Connection connection = getInterfaceConnection()) {
            // NOT: email_address String olduğu için tırnak yazmalısınız
            String sql = "SELECT * FROM register where email_address='"+email+"\'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                registerDto=new RegisterDto();
                registerDto.setId(resultSet.getLong("id"));
                registerDto.setuNickName(resultSet.getString("nick_name"));
                registerDto.setuEmailAddress(resultSet.getString("email_address"));
                registerDto.setuPassword(resultSet.getString("password"));
                registerDto.setRolles(resultSet.getString("roles"));
                registerDto.setRemaingNumber(resultSet.getLong("remaining_number"));
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

    // LIST
    // select * from cars;
    @Override
    public ArrayList list() {
        ArrayList<RegisterDto> list= new ArrayList<>();
        RegisterDto registerDto=null;
        try (Connection connection = getInterfaceConnection()) {
            // NOT: email_address String olduğu için tırnak yazmalısınız
            String sql = "SELECT * FROM register";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                registerDto=new RegisterDto();
                registerDto.setId(resultSet.getLong("id"));
                registerDto.setuNickName(resultSet.getString("nick_name"));
                registerDto.setuEmailAddress(resultSet.getString("email_address"));
                registerDto.setuPassword(resultSet.getString("password"));
                registerDto.setRolles(resultSet.getString("roles"));
                registerDto.setRemaingNumber(resultSet.getLong("remaining_number"));
                registerDto.setPassive(resultSet.getBoolean("is_passive"));
                registerDto.setSystemCreatedDate(resultSet.getDate("system_created_date"));
                list.add(registerDto);
            }
            return list;
        }catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE (Object)
    // UPDATE register SET `nick_name`="nick44", `email_address`="email44@gmail.com", `password`="12345644",
    // `roles`="user", `remaining_number`=44, `is_passive`=1 WHERE id = 1;
    @Override
    public RegisterDto update(Long id, RegisterDto registerDto) {
        RegisterDto findRegisterDto=findById(id);
        if(findRegisterDto!=null){
            try (Connection connection = getInterfaceConnection()) {
                // Manipulation:  executeUpdate() [create,delete,update]
                // Manipulation:  executeQuery()  [list Find]
                // Transaction:   Create, Delete, Update
                connection.setAutoCommit(false);
                String sql = "UPDATE register SET `nick_name`=?, `email_address`=?, `password`=?,`roles`=?, " +
                        "`remaining_number`=?, `is_passive`=? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, registerDto.getuNickName());
                preparedStatement.setString(2, registerDto.getuEmailAddress());
                preparedStatement.setString(3, registerDto.getuPassword());
                preparedStatement.setString(4, registerDto.getRolles());
                preparedStatement.setLong(5, registerDto.getRemaingNumber());
                preparedStatement.setBoolean(6, registerDto.getPassive());
                preparedStatement.setLong(7, registerDto.getId());

                // Manipulation:  executeUpdate() [create,delete,update]
                int rowsEffected = preparedStatement.executeUpdate();
                // Eğer ekleme yapılmamışsa -1 döner yoksa 0 büyük rakam girer.
                if (rowsEffected > 0) { //Başarılı
                    connection.commit();
                    System.out.println(RegisterDao.class+ "  Güncellemesi başarılı");
                } else {
                    connection.rollback(); //Başarısız
                    System.out.println(RegisterDao.class+ "  !!!! Güncellemesi başarısız");
                }
                return registerDto;
            }catch (SQLException sql) {
                sql.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(id+" Böyle ID kullanıcı yoktur Bundan dolayı güncelleme yapılamaz");
        }
        return null; //eğer ekleme yapmazsa null dönder
    }

    // UPDATE (Remaning)
    @Override
    public RegisterDto updateRemaing(Long id, RegisterDto registerDto) {
        RegisterDto findRegisterDto=findById(id);
        if(findRegisterDto!=null){
            try (Connection connection = getInterfaceConnection()) {
                // Manipulation:  executeUpdate() [create,delete,update]
                // Manipulation:  executeQuery()  [list Find]
                // Transaction:   Create, Delete, Update
                connection.setAutoCommit(false);
                String sql = "UPDATE register SET `remaining_number`=?, WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, registerDto.getRemaingNumber());
                preparedStatement.setLong(2, registerDto.getId());

                // Manipulation:  executeUpdate() [create,delete,update]
                int rowsEffected = preparedStatement.executeUpdate();
                // Eğer ekleme yapılmamışsa -1 döner yoksa 0 büyük rakam girer.
                if (rowsEffected > 0) { //Başarılı
                    System.out.println(RegisterDao.class+ " Kalan Hak Güncellemesi başarılı");
                    connection.commit();
                } else {
                    connection.rollback(); //Başarısız
                    System.out.println(RegisterDao.class+ " !!!! Kalan Hak Güncellemesi başarısız");
                }
                return registerDto;
            }catch (SQLException sql) {
                sql.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(id+" Böyle ID kullanıcı yoktur Bundan dolayı güncelleme yapılamaz");
        }
        return null; //eğer ekleme yapmazsa null dönder
    }

    // DELETE
    // DELETE FROM register WHERE (`id` = '1');
    @Override
    public RegisterDto deleteById(RegisterDto registerDto) {
        RegisterDto findRegisterDto=findById(registerDto.getId());
        if(findRegisterDto!=null){
            try (Connection connection = getInterfaceConnection()) {
                // Manipulation:  executeUpdate() [create,delete,update]
                // Manipulation:  executeQuery()  [list Find]
                // Transaction:   Create, Delete, Update
                connection.setAutoCommit(false);
                String sql = "DELETE from  register  WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, registerDto.getId());

                // Manipulation:  executeUpdate() [create,delete,update]
                int rowsEffected = preparedStatement.executeUpdate();
                // Eğer ekleme yapılmamışsa -1 döner yoksa 0 büyük rakam girer.
                if (rowsEffected > 0) { //Başarılı
                    System.out.println(RegisterDao.class+ " Silme başarılı");
                    connection.commit();
                } else {
                    connection.rollback(); //Başarısız
                    System.out.println(RegisterDao.class+ " !!!! Silme başarısız");
                }
                return registerDto;
            }catch (SQLException sql) {
                sql.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(registerDto.getId()+" Böyle ID kullanıcı yoktur Bundan dolayı güncelleme yapılamaz");
        }
        return null; //eğer ekleme yapmazsa null dönder
    }

} //end class
