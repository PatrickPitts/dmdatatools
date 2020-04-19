package org.nerdcore.dmdatatools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Configuration
public class CommonLists {


    private static final List<String> resistances = new ArrayList<String>(){{
        add("Acid");
        add("Cold");
        add("Fire");
        add("Force");
        add("Lightning");
        add("Necrotic");
        add("Poison");
        add("Psychic");
        add("Radiant");
        add("Thunder");
        add("Nonmagical Weapons");
        add("Magic Weapons");
        add("Bludgeoning");
        add("Slashing");
        add("Piercing");
        add("Bludgeoning, Piercing, and slashing from nonmagical attacks not made with silvered weapons");
    }};

    private static final List<String> skills = new ArrayList<String>(){{
        add("Acrobatics");
        add("Animal Handling");
        add("Arcana");
        add("Deception");
        add("History");
        add("Insight");
        add("Intimidation");
        add("Investigation");
        add("Medicine");
        add("Nature");
        add("Perception");
        add("Performance");
        add("Persuasion");
        add("Religion");
        add("Sleight of Hand");
        add("Stealth");
        add("Survival");
    }};

    private static final List<String> conditions = new ArrayList<String>(){{
        add("Blinded");
        add("Charmed");
        add("Deafened");
        add("Fatigued");
        add("Frightened");
        add("Grappled");
        add("Incapacitated");
        add("Invisible");
        add("Paralyzed");
        add("Petrified");
        add("Poisoned");
        add("Prone");
        add("Restrained");
        add("Stunned");
        add("Unconscious");
        add("Exhaustion");
    }};

    private static final List<String> attackTypes = new ArrayList<String>(){{
        add("Melee Weapon Attack");
        add("Ranged Weapon Attack");
        add("Melee or Ranged Weapon Attack");
        add("Melee Spell Attack");
        add("Ranged Spell Attack");
        add("Melee or Range Weapon Attack");
    }};

    private static final List<String> senseTypes = new ArrayList<String>(){{
       add("blindsight");
       add("darkvision");
       add("tremorsense");
       add("truesight");
    }};

    private static final Map<Double, Integer> crToExp = new HashMap<Double, Integer>(){{
        put(0., 10);
        put(0.125, 25);
        put(0.25, 50);
        put(0.5, 100);
        put(1., 200);
        put(2., 450);
        put(3., 700);
        put(4., 1100);
        put(5., 1800);
        put(6., 2300);
        put(7., 2900);
        put(8., 3900);
        put(9., 5000);
        put(10., 5900);
        put(11., 7200);
        put(12., 8400);
        put(13., 10000);
        put(14., 11500);
        put(15., 13000);
        put(16., 15000);
        put(17., 18000);
        put(18., 20000);
        put(19., 22000);
        put(20., 25000);
        put(21., 33000);
        put(22., 41000);
        put(23., 50000);
        put(24., 62000);
        put(25., 75000);
        put(26., 90000);
        put(27., 105000);
        put(28., 120000);
        put(29., 135000);
        put(30., 155000);
    }};


    @Bean(name="resistanceList")
    public static List<String> getResistances() {
        return resistances;
    }

    @Bean(name="skillList")
    public static List<String> getSkills() {
        return skills;
    }

    @Bean(name="conditionList")
    public static List<String> getConditions() {
        return conditions;
    }

    @Bean(name="attackTypeList")
    public static List<String> getAttackTypes() {return attackTypes; }

    @Bean(name="crToExpMap")
    public static Map<Double, Integer> getCrToExp() {return crToExp; }

    @Bean(name="senseTypeList")
    public static List<String> getSenseTypes() {return senseTypes; }

}
