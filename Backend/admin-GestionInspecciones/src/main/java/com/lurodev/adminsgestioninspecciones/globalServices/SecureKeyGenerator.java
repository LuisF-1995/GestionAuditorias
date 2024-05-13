package com.lurodev.adminsgestioninspecciones.globalServices;

import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class SecureKeyGenerator {
    private static final int PASSWORD_LENGTH = 12;

    public static String generateSecurePassword() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] passwordBytes = new byte[PASSWORD_LENGTH];
        secureRandom.nextBytes(passwordBytes);
        return Base64.getEncoder().encodeToString(passwordBytes);
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    public static String encodePrivateKey(PrivateKey privateKey) {
        byte[] privateKeyBytes = privateKey.getEncoded();
        return Base64.getEncoder().encodeToString(privateKeyBytes);
    }

    public static String generateEncodedSecret() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secret = new byte[32];
        secureRandom.nextBytes(secret);
        return Base64.getEncoder().encodeToString(secret);
    }
}
