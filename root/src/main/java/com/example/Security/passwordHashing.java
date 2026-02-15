package com.example.Security;

import org.mindrot.jbcrypt.BCrypt;

public class passwordHashing {
    public static String hashPassword(String input){
        String salt = BCrypt.gensalt(14);
        return BCrypt.hashpw(input, salt);
    }
    public static Boolean verifyPassword(String password, String storedhash){
        return BCrypt.checkpw(password, storedhash);
    }
}
