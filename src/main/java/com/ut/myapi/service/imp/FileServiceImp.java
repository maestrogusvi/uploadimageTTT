package com.ut.myapi.service.imp;


import com.ut.myapi.service.FileService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Log4j2
public class FileServiceImp implements FileService {

    @Override
    public String FileUpload(String download, String upload) {

        try {
            URL downloadURL = new URL(download);
            HttpURLConnection downloadHttp = (HttpURLConnection) downloadURL.openConnection();

            String name  = FilenameUtils.getBaseName(downloadURL.getPath());
            String suffix = FilenameUtils.getExtension(downloadURL.getPath());
            String contentType = downloadHttp.getContentType();

            File file = File.createTempFile(name,"."+suffix);
            InputStream inputStream = downloadHttp.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(file);

            int i;
            byte[] buffer = new byte[4096];
            while((i = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,i);
            }
            inputStream.close();
            outputStream.close();
            downloadHttp.disconnect();

            String f =  FileUploadCloudStorage(upload, contentType, file);

            if(file.delete())
                return "borrado";
            else
                return "no se borró el archivo";

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public String FileUploadCloudStorage(String upload, String contentType, File file) throws IOException {
        URL uploadURL = new URL(upload);

        HttpURLConnection uploadHttp = (HttpURLConnection) uploadURL.openConnection();
        uploadHttp.setDoOutput(true);
        uploadHttp.setDoInput(true);

        uploadHttp.setRequestMethod("POST");
        uploadHttp.setRequestProperty("Content-type", contentType);
        uploadHttp.setRequestProperty("Authorization", "Bearer ya29.a0ARrdaM9V2dQLRzb8_eFDDtP1O3LLXFULibmopSd-nMLlr7IjMHyZjgu3pZRn89zonnaRLn5Ge7m2Gg_KgadvlILePVMoBeUsyaI-LxI2vdlr9YeyuzrgaGvvFmIYBZ7dxulC5m-1krBVTkty7xljnCLZI1Lugw");
        uploadHttp.connect();

        //Opción 1

        BufferedOutputStream bos = new BufferedOutputStream(uploadHttp.getOutputStream());
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

        int i;
        byte[] buffer = new byte[4096];
        while((i = bis.read(buffer)) != -1){
            bos.write(buffer, 0, i);
        }

        bis.close();
        bos.close();

        //Opción 2
        /*
        OutputStream bos = uploadHttp.getOutputStream();
        FileInputStream bis = new FileInputStream(file);

        int i;
        byte[] buffer = new byte[4096];
        while((i = bis.read(buffer)) != -1){
            bos.write(buffer, 0, i);
        }

        bis.close();
        bos.close();
         */


        String response;
        int responseCode = uploadHttp.getResponseCode();
        if ((responseCode >= 200) && (responseCode <= 202)) {

            response = ""+responseCode;
        } else {
            response = uploadHttp.getErrorStream().toString();
        }
        uploadHttp.disconnect();

        return  response;
    }

}

