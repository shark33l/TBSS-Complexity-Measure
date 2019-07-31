package com.shakeel.complexity_measure.identifiers;

import java.text.ParseException;

public class IfRecursionIdentifier {


    private char[] codeLineArray;
    private int countBrackets;
    private int lineCount;
    private int countPlus;

    public IfRecursionIdentifier() {
        setCountBrackets(0);
        setLineCount(0);
        setCountPlus(0);
        setCodeLineArray("".toCharArray());
    }

    public void ifRecursionMethod(String codeLine) throws ParseException {

        setCodeLineArray(codeLine.toCharArray());
        System.out.println(getCodeLineArray());

        if(getCountBrackets() > 0 || codeLine.startsWith("if") || codeLine.startsWith("else")) {
            for (char code : this.codeLineArray) {

                if (code == '{') {
                    setCountBrackets(getCountBrackets() + 1);
                } else if (code == '}') {
                    setCountBrackets(getCountBrackets() - 1);
                }

                if (getCountBrackets() > 0) {
                    if (code == '+') {
                        setCountPlus(getCountPlus() + 1);
                    }
                }

            }
        }

//        if (codeLine.contains(" ")){
//            codeLine.replace(" ","");
//        }

//        if(codeLine.contains("+")){
//            setCountPlus(getCountPlus() +1);
//        }
//
//        //Set Count
//        if(codeLine.contains("{")){
//            setCountBrackets(getCountBrackets() + 1);
//        } else if (codeLine.contains("}")){
//            setCountBrackets(getCountBrackets() - 1);
//        }

        //return getCountBrackets();

    }

    public char[] getCodeLineArray() {
        return codeLineArray;
    }

    public void setCodeLineArray(char[] codeLineArray) {
        this.codeLineArray = codeLineArray;
    }

    public int getCountBrackets() {
        return countBrackets;
    }

    public void setCountBrackets(int countBrackets) {
        this.countBrackets = countBrackets;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public int getCountPlus() {
        return countPlus;
    }

    public void setCountPlus(int countPlus) {
        this.countPlus = countPlus;
    }
}
