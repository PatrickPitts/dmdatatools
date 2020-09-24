package org.nerdcore.dmdatatools.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MapSelectController {

    //http://localhost:8080
    private String extApiUrl;

    private String mapRequestUrl;

    public MapSelectController(@Value("${exturl.url}")String extApiUrl,
                               @Value("${exturl.maprequest}")String mapRequestUrl){
        this.extApiUrl = extApiUrl;
        this.mapRequestUrl = mapRequestUrl;
    }

    @RequestMapping("/map-select")
    public ModelAndView getMapSelectChoices(){
        ModelAndView mv = new ModelAndView("mapSelectList");
        List<String> mapNameList= (new RestTemplate()).getForObject(extApiUrl + mapRequestUrl, ArrayList.class);
        mv.addObject("mapNames", mapNameList);
        //TODO Externalize url property
        mv.addObject("mapRequestUrl", "/map-select/");
        return mv;
    }

    @RequestMapping("/map-select/{mapName}")
    public ModelAndView displaySelectedMap(@PathVariable String mapName){
        ModelAndView mv = new ModelAndView("showmappage");
        String mapurl = extApiUrl+mapRequestUrl+"/" + mapName;
        mv.addObject("mapurl",mapurl);
        return mv;
    }
}
