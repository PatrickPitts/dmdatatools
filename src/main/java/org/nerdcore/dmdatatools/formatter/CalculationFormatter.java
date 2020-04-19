package org.nerdcore.dmdatatools.formatter;

public class CalculationFormatter {

    public static String hitDieCalculator(int dieCount, int hitDieBase, int con ){
        int conHP = ((con - 10) / 2)*dieCount;
        int avgHP = (int)Math.floor( conHP + (((hitDieBase + 1) / 2.) * dieCount));
        return String.format("%1$s (%2$sd%3$s + %4$s)", avgHP, dieCount, hitDieBase, conHP);
    }
}
