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
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty, please choose a file.");
        }
        try {
            File dir = new File(context.getRealPath("upload/" + folder).toString());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            String hexName = Integer.toHexString(fileName.hashCode()) +
                    fileName.substring(fileName.lastIndexOf("."));
            String newFileName = "upload-" + System.currentTimeMillis() + "-" + hexName;

            File savedFile = new File(dir, newFileName);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
