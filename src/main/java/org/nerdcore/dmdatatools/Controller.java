package org.nerdcore.dmdatatools;

import org.nerdcore.dmdatatools.GameEntities.Creature;
import org.nerdcore.dmdatatools.GameEntities.CreatureAbility;
import org.nerdcore.dmdatatools.GameEntities.GameEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.CloseReason;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @Value("${exturl.url}")
    private String uri;

    @RequestMapping("/")
    public GameEntity test(){
        RestTemplate template = new RestTemplate();
        return template.getForObject(uri, Creature.class);
    }

    @RequestMapping("/create-creature")
    public ModelAndView createCreatureView(){
        ModelAndView mv = new ModelAndView("creatureuploadform");

        Creature postCreature = new Creature();
        for(int i = 0; i < 99; i++ ){
            postCreature.addAbility(new CreatureAbility());
        }

        mv.addObject("creature", postCreature);

        return mv;

    }

    @PostMapping("/uploadcreature")
    public GameEntity uploadCreature(@ModelAttribute("creature")Creature creature){

        for(Boolean b : creature.getSavingThrows().values()){
            System.out.println(b);
        }

        return creature;
    }
}
