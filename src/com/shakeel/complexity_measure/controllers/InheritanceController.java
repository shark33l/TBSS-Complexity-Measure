package com.shakeel.complexity_measure.controllers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class InheritanceController {
    public static final String SOURCE_CODE_PATH = "C:/Users/sasmini_112033/Documents/SPM/TBSS-Complexity-Measure/code.java";
    public static final String TYPE = "java";

   public void measureInheritanceComplexity() throws Exception{

      FileReader file=new FileReader(SOURCE_CODE_PATH);
      BufferedReader reader=new BufferedReader(file);


       int lines = 0;
       while (reader.readLine() != null)
           lines++;
       reader.close();


      System.out.println("Number of lines: "+lines);


      int ciArray[] = printCi(lines, TYPE);
      for(int i=0; i < lines; i++){
          System.out.println("Ci " +"line " + (i+1) + ": " + ciArray[i]);
      }
   }

    public int countLines(String code) throws Exception{

        FileReader file=new FileReader(SOURCE_CODE_PATH);
        BufferedReader reader=new BufferedReader(file);



        int lines = 0;
        while (reader.readLine() != null)
            lines++;

        reader.close();
        return lines;
    }

    public int[] printCi(int Size, String Type) throws Exception{


        File file=new File(SOURCE_CODE_PATH);
        Scanner sc = new Scanner(file);

        File file1=new File(SOURCE_CODE_PATH);
        Scanner sc1 = new Scanner(file1);

        int arraySize = Size;

        //System.out.println(Size);

        int Ci[] = new int[arraySize];

        int defaultValue = 0;

        if(Type == "java"){
           defaultValue = 1;

        }else{
            defaultValue = 0;
        }

        int i=0;

        while(sc.hasNextLine()){
            String line = sc.nextLine();

                if (line.contains("class ")) {
                    defaultValue = defaultValue + 1;
                }
                if (line.contains("extends ")) {
                    defaultValue = defaultValue + 1;
                }
                if (line.contains("implements ")) {
                    defaultValue = defaultValue + 1;
                }
        }

        for( int z = 0 ; z<arraySize ; z++){
            Ci[z] = defaultValue;
        }

        int temp = defaultValue;

        while(sc1.hasNextLine()){
            String line1 = sc1.nextLine();

            if(line1.contains("class ") || line1.contains("} ") || line1.equals("}") || line1.equals(" }") || line1.equals("\\r?\\n") || line1.equals("") || line1.equals("else {") || line1.equals("else{") || line1.equals("// ")){
                Ci[i] = 0;
            }
            i++;
        }
      return Ci;
    }


}
