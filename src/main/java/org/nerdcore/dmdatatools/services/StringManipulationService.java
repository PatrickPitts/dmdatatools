package org.nerdcore.dmdatatools.services;

import java.util.StringJoiner;

public class StringManipulationService {

    public static String capitalizeAllWords(String words){

        String[] allWords = words.split("_|\\s");
        StringJoiner sj = new StringJoiner(" ");
        for(String s : allWords){
            sj.add(s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase());
        }
        return sj.toString();

    }

    public static String scoreAndModifierString(int n){
        int mod = n - 10; mod /= 2;
        StringBuilder st = new StringBuilder(Integer.toString(n));
        st.append("(");
        if(mod >= 0){
            st.append("+");
        }
        st.append(mod).append(")");
        return st.toString();
    }

    public static String paragraphFormat(String text, int... lengthOfLine){

        int lineLength = 80;
        if(lengthOfLine.length > 0){
            lineLength = lengthOfLine[0];
        }

        String[] paragraphs = text.split("\n");
        StringBuilder ret = new StringBuilder();
        for(String paragraph : paragraphs){
            String[] words = paragraph.split(" ");
            String line = ""; int wordIndex = 0;

            while(wordIndex < words.length){
                if(line.length() < lineLength){
                    line += words[wordIndex++] + " ";
                } else {
                    line += "\n";
                    ret.append(line);
                    line = "";
                }
            }
            ret.append(line).append("\n");
        }
        return ret.toString();
    }
}
