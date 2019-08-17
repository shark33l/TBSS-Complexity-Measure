package com.shakeel.complexity_measure;

import com.shakeel.complexity_measure.identifiers.IfRecursionIdentifier;
import com.shakeel.complexity_measure.identifiers.RecursionIdentifier;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) {

        IfRecursionIdentifier ifRecursionIdentifier = new IfRecursionIdentifier();
        RecursionIdentifier recursionIdentifier = new RecursionIdentifier();

        String codeLine = "if (6+1 == 7) { a - b = a; +-b = b}  a++;";
        String codeLine2 = "}else {";
        String codeLine3 = "+++}";
        String codeLine4 = "return functionName2.function();";
        String codeLine5 = "public void ifRecursionMethod(String codeLine) throws ParseException { func();}";

        recursionIdentifier.containsMethodCall(codeLine);
        recursionIdentifier.containsMethodCall(codeLine4);
        recursionIdentifier.containsMethodCall(codeLine5);

//        try {
//            ifRecursionIdentifier.ifRecursionMethod(codeLine);
//            ifRecursionIdentifier.ifRecursionMethod(codeLine2);
//            ifRecursionIdentifier.ifRecursionMethod(codeLine3);
//        } catch(ParseException e){
//            System.out.println(e);
//        }
//
//        System.out.println("Plus - " + ifRecursionIdentifier.getCountPlus());
//        System.out.println("Brackets - " + ifRecursionIdentifier.getCountBrackets());
//        System.out.println(ifRecursionIdentifier.getCodeLineArray());


    }
}
