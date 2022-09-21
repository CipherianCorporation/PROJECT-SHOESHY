package com.edu.graduationproject.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edu.graduationproject.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ServletContext context;

    @Override
    public File save(MultipartFile file, String folder) {
        // TODO Auto-generated method stub
        return null;
    }


}
