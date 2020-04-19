package org.nerdcore.dmdatatools;

import org.nerdcore.dmdatatools.formatter.CalculationFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import sun.plugin.com.event.COMEventHandler;

public class Test {

    public static void main(String[] args){
        System.out.println(CalculationFormatter.hitDieCalculator(14,10,17));
    }
}
