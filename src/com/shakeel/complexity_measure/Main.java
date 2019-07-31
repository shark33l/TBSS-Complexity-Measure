package com.shakeel.complexity_measure;

import com.shakeel.complexity_measure.identifiers.IfRecursionIdentifier;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
	// write your code here

        IfRecursionIdentifier ifRecursionIdentifier = new IfRecursionIdentifier();

        String codeLine = "if (6+1 == 7) { a - b = a; +-b = b}  a++;";
        String codeLine2 = "else {";
        String codeLine3 = "+++}";

        try {
            ifRecursionIdentifier.ifRecursionMethod(codeLine);
            ifRecursionIdentifier.ifRecursionMethod(codeLine2);
            ifRecursionIdentifier.ifRecursionMethod(codeLine3);
        } catch(ParseException e){
            System.out.println(e);
        }

        System.out.println("Plus - " + ifRecursionIdentifier.getCountPlus());
        System.out.println("Brackets - " + ifRecursionIdentifier.getCountBrackets());
        System.out.println(ifRecursionIdentifier.getCodeLineArray());


    }
}
