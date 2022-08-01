package com.accountmanagement.practice.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService{

    public String uploadImages(String path, MultipartFile file) throws IOException {
        String name =  file.getOriginalFilename();
        String filePath = path + File.separator+name;
        File f = new File(path);
        if(!f.exists()) {
            boolean mkdir = f.mkdir();
        }
        Files.copy(file.getInputStream(),Paths.get(filePath));
        return name;
    }
    public InputStream loadFileAsResource(String path,String fileName) throws FileNotFoundException {
       String fullPath = path + File.separator + fileName;
        return new FileInputStream(fullPath);
    }

}
