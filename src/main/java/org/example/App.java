package org.example;

import org.example.CriticalSectionProblem.BakeryAlgorithm;
import org.example.CriticalSectionProblem.DekkerAlgorithm;
import org.example.CriticalSectionProblem.PetersonAlgorithm;

public class App {
    public static void main(String[] args) {
        // Dekker Algorithm
        DekkerAlgorithm dkAlgo = new DekkerAlgorithm();
        System.out.println("\nTesting Dekker Algorithm:\n");
        dkAlgo.run();

        // Peterson Algorithm
        PetersonAlgorithm petersonAlgo = new PetersonAlgorithm();
        System.out.println("\nTesting Peterson Algorithm:\n");
        petersonAlgo.run();

        // The Bakery Algorithm
        BakeryAlgorithm bakeryAlgo = new BakeryAlgorithm(50);
        System.out.println("\nTesting The Bakery Algorithm:\n");
        bakeryAlgo.run();
    }
}
