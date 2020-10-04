package org.nerdcore.dmdatatools.controllers;

import org.nerdcore.dmdatatools.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UploadController {
    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/uploadmap", consumes = {"multipart/form-data"})
    public ModelAndView uploadMap(@RequestParam("file")MultipartFile file){
        fileStorageService.uploadFile(file);
        return new ModelAndView("showmapppage");

    }

    @RequestMapping("/upload-form")
    public ModelAndView requestUploadMapForm(){
        return new ModelAndView("uploadimageform");
    }
}
