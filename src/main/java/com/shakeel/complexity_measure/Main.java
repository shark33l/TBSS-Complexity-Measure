package com.shakeel.complexity_measure;

import com.shakeel.complexity_measure.controllers.ControlStructuresController;
import com.shakeel.complexity_measure.controllers.InheritanceController;
import com.shakeel.complexity_measure.controllers.NestedControlStructureController;
import com.shakeel.complexity_measure.controllers.RecursiveController;
import com.shakeel.complexity_measure.identifiers.RecursionIdentifier;
import com.shakeel.complexity_measure.model.RecursionIdentifierModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.shakeel.complexity_measure.controllers.InheritanceController.ciArray;


public class Main {

    public static final String SOURCE_CODE_PATH = "C:/Users/sasmini_112033/Desktop/TBSS-Complexity-Measure/code1.cpp";
    public static int count = 0;
    public static void main(String[] args) throws Exception{

        String line;
        ArrayList<String> fileLines = new ArrayList<>();
        String fileName = "code.java";
        //Adding a file
        FileReader file = new FileReader(fileName);

        //Reading the file
        BufferedReader br = new BufferedReader(file);
//        String extension = FilenameUtils.getExtension(file);

        while((line = br.readLine())!= null) {
            fileLines.add(line);

        }

        sizeComplexity.SizeComplexity cs = new sizeComplexity.SizeComplexity();
        ControlStructuresController ctc = new ControlStructuresController();
        NestedControlStructureController cnc = new NestedControlStructureController();
        InheritanceController ci = new InheritanceController();
        RecursionIdentifier cr = new RecursionIdentifier();

        int lineCount = 0;
        int totLine = ci.countLines();
        //To push all Method Names to verify later
        ArrayList<String> methodList = new ArrayList<>();

        //Save all recursive data in predefined model
        ArrayList<RecursionIdentifierModel> recursionModelList = new ArrayList<>();

        RecursionIdentifierModel recursionModel;
        RecursiveController recursiveController = new RecursiveController();

        //Output Table Format
        String format = "| %1$-6s | %2$-125s | %3$-30s | %4$-30s | %5$-15s | %6$6.6s | %7$6.6s | %8$6.6s |\n";


        //Test Code Lines
        String[] testLines =
                {
                        "if (6+1 == 7) { a - b = a; +-b = b}  a++;",
                        "}else {",
                        "+++}",
                        "return functionName2.function();",
                        "public void ifRecursionMethod(String codeLine) throws ParseException { ",
                        "return functionName2.ifRecursionMethod();}",
                        "public void addBracket() {",
                        "        brackets++;",
                        "    }",
                        "reader.close()"
                };

        for (String codeLine : fileLines){
            lineCount++;
            recursionModel = recursiveController.recursionSetter(lineCount, codeLine, methodList);
            if(recursionModel.getMethod()){
                methodList.add(recursionModel.getMethodName());
            }

            //If function is recursive set visited for the parent method
            if(recursionModel.getVisited()){
                for(RecursionIdentifierModel recursionIdentifierModel : recursionModelList){
                    if(recursionIdentifierModel.getMethodName().equals(recursionModel.getFunctionName())){
                        recursionIdentifierModel.setVisited(true);
                    }
                }
            }
            recursionModelList.add(recursionModel);
        }

        //Output Table Padding and Header
        System.out.println();
        System.out.format(format, "------", "-----------------------------------------------------------------------------------------------------------------------------", "------------------------------", "------------------------------", "--------------", "------", "------", "------");
        System.out.format(format, "Line #", "Line", "Method Name", "Function Name", "Is Recursive", "Ctc", "Cnc", "Ci");
        System.out.format(format, "------", "-----------------------------------------------------------------------------------------------------------------------------", "------------------------------", "------------------------------", "--------------", "------", "------", "------");

        //Output Table data
        for (RecursionIdentifierModel recursionIdentifierModel : recursionModelList){
            count++;
            if(recursionIdentifierModel.getLine().contains("\t")){
                line = recursionIdentifierModel.getLine().replace("\t", "   ");
            } else {
                line = recursionIdentifierModel.getLine();
            }
            ciArray = ci.printCi(lineCount);
            System.out.format(format,
                    recursionIdentifierModel.getLineNo(),
                    line,
                    recursionIdentifierModel.getMethodName(),
                    recursionIdentifierModel.getFunctionName(),
                    recursionIdentifierModel.getVisited(),
                    ctc.calculateCtcForLine(line),
                    cnc.calculateCncForLine(line),
                    ciArray[count - 1]);

        }


    }
}
