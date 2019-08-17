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
        
        String line;
        ArrayList<String> lines = new ArrayList<>();
        String fileName = "code.java";
        //Adding a file
        FileReader file = new FileReader(fileName);

        //Reading the file
        BufferedReader br = new BufferedReader(file);
//        String extension = FilenameUtils.getExtension(file);

        SizeComplexity cs = new SizeComplexity();
        ControlStructuresController ctc = new ControlStructuresController();
        NestedControlStructureController cnc = new NestedControlStructureController();
        InheritanceController ci = new InheritanceController();
        RecursionIdentifier cr = new RecursionIdentifier();
        

        if (fileName.contains(".java")) {
            System.out.println("\n***********Calculating the Cs Count of Each Line******************");
            cs.measureSize();
            System.out.println("\n***********Calculating the Ctc Count of Each Line******************");
            ctc.displayOutput();
            System.out.println("\n***********Calculating the Cnc Count of Each Line******************");
            cnc.displayOutput();
            System.out.println("\n***********Calculating the Ci Count of Each Line******************");
            ci.measureInheritanceComplexity();
            System.out.println("\n***********Calculating the Cr Count of Each Line******************");
            cr.containsMethodCall(br.readLine());

        }else if(fileName.contains(".cpp")){
            System.out.println("\n***********Calculating the Cs Count of Each Line******************");

            System.out.println("\n***********Calculating the Ctc Count of Each Line******************");
            ctc.displayOutput();
            System.out.println("\n***********Calculating the Cnc Count of Each Line******************");
            cnc.displayOutput();
            System.out.println("\n***********Calculating the Ci Count of Each Line******************");
            ci.measureInheritanceComplexity();
            System.out.println("\n***********Calculating the Cr Count of Each Line******************");

        }else{
            System.out.println("The input file type is not supported!");
        }

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
