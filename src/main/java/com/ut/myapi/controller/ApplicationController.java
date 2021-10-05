package com.ut.myapi.controller;


import com.ut.myapi.Entity.FileEntity;
import com.ut.myapi.service.FileService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    FileService fileService;

    @PutMapping("/upload")
    public JSONObject updateStudent(@RequestBody FileEntity fileEntity){
        try {
            JSONObject json = new JSONObject();
            json.put("Status", fileService.FileUpload(fileEntity.getDownload(), fileEntity.getUpload()));
            return json;
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
