package com.shakeel.complexity_measure.identifiers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecursionIdentifier {

    //Regex for to find methods or functions
    public static final Pattern METHOD_PARENTHESES_REGEX = Pattern
            .compile("(?U)([.\\w]+)\\s*\\((.*)\\)");


    //Variable to save method & function
    String method;
    String functionName;

    //Check variables
    Boolean methodFound;
    Boolean functionFound;

    public RecursionIdentifier() {

        this.method = "";
        this.functionName = "";
        this.methodFound = false;
        this.functionFound = false;

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Boolean getMethodFound() {
        return methodFound;
    }

    public void setMethodFound(Boolean methodFound) {
        this.methodFound = methodFound;
    }

    public Boolean getFunctionFound() {
        return functionFound;
    }

    public void setFunctionFound(Boolean functionFound) {
        this.functionFound = functionFound;
    }

    //After these Java keywords may come an opening parenthesis.
    private static List<String> keyWordsBeforeParens =
            Arrays.asList(
                    "while",
                    "for",
                    "if",
                    "try",
                    "catch",
                    "switch");

    //Java method keywords prefixes
    private static String[] methodKeywords =
            {
                    "public",
                    "private",
                    "protected",
                    "abstract",
                    "final",
                    "static",
                    "native",
                    "transient",
                    "{"};

    //Method needs a single param of string which needs to be passed
    public boolean containsMethodCall(final String codeLine) {
        final Matcher matcher = METHOD_PARENTHESES_REGEX.matcher(codeLine);

        String[] methodNameArray;
        String methodName;
        String objectName;

        while (matcher.find()) {
            final String beforeParens = matcher.group(1);
            final String insideParens = matcher.group(2);

            if (keyWordsBeforeParens.contains(beforeParens)) {
                System.out.println("Keyword: " + beforeParens);
                return containsMethodCall(insideParens);
            } else {
                //If method contains and object before method, it will be split separately into objects and methods
                if(beforeParens.contains(".")){
                    methodNameArray = beforeParens.split("\\.");
                    methodName = methodNameArray[methodNameArray.length - 1];
                    objectName = methodNameArray[methodNameArray.length -2];

                } else {
                    methodName = beforeParens;
                    objectName = "No Objects found.";
                }

                if(Arrays.stream(methodKeywords).anyMatch(codeLine::contains)){

                    System.out.println("Method Name : " + methodName);
                    setMethodFound(true);
                    setMethod(methodName);


                } else {

                    System.out.println("Function name: " + methodName + "  |  Object Name : " + objectName);
                    setFunctionFound(true);
                    setFunctionName(methodName);

                }
                return true;
            }
        }
        return false;
    }

    public void ResetVariables(){
        this.setFunctionName("");
        this.setMethod("");
        this.setFunctionFound(false);
        this.setMethodFound(false);
    }
}
