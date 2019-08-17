package com.shakeel.complexity_measure;

import com.shakeel.complexity_measure.controllers.RecursiveController;
import com.shakeel.complexity_measure.identifiers.IfRecursionIdentifier;
import com.shakeel.complexity_measure.identifiers.RecursionIdentifier;
import com.shakeel.complexity_measure.model.RecursionIdentifierModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) {

        int lineCount = 0;

        //To push all Method Names to verify later
        ArrayList<String> methodList = new ArrayList<>();

        //Save all recursive data in predefined model
        ArrayList<RecursionIdentifierModel> recursionModelList = new ArrayList<>();

        RecursionIdentifierModel recursionModel;
        RecursiveController recursiveController = new RecursiveController();

        //Output Table Format
        String format = "| %1$-6s | %2$-75s | %3$-30s | %4$-30s | %5$-15s |\n";


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

        for (String codeLine : testLines){
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
        System.out.format(format, "------", "--------------------------------------------------------------------------", "------------------------------", "------------------------------", "--------------");
        System.out.format(format, "Line #", "Line", "Method Name", "Function Name", "Is Recursive");
        System.out.format(format, "------", "--------------------------------------------------------------------------", "------------------------------", "------------------------------", "--------------");

        //Output Table data
        for (RecursionIdentifierModel recursionIdentifierModel : recursionModelList){

            System.out.format(format,
                    recursionIdentifierModel.getLineNo(),
                    recursionIdentifierModel.getLine(),
                    recursionIdentifierModel.getMethodName(),
                    recursionIdentifierModel.getFunctionName(),
                    recursionIdentifierModel.getVisited());

        }


    }
}
