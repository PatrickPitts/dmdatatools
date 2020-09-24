package org.nerdcore.dmdatatools.GameEntities;

import java.io.Serializable;
import java.util.*;

public class Creature extends GameEntity implements Serializable {

    private String creatureName;
    private double challengeRating;
    private Size size;
    private Alignment alignment;

    private String armorClassQualifier;

    private int hitDieBase = 0;
    private int hitDieCount = 0;

    private int armorClass = 10;
    private int groundSpeed = 0;
    private int burrowSpeed = 0;
    private int climbSpeed = 0;
    private int flySpeed = 0;
    private int swimSpeed = 0;
    private DefaultCreatureType creatureType;

    private List<String> skills = new ArrayList<>();

    private List<String> damageVulnerabilities = new ArrayList<>();

    private List<String> damageImmunities = new ArrayList<>();

    private List<String> damageResistances = new ArrayList<>();

    private List<String> conditionImmunities = new ArrayList<>();

    private int blindsight = 0;
    private int darkvision = 0;
    private int tremorsense = 0;
    private int truesight = 0;
    private int passivePerception = 10;
    private int[] abilityScores = {10, 10, 10, 10, 10, 10};

    private Map<AbilityScore, Boolean> savingThrows = new HashMap<AbilityScore, Boolean>(){{
        put(AbilityScore.STR, false);
        put(AbilityScore.DEX, false);
        put(AbilityScore.CON, false);
        put(AbilityScore.INT, false);
        put(AbilityScore.WIS, false);
        put(AbilityScore.CHA, false);

    }};

    private List<CreatureAbility> abilities = new ArrayList<>();

    public enum Alignment {
        LAWFUL, LAWFUL_GOOD, LAWFUL_EVIL, ANY_LAWFUL, GOOD, NEUTRAL, EVIL, ANY_GOOD, ANY_EVIL, CHAOTIC_GOOD, CHAOTIC, CHAOTIC_EVIL, ANY_CHAOTIC, ANY, UNALIGNED
    }

    public enum AbilityScore {
        STR, DEX, CON, INT, WIS, CHA
    }

    public enum Size {
        TINY, SMALL, MEDIUM, LARGE, HUGE, GARGANTUAN
    }

    public enum DefaultCreatureType{
        ABERRATION, BEAST, CELESTIAL, CONSTRUCT, DRAGON, ELEMENTAL, FEY, FIEND, GIANT, HUMANOID, MONSTROSITY, OOZE, PLANT, UNDEAD
    }



    public Creature() {
    }

    public List<String> getDamageVulnerabilities() {
        return damageVulnerabilities;
    }

    public void setDamageVulnerabilities(List<String> damageVulnerabilities) {
        this.damageVulnerabilities = damageVulnerabilities;
    }

    public List<String> getDamageImmunities() {
        return damageImmunities;
    }

    public void setDamageImmunities(List<String> damageImmunities) {
        this.damageImmunities = damageImmunities;
    }

    public List<String> getDamageResistances() {
        return damageResistances;
    }

    public void setDamageResistances(List<String> damageResistances) {
        this.damageResistances = damageResistances;
    }

    public List<String> getConditionImmunities() {
        return conditionImmunities;
    }

    public void setConditionImmunities(List<String> conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    public Map<AbilityScore, Boolean> getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(Map<AbilityScore, Boolean> savingThrows) {
        this.savingThrows = savingThrows;
    }

    public void setChallengeRating(double challengeRating) {
        this.challengeRating = challengeRating;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public String getArmorClassQualifier() {
        return armorClassQualifier;
    }

    public void setArmorClassQualifier(String armorClassQualifier) {
        this.armorClassQualifier = armorClassQualifier;
    }

    public int getHitDieBase() {
        return hitDieBase;
    }

    public void setHitDieBase(int hitDieBase) {
        this.hitDieBase = hitDieBase;
    }

    public int getHitDieCount() {
        return hitDieCount;
    }

    public void setHitDieCount(int hitDieCount) {
        this.hitDieCount = hitDieCount;
    }

    public int getGroundSpeed() {
        return groundSpeed;
    }

    public void setGroundSpeed(int groundSpeed) {
        this.groundSpeed = groundSpeed;
    }

    public int getBurrowSpeed() {
        return burrowSpeed;
    }

    public void setBurrowSpeed(int burrowSpeed) {
        this.burrowSpeed = burrowSpeed;
    }

    public int getClimbSpeed() {
        return climbSpeed;
    }

    public void setClimbSpeed(int climbSpeed) {
        this.climbSpeed = climbSpeed;
    }

    public int getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(int flySpeed) {
        this.flySpeed = flySpeed;
    }

    public int getSwimSpeed() {
        return swimSpeed;
    }

    public void setSwimSpeed(int swimSpeed) {
        this.swimSpeed = swimSpeed;
    }

    public DefaultCreatureType getCreatureType() {
        return creatureType;
    }

    public void setCreatureType(DefaultCreatureType creatureType) {
        this.creatureType = creatureType;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getBlindsight() {
        return blindsight;
    }

    public void setBlindsight(int blindsight) {
        this.blindsight = blindsight;
    }

    public int getDarkvision() {
        return darkvision;
    }

    public void setDarkvision(int darkvision) {
        this.darkvision = darkvision;
    }

    public int getTremorsense() {
        return tremorsense;
    }

    public void setTremorsense(int tremorsense) {
        this.tremorsense = tremorsense;
    }

    public int getTruesight() {
        return truesight;
    }

    public void setTruesight(int truesight) {
        this.truesight = truesight;
    }

    public int getPassivePerception() {
        return passivePerception;
    }

    public void setPassivePerception(int passivePerception) {
        this.passivePerception = passivePerception;
    }

    public int[] getAbilityScores() {
        return abilityScores;
    }

    public void setAbilityScores(int[] abilityScores) {
        this.abilityScores = abilityScores;
    }

    public String getCreatureName() {
        return creatureName;
    }

    public void setCreatureName(String creatureName) {
        this.creatureName = creatureName;
    }

    public double getChallengeRating() {
        return challengeRating;
    }

    public List<CreatureAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<CreatureAbility> abilities) {
        this.abilities = abilities;
    }

    public void addAbility(CreatureAbility ability) {
        this.abilities.add(ability);
    }

    public void cleanup() {
        for (CreatureAbility c : abilities) {
            if (c.getAbilityName().length() <= 0 ) {
                abilities.remove(c);
            }
        }
    }

    public void updateAbilityScore(int score, AbilityScore abilityScore) {
        switch (abilityScore) {
            case STR:
                abilityScores[0] = score;
                break;
            case DEX:
                abilityScores[1] = score;
                break;
            case CON:
                abilityScores[2] = score;
                break;
            case INT:
                abilityScores[3] = score;
                break;
            case WIS:
                abilityScores[4] = score;
                break;
            case CHA:
                abilityScores[5] = score;
                break;
            default:
                break;
        }
    }

    public int proficiencyBonus(){
        if(challengeRating < 5){
            return 2;
        } else if(challengeRating < 9){
            return 3;
        } else if(challengeRating < 13){
            return 4;
        } else if(challengeRating < 17){
            return 5;
        } else if(challengeRating < 21){
            return 6;
        } else if(challengeRating < 25){
            return 7;
        } else if(challengeRating < 29){
            return 8;
        } return 9;
    }
}
