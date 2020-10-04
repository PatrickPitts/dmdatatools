package org.nerdcore.dmdatatools.DataWrapper;

import java.util.Map;

public class MapNodeDataWrapper {

    private String gameMapName;
    private Map<String, int[]> gameMapNodeLocations;

    public String getGameMapName() {
        return gameMapName;
    }

    public void setGameMapName(String gameMapName) {
        this.gameMapName = gameMapName;
    }

    public Map<String, int[]> getGameMapNodeLocations() {
        return gameMapNodeLocations;
    }

    public void setGameMapNodeLocations(Map<String, int[]> gameMapNodeLocations) {
        this.gameMapNodeLocations = gameMapNodeLocations;
    }
}
