package org.nerdcore.dmdatatools.GameEntities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

public class Creature extends GameEntity implements Serializable {

    private String creatureName;
    private double challengeRating;
    private Size size;
    private Alignment alignment;
    private int armorClass;
    private String armorClassQualifier;
    private String hitPointString;
    private int groundSpeed = 0;
    private int burrowSpeed = 0;
    private int climbSpeed = 0;
    private int flySpeed = 0;
    private int swimSpeed = 0;
    private DefaultCreatureType creatureType;
    private List<String> skills = new ArrayList<>();
    private List<String> vulnerabilities = new ArrayList<>();
    private List<String> immunities = new ArrayList<>();
    private List<String> resistances = new ArrayList<>();
    private int blindsight = 0;
    private int darkvision = 0;
    private int tremorsense = 0;
    private int truesight = 0;
    private int passivePerception = 10;
    private int[] abilityScores = {10, 10, 10, 10, 10, 17};
    private List<CreatureAbility> abilities = new ArrayList<>();
    private Map<AbilityScore, Boolean> savingThrows = new LinkedHashMap<AbilityScore, Boolean>(){{
        put(AbilityScore.STR, false);
        put(AbilityScore.DEX, false);
        put(AbilityScore.CON, false);
        put(AbilityScore.INT, false);
        put(AbilityScore.WIS, false);
        put(AbilityScore.CHA, false);
    }};

    private Map<String, ArrayList<String>> commonFeatures = new HashMap<String, ArrayList<String>>(){{
        put("resistances",(ArrayList<String>)Arrays.asList("Fire", "Thunder", "piercing"));

    }};


    public String printAllAbilities(){

        StringBuilder str = new StringBuilder();

        for (CreatureAbility ability : abilities) {
            System.out.println(ability.getAbilityName());
            str.append(ability.getAbilityName());
        }

        return str.toString();
    }


    private enum Alignment {
        LAWFUL, LAWFUL_GOOD, LAWFUL_EVIL, GOOD, NEUTRAL, EVIL, CHAOTIC_GOOD, CHAOTIC, CHAOTIC_EVIL, ANY, UNALIGNED

    }
    private enum AbilityScore {
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

    public Map<AbilityScore, Boolean> getSavingThrows() {
        return savingThrows;
    }

    public List<String> getResistances() {
        return resistances;
    }

    public void setResistances(List<String> resistances) {
        this.resistances = resistances;
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

    public String getHitPointString() {
        return hitPointString;
    }

    public void setHitPointString(String hitPointString) {
        this.hitPointString = hitPointString;
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

    public List<String> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(List<String> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public List<String> getImmunities() {
        return immunities;
    }

    public void setImmunities(List<String> immunities) {
        this.immunities = immunities;
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

    public void setChellangeRating(double challengeRating) {
        this.challengeRating = challengeRating;
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
            if (c.getAbilityName() == null) {
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
}
