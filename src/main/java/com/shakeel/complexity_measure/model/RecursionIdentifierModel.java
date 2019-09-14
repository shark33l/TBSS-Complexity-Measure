package com.shakeel.complexity_measure.model;

public class RecursionIdentifierModel {

    private int lineNo;
    private String line;
    private String methodName;
    private String functionName;
    private Boolean isMethod;
    private Boolean visited;
    private String checkReadMethod;
    private int countCr;
    private int timesVisited;

//
//    public RecursionIdentifierModel(){
//        this.lineNo = 0;
//        this.line = "";
//        this.methodName = "";
//        this.functionName = "";
//        this.visited = false;
//        this.countCr = 0;
//    }

    public RecursionIdentifierModel(int lineNo, String line) {
        this.lineNo = lineNo;
        this.line = line;
        this.methodName = "";
        this.functionName = "";
        this.visited = false;
        this.countCr = 0;
        this.isMethod = false;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public int getCountCr() {
        return countCr;
    }

    public void setCountCr(int countCr) {
        this.countCr = countCr;
    }

    public Boolean getMethod() {
        return isMethod;
    }

    public void setMethod(Boolean method) {
        isMethod = method;
    }

    public String getCheckReadMethod() {
        return checkReadMethod;
    }

    public void setCheckReadMethod(String checkReadMethod) {
        this.checkReadMethod = checkReadMethod;
    }

    public int getTimesVisited() {
        return timesVisited;
    }

    public void setTimesVisited(int timesVisited) {
        this.timesVisited = timesVisited;
    }
}
