package org.nerdcore.dmdatatools.formatter;

import org.springframework.context.annotation.Bean;


public class CaseFormatter {

    @Bean(name="singleCapital")
    public static String singleCapital(String word){
        if(word.length() == 1){
            return word.toUpperCase();
        }
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    @Bean(name="underscoreTitle")
    public static String underscoreTitle(String phrase){
        String[] words = phrase.split("_");
        StringBuilder ret = new StringBuilder();
        for(String str : words){
            ret.append(str.substring(0, 1).toUpperCase()).append(str.substring(1)).append(" ");
        }
        return ret.toString();
    }
}
