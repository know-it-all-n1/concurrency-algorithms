package org.example;

import org.example.CriticalSectionProblem.DekkerAlgorithm;
import org.example.CriticalSectionProblem.PetersonAlgorithm;

public class App {
    public static void main(String[] args) {
        // Dekker Algorithm
        DekkerAlgorithm dkAlgo = new DekkerAlgorithm();
        System.out.println("Testing Dekker Algorithm:\n");
        dkAlgo.run();

        // Peterson Algorithm
        PetersonAlgorithm petersonAlgo = new PetersonAlgorithm();
        System.out.println("Testing Peterson Algorithm:\n");
        petersonAlgo.run();
    }
}
