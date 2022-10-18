package com.edu.graduationproject.controller.rest;

import java.io.File;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.graduationproject.service.UploadService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@CrossOrigin("*")
@RestController
public class UploadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadRestController.class);

    @Autowired
    UploadService uploadService;

    // upload xong se tra ve json de client co the doc duoc
    @PostMapping("/rest/upload/{folder}")
    public JsonNode upload(@PathParam("file") MultipartFile file,
            @PathVariable("folder") String folder) {
        File saveFile = uploadService.save(file, folder);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", saveFile.getName());
        node.put("size", saveFile.length());
        return node;
    }

}
