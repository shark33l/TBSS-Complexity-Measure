package com.shakeel.complexity_measure.identifiers;

public class IfRecursionIdentifier {


    private String codeLine;
    private int countBrackets;
    private int lineCount;
    private int countPlus;

    public IfRecursionIdentifier() {
        setCountBrackets(0);
        setLineCount(0);
        setCountPlus(0);
        setCodeLine("");
    }

    public void ifRecursionMethod(String codeLine){

        char[] codeLineArray = codeLine.toCharArray();

        for ( char code : codeLineArray){

            if(code == '{'){
                setCountBrackets(getCountBrackets() + 1);
            } else if (code == '}'){
                setCountBrackets(getCountBrackets() - 1);
            }

            if(getCountBrackets() > 0){
                if(code == '+'){
                    setCountPlus(getCountPlus() +1);
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

    public String getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(String codeLine) {
        this.codeLine = codeLine;
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
