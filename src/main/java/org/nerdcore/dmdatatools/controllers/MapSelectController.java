package org.nerdcore.dmdatatools.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import org.nerdcore.dmdatatools.DataWrapper.MapNodeDataWrapper;
import org.nerdcore.dmdatatools.services.FileStorageService;
import org.nerdcore.dmdatatools.services.GameMapNodeTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Controller
public class MapSelectController {

    private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

    private static final String USER_IDENTIFIER_KEY = "DEFAULT_USER";

    @Value("${google.secret.key.path}")
    private Resource gdSecretKey;

    @Value("${google.credentials.folder.path}")
    private Resource credentialsFolder;

    //TODO Callback URI is likely to be an issue, config issues in Google API Credentials

    @Value("${google.oauth.callback.uri}") //intended to be default redirect after OAuth permissions
    private String CALLBACK_URI;

    private GoogleAuthorizationCodeFlow flow;

    @Autowired
    FileStorageService fileStorageService;

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

    @GetMapping(value = {"/"})
    public ModelAndView showHomePage() throws IOException{

        Map<String, String> configurationSettingsMap = GameMapNodeTokenFactory.configurationMap("test");
        configurationSettingsMap.put("HAS_STARTING_NODES", "true");
        ModelAndView mv = new ModelAndView("mapwithmarkers");
        mv.addObject("configurationSettingsMap", GameMapNodeTokenFactory.configurationMapJSONString("test"));
        mv.addObject("mapNodeTokenMap", GameMapNodeTokenFactory.testGameMapNodeTokenJSONString());
        return mv;
    }

    @GetMapping(value = {"/googlesignin"})
    public void doGoogleSignIn(HttpServletResponse response) throws IOException{
        GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        String redirectURL = url.setRedirectUri(CALLBACK_URI).setAccessType("offline").build();
        response.sendRedirect(redirectURL);
    }

    @GetMapping(value={"/oauth"})
    public ModelAndView saveAuthorizationCode(HttpServletRequest request)throws IOException{
        String code = request.getParameter("code");
        if(code != null) {
            saveToken(code);
            return new ModelAndView("dashboard");
        }
        return new ModelAndView("index");
    }

    private void saveToken(String code) throws IOException{
        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(CALLBACK_URI).execute();
        flow.createAndStoreCredential(response, USER_IDENTIFIER_KEY);
    }

    @GetMapping(value={"/create"})
    public void createFile(HttpServletResponse response) throws Exception{

        //fileStorageService.uploadToGDrive(USER_IDENTIFIER_KEY, response, flow);
        fileStorageService.getAllFileNamesFromGDrive(USER_IDENTIFIER_KEY, response, flow);
    }

    @PostMapping(value="/uploadMapData")
    public @ResponseBody void saveMapNodeData(@RequestBody MapNodeDataWrapper mapNodeDataWrapper){

        System.out.println(mapNodeDataWrapper.getGameMapName());
        for(String nodeName : mapNodeDataWrapper.getGameMapNodeLocations().keySet()){
            System.out.printf("%s : %s", nodeName, Arrays.toString(mapNodeDataWrapper.getGameMapNodeLocations().get(nodeName)));
        }

    }


    @PostConstruct
    public void init() throws IOException {
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(gdSecretKey.getInputStream()));
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, secrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(credentialsFolder.getFile())).build();
    }


}
