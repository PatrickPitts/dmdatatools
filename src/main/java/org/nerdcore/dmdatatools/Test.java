package org.nerdcore.dmdatatools;

import org.nerdcore.dmdatatools.formatter.CalculationFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import sun.plugin.com.event.COMEventHandler;

import javax.print.DocFlavor;

public class Test {

    public static void main(String[] args){
        String[] tests = {"abcabc", "abaaba", "abaabc","abcdefg", "abacartoon", "abadfgabcdfgabc"};
        int[] expected = {2,2,2,1,1,3};
        for(int i = 0; i < tests.length; i++){
            StringBuilder s = new StringBuilder(tests[i]);
            s.append(" "). append(recursiveSubstringCount(tests[i])).append(" ").append(expected[i]);
            System.out.println(s.toString());
        }

    }

    public static void stuff(int[] x){
        x[0] = 9;
        for(int y : x){
            System.out.println(y);
        }
    }

    public static int recursiveSubstringCount(String myString){

        if(myString.indexOf("abc") < 0 && myString.indexOf("aba") < 0){
            return 0;
        }

        String choppedString = "";

        if(myString.indexOf("abc") < 0){
            int index = myString.indexOf("aba");
            choppedString = myString.substring(index+3);
        } else if(myString.indexOf("aba") < 0){
            int index = myString.indexOf("abc");
            choppedString = myString.substring(index + 3);
        } else {
            int indexAba = myString.indexOf("aba");
            int indexAbc = myString.indexOf("abc");
            if(indexAba > indexAbc){
                choppedString = myString.substring(indexAbc + 3);
            } else {
                choppedString = myString.substring(indexAba + 3);
            }
        }

        return recursiveSubstringCount(choppedString) + 1;
    }
}
