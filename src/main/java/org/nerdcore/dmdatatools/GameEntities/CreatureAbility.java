package org.nerdcore.dmdatatools.GameEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

public class CreatureAbility implements Serializable {

    private long abilityId;

    private String abilityName;
    private String abilityDescription;
    private AbilityType abilityType;
    private boolean isLimitedUsage = false;
    private LimitedUsageType limitedUsageType;
    private int limitedUsageLower = -1;
    private int limitedUsageUpper = -1;
    private String hitDamage;
    private Creature owningCreature;


    public enum LimitedUsageType{
        DAY, RECHARGE_ROLL, RECHARGE_REST
    }

    public enum  AbilityType{
        ACTION, REACTION, LEGENDARY, TRAIT, LAIR, REGIONAL
    }

    public CreatureAbility() {
    }

    public CreatureAbility(long abilityId, String abilityName, String abilityDescription, AbilityType abilityType, boolean isLimitedUsage, LimitedUsageType limitedUsageType, int limitedUsageLower, int limitedUsageUpper, String hitDamage, Creature owningCreature) {
        this.abilityId = abilityId;
        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
        this.abilityType = abilityType;
        this.isLimitedUsage = isLimitedUsage;
        this.limitedUsageType = limitedUsageType;
        this.limitedUsageLower = limitedUsageLower;
        this.limitedUsageUpper = limitedUsageUpper;
        this.hitDamage = hitDamage;
        this.owningCreature = owningCreature;
    }

    public long getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(long abilityId) {
        this.abilityId = abilityId;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getAbilityDescription() {
        return abilityDescription;
    }

    public void setAbilityDescription(String abilityDescription) {
        this.abilityDescription = abilityDescription;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    public boolean isLimitedUsage() {
        return isLimitedUsage;
    }

    public void setLimitedUsage(boolean limitedUsage) {
        isLimitedUsage = limitedUsage;
    }

    public LimitedUsageType getLimitedUsageType() {
        return limitedUsageType;
    }

    public void setLimitedUsageType(LimitedUsageType limitedUsageType) {
        this.limitedUsageType = limitedUsageType;
    }

    public int getLimitedUsageLower() {
        return limitedUsageLower;
    }

    public void setLimitedUsageLower(int limitedUsageLower) {
        this.limitedUsageLower = limitedUsageLower;
    }

    public int getLimitedUsageUpper() {
        return limitedUsageUpper;
    }

    public void setLimitedUsageUpper(int limitedUsageUpper) {
        this.limitedUsageUpper = limitedUsageUpper;
    }

    public String getHitDamage() {
        return hitDamage;
    }

    public void setHitDamage(String hitDamage) {
        this.hitDamage = hitDamage;
    }

    public Creature getOwningCreature() {
        return owningCreature;
    }

    public void setOwningCreature(Creature owningCreature) {
        this.owningCreature = owningCreature;
    }
}
