package com.hamitmizrak.crypto;

abstract public interface ICrypto {

    // // Generate (Sifreyi Kripto)
    public String generateBCryptoPasswordEncoder(String currentPassword);

    // Match
    public Boolean matchCrytoPassword(String currentPassword, String rawPassword);
}
