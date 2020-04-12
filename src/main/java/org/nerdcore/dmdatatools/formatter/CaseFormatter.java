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
}
