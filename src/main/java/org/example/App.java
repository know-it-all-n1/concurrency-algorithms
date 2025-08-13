package org.example;

import org.example.CriticalSectionProblem.DekkerAlgorithm;

public class App {
    public static void main(String[] args) {
        // Dekker Algorithm
        DekkerAlgorithm dkAlgo = new DekkerAlgorithm();
        System.out.println("Testing Dekker Algorithm:\n");
        dkAlgo.run();
    }
}
