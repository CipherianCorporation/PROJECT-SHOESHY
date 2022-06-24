package com.edu.graduationproject.utils;

public class Check {
    public static boolean ParseSDT(String sdt) {
        return sdt.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b");

    }

    public static boolean parseEmail(String email) {
        return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }
}
