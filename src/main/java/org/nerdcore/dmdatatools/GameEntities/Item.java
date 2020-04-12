package org.nerdcore.dmdatatools.GameEntities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

public class Item extends GameEntity {

    private String itemName;
    private String itemType;
    private String description;
    private String rarity;

    public Item() {
    }

    public Item(String itemName, String itemType, String description, String rarity) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.description = description;
        this.rarity = rarity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
