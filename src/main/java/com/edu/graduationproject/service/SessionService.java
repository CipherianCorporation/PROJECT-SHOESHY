package com.edu.graduationproject.service;

import org.springframework.stereotype.Service;

@Service
public interface SessionService {
    public <T> T get(String name);

    public <T> T get(String name, T defaultValue);

    public void set(String name, Object value);

    public void remove(String name);

    public boolean isLoggedIn();
}
