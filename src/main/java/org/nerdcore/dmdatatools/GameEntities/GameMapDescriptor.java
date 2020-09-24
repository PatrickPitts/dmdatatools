package org.nerdcore.dmdatatools.GameEntities;

public class GameMapDescriptor {

    private Long gameMapDescriptorID;
    private String locationIdentifier;
    private String description;

    public GameMapDescriptor(Long gameMapDescriptorID, String locationIdentifier, String description) {
        this.gameMapDescriptorID = gameMapDescriptorID;
        this.locationIdentifier = locationIdentifier;
        this.description = description;
    }

    public GameMapDescriptor() {
    }

    public Long getGameMapDescriptorID() {
        return gameMapDescriptorID;
    }

    public void setGameMapDescriptorID(Long gameMapDescriptorID) {
        this.gameMapDescriptorID = gameMapDescriptorID;
    }

    public String getLocationIdentifier() {
        return locationIdentifier;
    }

    public void setLocationIdentifier(String locationIdentifier) {
        this.locationIdentifier = locationIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
