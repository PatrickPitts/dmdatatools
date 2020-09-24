package org.nerdcore.dmdatatools.GameEntities;

import java.util.List;

public class GameMapWithDescriptors {

    private Long gameMapID;
    private String mapName;
    private String maptTitle;
    private List<GameMapDescriptor> descriptors;

    public GameMapWithDescriptors() {
    }

    public GameMapWithDescriptors(Long gameMapID, String mapName, String maptTitle, List<GameMapDescriptor> descriptors) {
        this.gameMapID = gameMapID;
        this.mapName = mapName;
        this.maptTitle = maptTitle;
        this.descriptors = descriptors;
    }

    public Long getGameMapID() {
        return gameMapID;
    }

    public void setGameMapID(Long gameMapID) {
        this.gameMapID = gameMapID;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMaptTitle() {
        return maptTitle;
    }

    public void setMaptTitle(String maptTitle) {
        this.maptTitle = maptTitle;
    }

    public List<GameMapDescriptor> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(List<GameMapDescriptor> descriptors) {
        this.descriptors = descriptors;
    }
}
