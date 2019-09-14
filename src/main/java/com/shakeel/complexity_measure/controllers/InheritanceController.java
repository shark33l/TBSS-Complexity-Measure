package com.shakeel.complexity_measure.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InheritanceController {

    public static final String SOURCE_CODE_PATH = "C:/Users/sasmini_112033/Desktop/TBSS-Complexity-Measure/code.java";
    public static final List<String> keywords = Arrays.asList("extends ", "implements ", ":");
    String commaMatching = "(\\,)";
    String multilineComment = "(?s)/\\*.*?\\*/";

    ArrayList<String> programlines;
    ArrayList<Integer> ciCount;

    public void displayOutput() throws Exception {

        FileReader file = new FileReader(SOURCE_CODE_PATH);
        BufferedReader reader = new BufferedReader(file);

        int lines = 0;
        while (reader.readLine() != null)
            lines++;
        reader.close();

        System.out.println("Number of lines: " + lines);

        int ciArray[] = printCi(SOURCE_CODE_PATH);
        for (int i = 0; i < lines; i++) {
            System.out.println("Ci " + "line " + (i + 1) + ": " + ciArray[i]);
        }
    }

    public int countLines(String code) throws Exception {

        FileReader file = new FileReader(SOURCE_CODE_PATH);
        BufferedReader reader = new BufferedReader(file);


        int lines = 0;
        while (reader.readLine() != null)
            lines++;

        reader.close();
        return lines;
    }

    public int[] printCi(String path) throws Exception {

        boolean singleLineCommented = false;
        boolean multiLineCommented = false;

        FileReader bfile = new FileReader(path);

        BufferedReader reader = new BufferedReader(bfile);

        int lines = 0;
        while (reader.readLine() != null)
            lines++;
        reader.close();

        File file = new File(path);
        Scanner sc = new Scanner(file);

        File file1 = new File(path);
        Scanner sc1 = new Scanner(file1);

//        int arraySize = Size;

        int Ci[] = new int[lines];

        int defaultValue = 0;

        int i = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line = line.trim();

            if (!line.isEmpty() && line.contains("class ")&& (path.contains(".java")) ) {
                defaultValue = 2;
            }
            if (!line.isEmpty() && line.contains("class ")&& (path.contains(".cpp")) ) {
                defaultValue = 1;
            }

                for (String keyword : keywords) {
                    if (!line.isEmpty() && line.indexOf(keyword) != -1 && line.contains("class ") ) {
                        if (line.contains(keyword)) {
                            Pattern pattern5 = Pattern.compile(commaMatching);
                            Matcher matcher5 = pattern5.matcher(line);
                            while(matcher5.find()){
                                defaultValue++;
                            }
                            defaultValue = defaultValue + 1;
                        }
                    }
                }
            }

            for (int z = 0; z < lines; z++) {
                Ci[z] = defaultValue;
            }

        int temp = defaultValue;

        while (sc1.hasNextLine()) {
            String line1 = sc1.nextLine();

            if (line1.trim().startsWith("//") || line1.trim().startsWith("import") || line1.trim().startsWith("include")) {
                Ci[i] = 0;
            }
            if (line1.trim().startsWith("/*")) {
                line1 = line1.replace("(?s)/\\*.*?\\*/", "");
                Ci[i] = 0;
            }

            if (line1.contains("//")) {
                line1 = line1.replace(line1.substring(line1.indexOf("//")), "");
            }
            if (line1.trim().startsWith("try{") || line1.trim().startsWith("}") || line1.trim().startsWith("else")|| line1.trim().startsWith("else{") || line1.trim().startsWith("}else{") || line1.isEmpty()|| line1.equals("") ||line1.equals("\\r?\\n")|| line1.trim().startsWith("else") || line1.trim().contains("class")) {
                Ci[i] = 0;
            }
            if((line1.trim().contains("System.out.println")) || (line1.trim().contains("System.out.print"))){
                Ci[i] = defaultValue;
            }
            Pattern pattern6 = Pattern.compile(multilineComment);
            Matcher matcher6 = pattern6.matcher(line1);
            while(matcher6.find()){
                Ci[i] = 0;
            }
            i++;
        }
        return Ci;
    }
}


