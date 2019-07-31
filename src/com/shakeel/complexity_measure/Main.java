package com.shakeel.complexity_measure;

import com.shakeel.complexity_measure.identifiers.IfRecursionIdentifier;

public class Main {

    public static void main(String[] args) {
	// write your code here

        IfRecursionIdentifier ifRecursionIdentifier = new IfRecursionIdentifier();

        String codeLine = "if (6+1 == 7){{ a+b {7+1+}} ++++}";

        ifRecursionIdentifier.ifRecursionMethod(codeLine);

        System.out.println("Plus - " + ifRecursionIdentifier.getCountPlus());
        System.out.println("Brackets - " + ifRecursionIdentifier.getCountBrackets());


    }
}
