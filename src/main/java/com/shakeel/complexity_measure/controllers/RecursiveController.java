package com.shakeel.complexity_measure.controllers;

import com.shakeel.complexity_measure.identifiers.RecursionIdentifier;
import com.shakeel.complexity_measure.model.RecursionIdentifierModel;

import java.util.ArrayList;

public class RecursiveController {


    RecursionIdentifier recursionIdentifier = new RecursionIdentifier();
    String currentMethodName;

    public RecursionIdentifierModel recursionSetter(int lineNo, String codeLine, ArrayList<String> methodList){

        RecursionIdentifierModel recursionIdentifierModel = new RecursionIdentifierModel(lineNo, codeLine);
        recursionIdentifier.containsMethodCall(codeLine);

        //If its a method save it separately
        if(recursionIdentifier.getMethodFound()){
            recursionIdentifierModel.setMethodName(recursionIdentifier.getMethod());
            recursionIdentifierModel.setMethod(true);
            currentMethodName = recursionIdentifier.getMethod();
        }
        //If it's function save it separately
        else if(recursionIdentifier.getFunctionFound()){
            recursionIdentifierModel.setFunctionName(recursionIdentifier.getFunctionName());
            recursionIdentifierModel.setVisited(this.checkIfRecursive(methodList, recursionIdentifier.getFunctionName())); //Call recursive checker method
        }
        if(recursionIdentifier.getBrackets() > 0){
            recursionIdentifierModel.setCheckReadMethod(currentMethodName);
        }
        recursionIdentifierModel.setCountCr(recursionIdentifier.getBrackets());

        recursionIdentifier.ResetVariables();
        return recursionIdentifierModel;
    }


    //Check with listed methods if any of the methods are being called
    public boolean checkIfRecursive(ArrayList<String> methodList, String functionName){
        if(methodList.contains(functionName)){
            return true;
        } else {
            return false;
        }
    }
}
