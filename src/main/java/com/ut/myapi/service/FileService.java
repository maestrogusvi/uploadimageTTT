package com.ut.myapi.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    String FileUpload(String download, String upload);
    String FileUploadCloudStorage(String upload, String contentType, File file) throws IOException;
}
