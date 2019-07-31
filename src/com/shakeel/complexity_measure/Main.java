package com.shakeel.complexity_measure;

import com.shakeel.complexity_measure.identifiers.IfRecursionIdentifier;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
	// write your code here

        IfRecursionIdentifier ifRecursionIdentifier = new IfRecursionIdentifier();

        String codeLine = "if (6+1 == 7){{ a+b {7+1+}} ++++}//++++ (){+-=_+";
        String codeLine2 = "}else{+}";

        try {
            ifRecursionIdentifier.ifRecursionMethod(codeLine);
            ifRecursionIdentifier.ifRecursionMethod(codeLine2);
        } catch(ParseException e){
            System.out.println(e);
        }

        System.out.println("Plus - " + ifRecursionIdentifier.getCountPlus());
        System.out.println("Brackets - " + ifRecursionIdentifier.getCountBrackets());
        System.out.println("Line - " + ifRecursionIdentifier.getCodeLineArray());


    }
}
