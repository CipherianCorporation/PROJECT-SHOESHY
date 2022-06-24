package com.edu.graduationproject.utils;

import java.security.SecureRandom;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Bcryptor {
    public static String encrypt(String password) {
        return BCrypt.with(new SecureRandom()).hashToString(12, password.toCharArray());
    }

    public static boolean matches(String password, String hashedPassword) {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified;
    }
}
