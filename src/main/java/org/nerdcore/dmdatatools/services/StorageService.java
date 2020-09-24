package org.nerdcore.dmdatatools.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class StorageService {

    @Value("${upload.path}")
    private String path;

    public void uploadFile(MultipartFile file){

        //TODO Error handling, throw exceptions
        if(file.isEmpty()){
            System.out.println("No file!");
            return;
        }
        try{
            String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();

            File imgFile = new File(filename);
            FileWriter writer = new FileWriter(filename);
            Files.copy(inputStream, Paths.get(path+filename));

        } catch (IOException e){
            String msg = String.format("Failed to store file %s", file.getName());
            e.printStackTrace();
        }
    }


}
