package com.shakeel.complexity_measure.identifiers;

import java.text.ParseException;

public class IfRecursionIdentifier {


    private char[] codeLineArray;
    private int countBrackets;
    private int lineCount;
    private int countPlus;
    private int identifyIfElse;

    public IfRecursionIdentifier() {
        setCountBrackets(0);
        setLineCount(0);
        setCountPlus(0);
        setCodeLineArray("".toCharArray());
    }

    public void ifRecursionMethod(String codeLine) throws ParseException {

        String[] stringCodeArray = codeLine.split("(?=})");
        //System.out.println(getCodeLineArray());

        for (String block : stringCodeArray) {

            System.out.println(block);

            setCodeLineArray(block.toCharArray());

            if((block.replace(" ", "")).startsWith("}else")){
                System.out.println("Started with else");

                //Reduce the start bracket
                setCountBrackets(getCountBrackets() + 1);

            }

            for (char code : this.codeLineArray) {

                if ((block.replace(" ", "")).startsWith("if(") || (block.replace(" ", "")).startsWith("}else") || block.startsWith("else") ||getCountBrackets() > 0) {

                    if (code == '{') {
                        System.out.println(getCountBrackets());
                        setCountBrackets(getCountBrackets() + 1);
                    } else if (code == '}') {
                        System.out.println(getCountBrackets());
                        setCountBrackets(getCountBrackets() - 1);
                    }

                    if (getCountBrackets() > 0) {
                        if (code == '+') {
                            setCountPlus(getCountPlus() + 1);
                        }
                    }

                } else {

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

    public int getIdentifyIfElse() {
        return identifyIfElse;
    }

    public void setIdentifyIfElse(int identifyIfElse) {
        this.identifyIfElse = identifyIfElse;
    }
}
