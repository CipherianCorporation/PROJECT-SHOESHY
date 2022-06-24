package com.edu.graduationproject.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface CommonUtils {
    public File saveFile(MultipartFile multipartFile, String path);

    public String randomAlphaNumericString(int size);

}
