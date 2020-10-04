package org.nerdcore.dmdatatools.services;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameMapNodeTokenFactory {

    private static final String tokenIdNamePrefix = "mapNodeToken_";
    private static int defaultStartingTokenCount = 5;

    private static JSONObject testTokenMap = new JSONObject();

    public static int getDefaultStartingTokenCount() {
        return defaultStartingTokenCount;
    }

    public static void setDefaultStartingTokenCount(int defaultStartingTokenCount) {
        GameMapNodeTokenFactory.defaultStartingTokenCount = defaultStartingTokenCount;
    }

    public static String configurationMapJSONString(String... parameter){

        return new JSONObject(configurationMap(parameter)).toJSONString();
    }

    public static Map<String, String> configurationMap(String... parameter) {

        Map<String, String> configurationMap = new HashMap<>();
        configurationMap.put("NUM_STARTING_TOKENS", Integer.toString(defaultStartingTokenCount));

        if(parameter == null){
            configurationMap.put("HAS_STARTING_NODES", "false");
            return configurationMap;
        }

        switch (parameter[0].toLowerCase()){
            case "test":
                configurationMap.put("HAS_STARTING_NODES", "true");
                break;
            default:
                break;
        }
        return configurationMap;





    }

    public static String defaultConfigurationJSON() {
//        return JSON.par;
        return null;
    }

    public static Map<String, int[]> startingGameMapNodeTokenMap(int numTokens) {
        Map<String, int[]> tokenMap = new HashMap<>();
        for (int i = 1; i <= numTokens; i++) {
            tokenMap.put(tokenIdNamePrefix + i, new int[2]);
        }
        return tokenMap;
    }

    public static Map<String, int[]> startingGameMapNodeTokenMap() {
        return startingGameMapNodeTokenMap(defaultStartingTokenCount);
    }

    public static Map<String, int[]> testGameMapNodeTokenMap() {
        Map<String, int[]> mapNodeTokenMap = new HashMap<>();
        mapNodeTokenMap.put("mapNodeToken_1", new int[]{2, 1});
        mapNodeTokenMap.put("mapNodeToken_2", new int[]{520, 79});
        mapNodeTokenMap.put("mapNodeToken_3", new int[]{111, 94});
        mapNodeTokenMap.put("mapNodeToken_4", new int[]{610, 788});
        mapNodeTokenMap.put("mapNodeToken_5", new int[]{597, 4});
        return mapNodeTokenMap;
    }

    public static String testGameMapNodeTokenJSONString() {

        JSONArray arr1 = new JSONArray();
        JSONArray arr2 = new JSONArray();
        JSONArray arr3 = new JSONArray();
        JSONArray arr4 = new JSONArray();
        JSONArray arr5 = new JSONArray();

        arr1.add(2);
        arr1.add(1);
        arr2.add(520);
        arr2.add(79);
        arr3.add(111);
        arr3.add(94);
        arr4.add(610);
        arr4.add(788);
        arr5.add(597);
        arr5.add(4);


        JSONObject map = (JSONObject) new JSONObject();
        map.put("mapNodeToken_1", arr1);
        map.put("mapNodeToken_2", arr2);
        map.put("mapNodeToken_3", arr3);
        map.put("mapNodeToken_4", arr4);
        map.put("mapNodeToken_5", arr5);

        testTokenMap = map;

        return map.toJSONString();

    }

    public static void updateTestMap(JSONObject newMap){
        testTokenMap = newMap;
    }

    public static void updateTestMap(Map<String, int[]> newMap){
        testTokenMap = new JSONObject();
        for(String nodeName : newMap.keySet()){
            JSONArray arr = new JSONArray();
            for(int num : newMap.get(nodeName)){
                arr.add(num);
            }
            testTokenMap.put(nodeName,arr);
        }
    }
}
