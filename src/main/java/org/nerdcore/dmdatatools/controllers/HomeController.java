package org.nerdcore.dmdatatools.controllers;

import org.apache.commons.io.IOUtils;
import org.nerdcore.dmdatatools.CommonLists;
import org.nerdcore.dmdatatools.GameEntities.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.codec.binary.Base64;
import sun.nio.ch.IOUtil;

import javax.imageio.ImageIO;
import javax.websocket.CloseReason;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

@RestController
public class HomeController {

    @Value("${exturl.url}")
    private String uri;

//    @RequestMapping("/{creatureName}")
//    public SimpleCreature test(@PathVariable("creatureName") String creatureName){
//        String creatureRequestURI = uri + "/creature/" + creatureName;
//        RestTemplate template = new RestTemplate();
//        //SimpleCreature target = template.getForObject(creatureRequestURI, SimpleCreature.class);
//        return target;
//        //return template.getForObject(uri, SimpleCreature.class);
//    }

    @RequestMapping(value = "/tohmap")
    public ModelAndView getTombOfHorrors(){
        ModelAndView mv = new ModelAndView("showmappage");
        String mapurl = uri+"/mapimage/toh";
        System.out.println(mapurl);
        mv.addObject("mapurl",mapurl);
        return mv;
    }


    @RequestMapping("/create-creature")
    public ModelAndView createCreatureView(){
        ModelAndView mv = new ModelAndView("creatureuploadform");
        RestTemplate template = new RestTemplate();
//        List<Sourcebook> sources = template.getForObject();
        Creature postCreature = new Creature();
        for(int i = 0; i < 99; i++ ){
            postCreature.addAbility(new CreatureAbility());
        }

        mv.addObject("creature", postCreature);
        mv.addObject("resistanceList", CommonLists.getResistances());

        return mv;

    }

    @PostMapping("/uploadcreature")
    public GameEntity uploadCreature(@ModelAttribute("creature")Creature creature){

        System.out.println(creature.getAbilities().get(0).getAbilityDescription());
        System.out.println(creature.getAbilities().get(1).getAbilityDescription());

        return creature;
    }


}
