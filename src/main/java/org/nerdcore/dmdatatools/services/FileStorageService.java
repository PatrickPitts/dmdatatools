package org.nerdcore.dmdatatools.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Value("${upload.path}")
    private String path;

    @Value("${google.application-name}")
    private String googleDriveApplicationName;

    @Value("${google.drive-folder-id}")
    private String googleDriveFolderID;

    @Autowired
    DirectoryVerificationService directoryVerificationService;

    private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

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

    public List<String> getAllFileNamesFromGDrive(String userIdentifierKey,
                                                  HttpServletResponse response,
                                                  GoogleAuthorizationCodeFlow flow) throws IOException {
        Drive drive = new Drive.Builder(HTTP_TRANSPORT,
                JSON_FACTORY,
                flow.loadCredential(userIdentifierKey))
                .setApplicationName(googleDriveApplicationName)
                .build();
        String pageToken = null;
        do{
            FileList results = drive.files().list()
                    .setQ("mimeType='image/jpeg' and '" + googleDriveFolderID + "' in parents")
                    .setSpaces("drive")
                    .setFields("nextPageToken, files(id, name)")
                    .setPageToken(pageToken)
                    .execute();
            for(com.google.api.services.drive.model.File file : results.getFiles()){
                System.out.printf("Found file : %s (%s)%n", file.getName(), file.getId());
            }
            pageToken = results.getNextPageToken();
        } while(pageToken != null);

        return new ArrayList<String>();
    }

    public void uploadToGDrive(String userIdentifierKey,
                               HttpServletResponse response,
                               GoogleAuthorizationCodeFlow flow) throws Exception{

        Drive drive = new Drive.Builder(HTTP_TRANSPORT,
                JSON_FACTORY,
                flow.loadCredential(userIdentifierKey))
                .setApplicationName(googleDriveApplicationName)
                .build();

        com.google.api.services.drive.model.File file = new com.google.api.services.drive.model.File();
        //TODO get name of image from HttpServletResponse
        file.setName("Tomb-Of-Horrors.jpg");

        //TODO update implementation of getDriveDirectoryID
        String folderID = directoryVerificationService.getDriveDirectoryID(new Object());

        file.setParents(Collections.singletonList(folderID));
        //TODO Currently downloading file from local files, update to upload from webpage
        java.io.File filePath = new java.io.File("src/main/resources/localimages/toh_5e_cropped.jpg");

        FileContent content = new FileContent("image/jpeg", filePath);

        com.google.api.services.drive.model.File uploadFile = drive.files().create(file, content)
                .setFields("id").execute();
    }



}
