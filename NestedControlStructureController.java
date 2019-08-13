package com.shakeel.complexity_measure.controllers;

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
    String closeBracketChecking = "^(\\s*\\}\\s*)|^(\\s*)$";

    public NestedControlStructureController(ArrayList<String> lines){
        this.lines = lines;
        cncCount = new ArrayList<Integer>(lines.size());
    }

    public void changingBracketsCount(){

        if(brackets > 0){
            brackets--;
        }else{
            brackets++;
        }
    }

    //Calculate Ctc value for each line
    public void calculateLineByLine(){

        Pattern pattern1 = Pattern.compile(openBracketsChecking);
        Pattern pattern2 = Pattern.compile(closeBracketChecking);

        //Calculate cnc value for each line
        for(int i = 0; i < lines.size() ; i++){
            int pointsCount = 0;

            String line = lines.get(i);

            //Checking for nested control structures
            Matcher matcher = pattern1.matcher(line);
            while (matcher.find()){
                changingBracketsCount();
            }
            pointsCount = brackets;

            //Checking for close bracket or empty line
            Matcher matcher1 = pattern2.matcher(line);
            if (matcher1.find()){
                pointsCount = 0;
            }

            cncCount.add(pointsCount);
        }
    }

    //Return Cnc value for each line
    public ArrayList<Integer> returnCtcValues(){
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












}
