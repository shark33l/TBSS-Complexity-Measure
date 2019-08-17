package com.shakeel.complexity_measure.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class NestedControlStructureController {


    ArrayList<String> lines;
    ArrayList<Integer> cncCount;

    int brackets = 0;

    //Regular expression for conditional and iterative control structures with open brackets
    String openBracketsChecking = "\\b((if|while|for|do)(\\s+|\\().*\\{)";
    //Regular expression for close bracket or empty line
    String closeBracketEmptyLineChecking = "^(\\s*\\}\\s*)|^(\\s*)$";
    //Regular expression for close bracket
    String closeBracketChecking = "\\}";
    //Regular expression for else open bracket
    String elseBracketChecking = "\\b((else)(.*\\{))";

    public NestedControlStructureController(){
    }

    public NestedControlStructureController(ArrayList<String> lines){
        this.lines = lines;
        cncCount = new ArrayList<Integer>(lines.size());
    }

//     public void changingBracketsCount(){

//         if(brackets > 0){
//             brackets--;
//         }else{
//             brackets++;
//         }
//     }
    public void addBracket() {
        brackets++;
    }
    
    public void removeBracket() {
        if (brackets > 0) {
            brackets--;
        }
    }

    //Calculate Ctc value for each line
    public void calculateLineByLine(){

        Pattern pattern1 = Pattern.compile(openBracketsChecking);
        Pattern pattern2 = Pattern.compile(closeBracketEmptyLineChecking);
        Pattern pattern3 = Pattern.compile(closeBracketChecking);
        Pattern pattern4 = Pattern.compile(elseBracketChecking);
        
        //Calculate cnc value for each line
        for(int i = 0; i < lines.size() ; i++){
            int pointsCount = 0;

            String line = lines.get(i);

            //Checking for nested control structures
            Matcher matcher1 = pattern1.matcher(line);
            while (matcher1.find()){
                addBracket();
            }
            pointsCount = brackets;

            //Checking for close bracket or empty line
            Matcher matcher2 = pattern2.matcher(line);
            if (matcher1.find()){
                pointsCount = 0;
            }
            
            //Checking for close bracket
            Matcher matcher3 = pattern3.matcher(line);
            while (matcher3.find()){
                removeBracket();
            }

            //Checking for else open bracket
            Matcher matcher4 = pattern4.matcher(line);
            if (matcher4.find()){
                addBracket();
            }

            cncCount.add(pointsCount);
        }
    }

    //Return Cnc value for each line
    public ArrayList<Integer> returnCncValues(){
        calculateLineByLine();
        return cncCount;
    }

    //Calculate total Ctc value for whole program
    public int calculateTotalCnc(){
        int totalCnc = 0;
        for(int i = 0; i < cncCount.size() ; i++){
            totalCnc = totalCnc + cncCount.get(i);
        }

        return totalCnc;
    }
    
    //Display Output
    public void displayOutput() throws IOException {

        String line;
        ArrayList<String> lines = new ArrayList<>();
        String fileName = "code.java";
        int pointsCount = 0;

        //Adding a file
        FileReader file = new FileReader(fileName);

        //Reading the file
        BufferedReader br = new BufferedReader(file);

        while((line = br.readLine())!= null) {
            lines.add(line);
        }
        
        NestedControlStructureController cnc = new NestedControlStructureController(lines);
        ArrayList<Integer> cncCount = cnc.returnCncValues();

        System.out.println("");

        for(int i = 0; i < lines.size() ; i++){
            System.out.println("Line " + (i+1) + " Cnc count = " +cncCount.get(i) );
        }

        System.out.println("Total Cnc Count = " +cnc.calculateTotalCnc());
        System.out.println("");
    }













}
