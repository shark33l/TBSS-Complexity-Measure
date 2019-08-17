package com.shakeel.complexity_measure.model;

public class RecursionIdentifierModel {

    private int lineNo;
    private String line;
    private String methodName;
    private Boolean visited;
    private int countCr;

    public RecursionIdentifierModel(int lineNo, String line) {
        this.lineNo = lineNo;
        this.line = line;
        this.methodName = "";
        this.visited = false;
        this.countCr = 0;
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
}
