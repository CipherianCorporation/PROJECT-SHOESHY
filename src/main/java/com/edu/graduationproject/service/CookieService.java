package com.edu.graduationproject.service;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Service;

@Service
public interface CookieService {
    void add(String name, String value, int hours);

    void remove(String name);

    Cookie get(String name);

    String getValue(String name);
}
