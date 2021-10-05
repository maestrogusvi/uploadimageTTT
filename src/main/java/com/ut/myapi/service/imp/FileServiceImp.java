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

        int read = 0;
        byte[] buffer = new byte[4096];

        try {
            URL downloadURL = new URL(download);

            HttpURLConnection downloadHttp = (HttpURLConnection) downloadURL.openConnection();
            InputStream inputStream = downloadHttp.getInputStream();
            String name  = FilenameUtils.getBaseName(downloadURL.getPath());

            File file = File.createTempFile(name,".jpg");
            FileOutputStream outputStream = new FileOutputStream(file);

            while((read = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,read);
            }
            inputStream.close();
            outputStream.close();
            downloadHttp.disconnect();

            String f =  FileUploadCloudStorage( upload, "image/jpeg",file, buffer);

            if(file.delete())
                return "borrado";
            else
                return "no se borró el archivo";

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public String FileUploadCloudStorage(String upload, String contentType, File file,  byte[] buffer) throws IOException {
        URL uploadURL = new URL(upload);

        HttpURLConnection uploadHttp = (HttpURLConnection) uploadURL.openConnection();
        uploadHttp.setDoOutput(true);
        uploadHttp.setDoInput(true);

        uploadHttp.setRequestMethod("POST");
        uploadHttp.setRequestProperty("Content-type", contentType);
        uploadHttp.setRequestProperty("Authorization", "Bearer ya29.a0ARrdaM9V2dQLRzb8_eFDDtP1O3LLXFULibmopSd-nMLlr7IjMHyZjgu3pZRn89zonnaRLn5Ge7m2Gg_KgadvlILePVMoBeUsyaI-LxI2vdlr9YeyuzrgaGvvFmIYBZ7dxulC5m-1krBVTkty7xljnCLZI1Lugw");
        uploadHttp.connect();

        BufferedOutputStream bos = new BufferedOutputStream(uploadHttp.getOutputStream());
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

        int i;
        byte[] buffer2 = new byte[4096];
        while ((i = bis.read(buffer2)) > 0) {
            bos.write(buffer, 0, i);
        }

        bis.close();
        bos.close();

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

