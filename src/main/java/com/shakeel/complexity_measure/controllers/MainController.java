package com.shakeel.complexity_measure.controllers;
import com.shakeel.complexity_measure.calculations.ComplexityCalculations;
import com.shakeel.complexity_measure.controllers.ControlStructuresController;
import com.shakeel.complexity_measure.controllers.InheritanceController;
import com.shakeel.complexity_measure.controllers.NestedControlStructureController;
import com.shakeel.complexity_measure.controllers.RecursiveController;
import com.shakeel.complexity_measure.identifiers.RecursionIdentifier;
import com.shakeel.complexity_measure.model.ComplexityModel;
import com.shakeel.complexity_measure.model.RecursionIdentifierModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MainController {

    ArrayList<ComplexityModel> complexityModelArrayList = new ArrayList<>();
    ComplexityCalculations complexityCalculations = new ComplexityCalculations();

    public void fileRead(Path pathPath, String fileNameOnly) throws Exception{

        String path = pathPath.toString();
        String line;
        String commentLine[];
        ArrayList<String> fileLines = new ArrayList<>();
        String fileName = path;
        //Adding a file
        FileReader file = new FileReader(fileName);

        //Reading the file
        BufferedReader br = new BufferedReader(file);

        Boolean multiLineCommentCaught = false;

        while((line = br.readLine())!= null) {

            if(line.contains("//")){

                line = line.split("//")[0];
                if(line.replace(" ","").length() == 0){
                    line = "// Comment Removed";
                }

            } else if(line.contains("/*")){

                commentLine = line.split("/*");
                multiLineCommentCaught = true;

                if(commentLine[1].contains("*/")){
                    multiLineCommentCaught = false;
                    line = commentLine[1];
                    line = line.split("/*")[1];
                }

                if((line.replace(" ","").length() == 0) || (commentLine[0].replace(" ","").length() == 0)){
                    line = "// Comment Removed";
                }

                line = line + " /* Comment Removed */ " + commentLine[0];

            }else if (multiLineCommentCaught){

                if(line.contains("*/")){
                    multiLineCommentCaught = false;
                    line = line.split("/*")[1];

                    if(line.replace(" ","").length() == 0){
                        line = "// Comment Removed";
                    }
                } else {
                    line = "// Comment Removed";
                }

            }

            fileLines.add(line);
            System.out.println(fileLines.size());

        }

        String fileType = getExtension(fileName);

        System.out.println("File Type - " + fileType);
        System.out.println("File Name - " + fileNameOnly);
        System.out.println("File Location - " + fileName);


        SizeComplexity cs = new SizeComplexity();
        ControlStructuresController ctc = new ControlStructuresController();
        NestedControlStructureController cnc = new NestedControlStructureController();
        InheritanceController ci = new InheritanceController();
        RecursionIdentifier cr = new RecursionIdentifier();


        int lineCount = 0;

        //To push all Method Names to verify later
        ArrayList<String> methodList = new ArrayList<>();

        //Save all recursive data in predefined model
        ArrayList<RecursionIdentifierModel> recursionModelList = new ArrayList<>();

        RecursionIdentifierModel recursionModel;
        RecursiveController recursiveController = new RecursiveController();

        //Save all Tokens of Cs
        ArrayList<String> tokens = new ArrayList<>();
        String tokenString = "";

        // Saving all Inheritance Count
        int ciCount[] = ci.printCi(path);

        // Checking recursionMethods
        String currentMethodName = "";


        //Output Table Format
        String format = "| %1$-6s | %2$-125s | %3$-30s | %4$-30s | %5$-15s | %6$-30s | %7$-30s | %8$6.6s | %9$6.6s | %10$6.6s | %11$6.6s |\n";

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
        System.out.format(format, "------", "-----------------------------------------------------------------------------------------------------------------------------", "------------------------------", "------------------------------", "--------------", "------------------------------", "------------------------------","------", "------", "------", "------");
        System.out.format(format, "Line #", "Line", "Declared Method Name", "Called Method Name", "Is Recursive", "Method Starts", "Cs Tokens", "Cs", "Ctc", "Cnc", "Ci");
        System.out.format(format, "------", "-----------------------------------------------------------------------------------------------------------------------------", "------------------------------", "------------------------------", "--------------", "------------------------------", "------------------------------","------", "------", "------", "------");

        //Output Table data
        for (RecursionIdentifierModel recursionIdentifierModel : recursionModelList){

            if(recursionIdentifierModel.getLine().contains("\t")){
                line = recursionIdentifierModel.getLine().replace("\t", "   ");
            } else {
                line = recursionIdentifierModel.getLine();
            }

            // Cs Tokens
            if(line.contains("//") || line.contains("/*")){
                tokenString = "";
            } else {
                try {
                    tokens = cs.measureSize(line);
                    tokenString = "";
                    if (tokens.size() > 0) {
                        for (String tokenCs : tokens) {
                            tokenString = tokenString + tokenCs;
                        }
                    }
                } catch (NullPointerException ex) {
                    System.out.println(ex);
                }
            }

            int lineNo = recursionIdentifierModel.getLineNo();
            String methodName = recursionIdentifierModel.getMethodName();
            String functionName = recursionIdentifierModel.getFunctionName();
            Boolean isVisited = recursionIdentifierModel.getVisited();
            String checkMethodRead = recursionIdentifierModel.getCheckReadMethod();
            int countCs = cs.measureSize(line).size();
            int countCtc = ctc.calculateCtcForLine(line);
            int countCnc = cnc.calculateCncForLine(line);
            int countCi = ciCount[lineNo - 1];
            int countTw = complexityCalculations.totalComplexity(countCtc, countCnc, countCi);
            int countCps = complexityCalculations.complexityStatementCps(countTw, countCs);
            int countCr = 0;

            if(methodName.length() > 0 && isVisited){
                currentMethodName = methodName;
            } else if (currentMethodName.length() > 0){
                if(currentMethodName == checkMethodRead){
                    checkMethodRead = recursionIdentifierModel.getCheckReadMethod();
                }else{
                    checkMethodRead = null;
                }
            }else{
                checkMethodRead = null;
            }

            if(checkMethodRead != null){

                countCr = countCps * 2;

            }


            System.out.format(format,
                    lineNo,
                    line,
                    methodName,
                    functionName,
                    isVisited,
                    checkMethodRead,
                    tokenString,
                    countCs,
                    countCtc,
                    countCnc,
                    countCi
            );

            //Adding to Complexity Model
            ComplexityModel complexityModel = new ComplexityModel();
            complexityModel.setFileType(fileType);
            complexityModel.setFileName(fileNameOnly);
            complexityModel.setFilePath(fileName);
            complexityModel.setLineNo(lineNo);
            complexityModel.setLine(line);

            //Size Complexity
            complexityModel.setCsToken(tokenString);
            complexityModel.setCountCs(countCs);

            //Conditional
            complexityModel.setCountCtc(countCtc);

            //Nested
            complexityModel.setCountCnc(countCnc);

            //Inheritance
            complexityModel.setCountCi(countCi);

            //TW - Total Complexity
            complexityModel.setCountTW(countTw);

            //Cps - Complexity Statement
            complexityModel.setCountCps(countCps);

            //Recursions
            complexityModel.setMethodName(methodName);
            complexityModel.setFunctionName(functionName);
            complexityModel.setRecursive(isVisited);


            //Check if recursive method was visited
//            if(functionName.length() > 0 && isVisited){
//                for(RecursionIdentifierModel recursionIdentifierModelVisitedChecker : recursionModelList){
//                    if(recursionIdentifierModel.getMethodName().equals(functionName)){
//                        recursionIdentifierModel.setTimesVisited(recursionIdentifierModelVisitedChecker.getTimesVisited() + 1);
//                    }
//                }
//            }

            complexityModel.setTimesVisited(recursionIdentifierModel.getTimesVisited());

            //Cr - Temporary Recursion
            complexityModel.setCountCr(countCr);

            //Add Complexity Model to list
            complexityModelArrayList.add(complexityModel);

        }




    }

    public void directoryRead(Path directoryPath, String directoryName){


        System.out.println("Directory Name - " + directoryName);

        try(Stream<Path> subPaths = Files.walk(directoryPath)){
            subPaths.forEach(subPath -> {
                if(getExtension(subPath.toString()).equalsIgnoreCase("java") || getExtension(subPath.toString()).equalsIgnoreCase("cpp")){
                    System.out.println(subPath.toString());
                    try{
                        fileRead(subPath, subPath.getFileName().toString());
                    }catch(Exception ex){
                        System.out.println("Error - " + ex);
                    }
                }
            });
        }catch(IOException IOex){
            System.out.println("Error - " + IOex);
        }

    }

    public String getExtension(String path){

        String fileType;

        int i = path.lastIndexOf('.');

        if (i > 0 &&  i < path.length() - 1) {
            fileType = path.substring(i+1).toLowerCase();
        } else {
            fileType = "Unsupported";
        }

        return fileType;
    }

    public void recursiveCountVisited(){

        for (ComplexityModel complexityModel : complexityModelArrayList){

            if(complexityModel.getFunctionName().length() > 0 /*&& complexityModel.getRecursive()*/){

                for (ComplexityModel complexityModelVisitedChecker : complexityModelArrayList){
                    if(complexityModelVisitedChecker.getMethodName().equals(complexityModel.getFunctionName())){
                        complexityModelVisitedChecker.setTimesVisited(complexityModelVisitedChecker.getTimesVisited() + 1);
                    }
                }

            }

        }

    }

    public void calculateActualMultipleCr(){

        int countCr;
        int countCp;

        for (ComplexityModel complexityModel : complexityModelArrayList){

            countCr = complexityModel.getCountCr() * complexityModel.getTimesVisited();
            countCp = complexityModel.getCountCps() + countCr;
            complexityModel.setCountCr(countCr);
            complexityModel.setCountCp(countCp);

        }

    }

    //Here could be either directory name or file name
    public void generateReport(String selectionType, Path path, String name, String saveLocation){

        CreatePdf createPdf = new CreatePdf();
        complexityModelArrayList.clear();


        try {
            if (selectionType == "File") {
                fileRead(path, name);
            } else {
                directoryRead(path, name);
            }

            recursiveCountVisited();
            calculateActualMultipleCr();
            createPdf.createPdfDocument(complexityModelArrayList, saveLocation);


        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
