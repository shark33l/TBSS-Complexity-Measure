package com.shakeel.complexity_measure.model;

public class ComplexityModel {

    private String fileType;
    private String fileName;
    private String filePath;
    private String className;
    private int lineNo;
    private String line;
    private String methodName;
    private String functionName;
    private Boolean isRecursive;
    private int timesVisited;
    private int countCr;
    private String csToken;
    private int countCs;
    private int countCtc;
    private int countCnc;
    private int countCi;
    private int countTW;
    private int countCps;
    private int countCp;

    public ComplexityModel() {
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Boolean getRecursive() {
        return isRecursive;
    }

    public void setRecursive(Boolean recursive) {
        isRecursive = recursive;
    }

    public int getTimesVisited() {
        return timesVisited;
    }

    public void setTimesVisited(int timesVisited) {
        this.timesVisited = timesVisited;
    }

    public int getCountCr() {
        return countCr;
    }

    public void setCountCr(int countCr) {
        this.countCr = countCr;
    }

    public String getCsToken() {
        return csToken;
    }

    public void setCsToken(String csToken) {
        this.csToken = csToken;
    }

    public int getCountCs() {
        return countCs;
    }

    public void setCountCs(int countCs) {
        this.countCs = countCs;
    }

    public int getCountCtc() {
        return countCtc;
    }

    public void setCountCtc(int countCtc) {
        this.countCtc = countCtc;
    }

    public int getCountCnc() {
        return countCnc;
    }

    public void setCountCnc(int countCnc) {
        this.countCnc = countCnc;
    }

    public int getCountCi() {
        return countCi;
    }

    public void setCountCi(int countCi) {
        this.countCi = countCi;
    }

    public int getCountTW() {
        return countTW;
    }

    public void setCountTW(int countTW) {
        this.countTW = countTW;
    }

    public int getCountCps() {
        return countCps;
    }

    public void setCountCps(int countCps) {
        this.countCps = countCps;
    }

    public int getCountCp() {
        return countCp;
    }

    public void setCountCp(int countCp) {
        this.countCp = countCp;
    }
}
