package org.nerdcore.dmdatatools.controllers;

import org.nerdcore.dmdatatools.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;

@RestController
public class UploadController {
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/uploadmap", consumes = {"multipart/form-data"})
    public ModelAndView uploadMap(@RequestParam("file")MultipartFile file){
        storageService.uploadFile(file);
        return new ModelAndView("showmapppage");

    }

    @RequestMapping("/upload-form")
    public ModelAndView requestUploadMapForm(){
        return new ModelAndView("uploadimageform");
    }
}
