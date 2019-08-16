package com.shakeel.complexity_measure.controllers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlStructuresController {

    ArrayList<String> lines;
    ArrayList<Integer> ctcCount; //No of Control Structure Units

    //Regular Expression for checking if conditions
    String conditionalChecking = "((?<!\\S)(if\\s*\\())"; /*/S - Non-white space character, s* - White Space characters*/
    //Regular Expression for checking for, while, do.. while conditions
    String iterativeChecking = "((?<!\\S)(for\\s*\\())|(while\\s*\\(.*\\)\\s*(?!\\s*;)|do\\s*\\{|do\\s*(?!.))";
    //Regular Expression for checking catch statements
    String catchChecking = "((?<!\\S)(catch\\s*\\())";
    //Regular Expression for checking switch statements
    String caseChecking = "((?<!\\S)(case\\s+.*\\:|default\\s*\\:))";
    //Regular Expression for checking logical operators
    String logicalOperatorsChecking = "(\\||\\&&)";
    //Regular Expression for checking bitwise operators
    String bitwiseOperatorsChecking = "(\\|\\&)";

    public ControlStructuresController(ArrayList<String> lines){
        this.lines  = lines;
        ctcCount = new ArrayList<Integer>(lines.size());
    }

    //Points calculation

    //For each of the if conditions add 1 point and for each bitwise or logical operator add 1 point.
    public int conditionalCount(String line){
        Pattern pattern1 = Pattern.compile(conditionalChecking);
        Matcher matcher1 = pattern1.matcher(line);

        int pointsCount = 0;
        while (matcher1.find()){
            Pattern pattern2 = Pattern.compile(logicalOperatorsChecking);
            Matcher matcher2 = pattern2.matcher(line);

            Pattern pattern3 = Pattern.compile(bitwiseOperatorsChecking);
            Matcher matcher3 = pattern3.matcher(line);

            if (matcher2.find()){
                pointsCount++;
            }
            if(matcher3.find() ){
                pointsCount++;
            }

            pointsCount++;
        }
        return pointsCount;
    }

    //For each of the for, while, do..while conditions add 2 points and for each bitwise or logical operator add 2 points.
    public int iterativeCount(String line){
        Pattern pattern1 = Pattern.compile(iterativeChecking);
        Matcher matcher1 = pattern1.matcher(line);

        int pointsCount = 0;
        while (matcher1.find()){
            Pattern pattern2 = Pattern.compile(logicalOperatorsChecking);
            Matcher matcher2 = pattern2.matcher(line);

            Pattern pattern3 = Pattern.compile(bitwiseOperatorsChecking);
            Matcher matcher3 = pattern3.matcher(line);

            if (matcher2.find()){
                pointsCount = pointsCount + 2;
            }
            if(matcher3.find()){
                pointsCount = pointsCount + 2;
            }
            pointsCount = pointsCount + 2;
        }
        return pointsCount;
    }

    //For each of the catch statements add 1 point.
    public int catchCount(String line){
        Pattern pattern = Pattern.compile(catchChecking);
        Matcher matcher = pattern.matcher(line);

        int pointsCount = 0;
        while (matcher.find()){
            pointsCount++;
        }
        return pointsCount;
    }

    //For each of the case statements add 1 point.
    public int caseCount(String line){
        Pattern pattern = Pattern.compile(caseChecking);
        Matcher matcher = pattern.matcher(line);

        int pointsCount = 0;
        while (matcher.find()){
            pointsCount++;
        }
        return pointsCount;
    }

    //Calculate Ctc value for each line
    public void calculateLineByLine(){
        for(int i = 0; i < lines.size(); i++){
            int conditional_Count = conditionalCount(lines.get(i));
            int iterative_Count = iterativeCount(lines.get(i));
            int catch_Count = catchCount(lines.get(i));
            int case_Count = caseCount(lines.get(i));

            ctcCount.add(conditional_Count + iterative_Count + catch_Count + case_Count);
        }
    }

    //Return Ctc value for each line
    public ArrayList<Integer> returnCtcValues(){
        calculateLineByLine();
        return ctcCount;
    }

    //Calculate total Ctc value for whole program
    public int calculateTotalCtc(){
        int totalCtc = 0;
        for(int i = 0; i < ctcCount.size() ; i++){
            totalCtc = totalCtc + ctcCount.get(i);
        }

        return totalCtc;
    }



//    //For each of the logical operators adds 1 point
//    public int logicalOperatorsCount(String line){
//        Pattern pattern = Pattern.compile(logicalOperatorsChecking);
//        Matcher matcher = pattern.matcher(line);
//
//        int pointsCount = 0;
//        while (matcher.find()){
//            pointsCount++;
//        }
//        return pointsCount;
//    }

//    //For each of the bitwise operators adds 1 point
//    public int bitwiseOperatorsCount(String line){
//        Pattern pattern = Pattern.compile(bitwiseOperatorsChecking);
//        Matcher matcher = pattern.matcher(line);
//
//        int pointsCount = 0;
//        while (matcher.find()){
//            pointsCount++;
//        }
//        return pointsCount;
//    }

}
