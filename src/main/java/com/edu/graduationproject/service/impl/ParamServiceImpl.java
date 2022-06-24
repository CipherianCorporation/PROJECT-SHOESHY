package com.edu.graduationproject.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.edu.graduationproject.service.ParamService;

public class ParamServiceImpl implements ParamService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    ServletContext context;

    @Override
    public String getString(String name, String defaultValue) {
        String result = request.getParameter(name);
        return result != null ? result : defaultValue;
    }

    @Override
    public int getInt(String name, int defaultValue) {
        String result = request.getParameter(name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    @Override
    public double getDouble(String name, double defaultValue) {
        String result = request.getParameter(name);
        return result != null ? Double.parseDouble(result) : defaultValue;
    }

    @Override
    public boolean getBoolean(String name, boolean defaultValue) {
        String result = request.getParameter(name);
        return result != null ? Boolean.parseBoolean(result) : defaultValue;
    }

    @Override
    public Date getDate(String name, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.parse(request.getParameter(name));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public File save(MultipartFile file, String path) throws Exception {
        File dir = new File(context.getRealPath(path));
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File saveFile = new File(dir, file.getOriginalFilename());
            file.transferTo(saveFile);
            return saveFile;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
