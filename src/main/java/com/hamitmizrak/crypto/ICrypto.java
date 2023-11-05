package com.hamitmizrak.crypto;

abstract public interface ICrypto {

    // // Generate (Şifreyi Kripto)
    public String generateBCryptoPasswordEncoder(String currentPassword);

    // Match
    public Boolean matchCrytoPassword(String currentPassword, String rawPassword);
}
