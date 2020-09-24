package org.nerdcore.dmdatatools.GameEntities;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.util.Map;

import static org.nerdcore.dmdatatools.services.StringManipulationService.*;


public class SimpleCreature {

    private String simpleCreatureName;
    private String simpleCreatureBody;

    public SimpleCreature(String simpleCreatureName, String simpleCreatureBody) {
        this.simpleCreatureName = simpleCreatureName;
        this.simpleCreatureBody = simpleCreatureBody;
    }

    public SimpleCreature(){}

    public SimpleCreature(Map<String, Object> simpleCreatureParameters) {
        writeSimpleTextBody(simpleCreatureParameters);
    }

    public String getSimpleCreatureName() {
        return simpleCreatureName;
    }

    public void setSimpleCreatureName(String simpleCreatureName) {
        this.simpleCreatureName = simpleCreatureName;
    }

    public String getSimpleCreatureBody() {
        return simpleCreatureBody;
    }

    public void setSimpleCreatureBody(String simpleCreatureBody) {
        this.simpleCreatureBody = simpleCreatureBody;
    }

    public void writeSimpleTextBody(Map<String, Object> simpleCreatureParameters) {

        StringBuilder textBody = new StringBuilder();
        textBody.append(capitalizeAllWords(simpleCreatureParameters.get("Name").toString())).append("\n");
        textBody.append(String.format("%s %s, %s%n",
                capitalizeAllWords(simpleCreatureParameters.get("Size").toString()),
                capitalizeAllWords(simpleCreatureParameters.get("CreatureType").toString()),
                capitalizeAllWords(simpleCreatureParameters.get("Alignment").toString())));
        textBody.append("------------------------------\n");
        textBody.append(String.format("Armor Class %s%n", capitalizeAllWords(simpleCreatureParameters.get("ArmorClass").toString())));
        textBody.append(String.format("Hit Points %s%n", capitalizeAllWords(simpleCreatureParameters.get("HitPoints").toString())));
        textBody.append(String.format("Speed %s%n", capitalizeAllWords(simpleCreatureParameters.get("Speed").toString())));

        textBody.append("------------------------------\n");
        textBody.append("    STR        DEX        CON        INT        WIS        CHA    \n");
        for (int i = 0; i < 6; i++) {
            //8: int array of Ability Scores
            int as = ((int[]) simpleCreatureParameters.get("AbilityScores"))[i];
            String abScoreString = StringUtils.center(scoreAndModifierString(as), 11);
            textBody.append(abScoreString);
        }
        textBody.append("\n------------------------------\n");
        if(simpleCreatureParameters.get("SavingThrows") != null){
            textBody.append(String.format("Saving Throws %s%n", capitalizeAllWords(simpleCreatureParameters.get("SavingThrows").toString())));
        }
        if(simpleCreatureParameters.get("Skills") != null){
            textBody.append(String.format("Skills %s%n", capitalizeAllWords(simpleCreatureParameters.get("Skills").toString())));

        }
        if(simpleCreatureParameters.get("DamageResitances") != null){
            textBody.append(String.format("Damage Resistances %s%n", capitalizeAllWords(simpleCreatureParameters.get("DamageResistances").toString())));

        }
        if(simpleCreatureParameters.get("DamageImmunities") != null){
            textBody.append(String.format("Damage Immunities %s%n", capitalizeAllWords(simpleCreatureParameters.get("DamageImmunities").toString())));

        }
        if(simpleCreatureParameters.get("ConditionImmunities") != null){
            textBody.append(String.format("Condition Immunities %s%n", capitalizeAllWords(simpleCreatureParameters.get("ConditionImmunities").toString())));

        }
        if(simpleCreatureParameters.get("Senses") != null){
            textBody.append(String.format("Senses %s%n", capitalizeAllWords(simpleCreatureParameters.get("Senses").toString())));
        }
        textBody.append("Langages ");
        if(simpleCreatureParameters.get("Languages") != null){
            textBody.append(capitalizeAllWords(simpleCreatureParameters.get("Languages").toString())).append("\n");

        } else {
            textBody.append("-\n");
        }
        textBody.append(String.format("Challenge %s%n", simpleCreatureParameters.get("Challenge").toString()));
        textBody.append("\n------------------------------\n");
        if(simpleCreatureParameters.get("Features") != null){
            for(String str : (String[])simpleCreatureParameters.get("Features")){
                textBody.append(paragraphFormat(str)).append("\n\n");
            }
            textBody.append("\n------------------------------\n");
        }
        if(simpleCreatureParameters.get("Actions") != null){
            textBody.append("Actions\n");
            textBody.append("\n------------------------------\n");
            for(String str : (String[])simpleCreatureParameters.get("Actions")){
                textBody.append(paragraphFormat(str)).append("\n\n");
            }
        }
        if(simpleCreatureParameters.get("Reactions") != null){
            textBody.append("Reactions\n");
            textBody.append("\n------------------------------\n");
            for(String str : (String[])simpleCreatureParameters.get("Reactions")){
                textBody.append(paragraphFormat(str)).append("\n\n");
            }
        }
        if(simpleCreatureParameters.get("LegendaryActions") != null){
            textBody.append("Legendary Actions\n");
            textBody.append("\n------------------------------\n");
            for(String str : (String[])simpleCreatureParameters.get("LegendaryActions")){
                textBody.append(paragraphFormat(str)).append("\n\n");
            }
        }





        simpleCreatureBody = textBody.toString();
    }

    @Override
    public String toString() {
        return simpleCreatureBody;
    }
}
