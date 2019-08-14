package com.shakeel.complexity_measure.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InheritanceController {
//    static int ci_constant = 1;
//
//    //String extends_regex ="((?<!\\S)(extends\\s*))";
//    String extends_regex ="((?<!\\S)(extends\\s*))";
//
   public void measureInheritanceComplexity() throws Exception{

      FileReader file=new FileReader("C:/Users/sasmini_112033/Documents/SPM/InheritanceCode.txt");
      BufferedReader reader=new BufferedReader(file);

       int lines = 0;
       while (reader.readLine() != null)
           lines++;
       reader.close();

      //int arraySize = countLines(reader);
      System.out.println(lines);

      int ciArray[] = printCi(lines,"java");
      for(int i=0; i<lines; i++){
          System.out.println(ciArray[i]);
      }
   }

//
//        String CurrentLine="";
//        String line;
//
//        while((line = reader.readLine())!= null) {
//
//            //line=reader.readLine();
//            //System.out.println(line);
//            CurrentLine=line;
//
//            //CurrentLine = CurrentLine.replace(" ","");
//
//            //detectChar(CurrentLine,ch);
//            extends_count(CurrentLine);
//
//        }
//
//        //System.out.println(this.Cs);
//
//    }
//
//    public int extends_count(String line) {
//
//
//        Pattern pattern = Pattern.compile(extends_regex);
//        Matcher matcher = pattern.matcher(line);
//
//
//        int count = 0;
//        while (matcher.find()) {
//            count++;
//        }
//
//        int ci_total = count + ci_constant + 1;
//
//
//        System.out.println("Ci :" + ci_total);
//        return ci_total;
//    }

    public int countLines(String code) throws Exception{

        FileReader file=new FileReader("C:/Users/sasmini_112033/Documents/SPM/InheritanceCode.txt");
        BufferedReader reader=new BufferedReader(file);

        int lines = 0;
        while (reader.readLine() != null)
            lines++;
        reader.close();
        return lines;
    }

    public int[] printCi(int Size, String Type) throws Exception{
        FileReader file=new FileReader("C:/Users/sasmini_112033/Documents/SPM/InheritanceCode.txt");
        BufferedReader reader=new BufferedReader(file);

        int arraySize = Size;

        System.out.println(Size);

        int Ci[] = new int[arraySize];

        int defaultValue = 0;

        if(Type == "java"){
           defaultValue = 1;

        }else{
            defaultValue = 0;
        }

        String[] lines = reader.readLine().split("\\r?\\n");
        int i=0;

        for(String line : lines){

            System.out.println(line);

            if(line.contains("class ")){
                defaultValue = defaultValue +1;
            }
            if(line.contains("extends ")){
                defaultValue = defaultValue +1;
            }
            if(line.contains("implements ")){
                defaultValue = defaultValue +1;
            }
        }

        for( i = 0 ; i<arraySize ; i++){
            Ci[i] = defaultValue;
        }

        int z = 0;

        for(String line : lines){

            if(line.contains("class ")){
                Ci[z] = 0;
            }
            else if(line.equals("else { ")){
                Ci[z] = 0;
            }else if(line.equals("}")){
                Ci[z] = 0;
            }
            z = z + 1;
        }
        return Ci;
    }
}
