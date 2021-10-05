package com.ut.myapi.controller;


import com.ut.myapi.Entity.FileDTO;
import com.ut.myapi.Entity.FileEntity;
import com.ut.myapi.service.FileService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    @Autowired
    FileService fileService;

    @PostMapping(path= "/upload", produces= MediaType.APPLICATION_JSON_VALUE)
    public FileDTO updateStudent(@RequestBody FileEntity fileEntity){
        try {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setStatus(fileService.FileUpload(fileEntity.getDownload(), fileEntity.getUpload()));
           fileDTO.setCode("200");
            return fileDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/api/inicio")
    public String inicio(){
        return "Sucess";
    }
}
