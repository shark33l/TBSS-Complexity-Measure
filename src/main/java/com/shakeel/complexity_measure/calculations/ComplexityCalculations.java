package com.shakeel.complexity_measure.calculations;

public class ComplexityCalculations {

    public int totalComplexity(int ctc, int cnc, int ci){
        return (ctc + cnc + ci);
    }

    public int complexityStatementCps(int tw, int cs){
        return tw * cs;
    }

    public int complexityProgramCp(int cps, int cr){
        return  cps + cr;
    }
}
