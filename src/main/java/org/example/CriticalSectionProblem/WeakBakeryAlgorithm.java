package org.example.CriticalSectionProblem;

import java.util.Arrays;

public class WeakBakeryAlgorithm {
    private volatile int[] tickets;
    private volatile CriticalSection cs;
    private final int maxNumProcesses;

    public WeakBakeryAlgorithm(int maxNumProcesses) {
        tickets = new int[maxNumProcesses];
        cs = new CriticalSection();
        this.maxNumProcesses = maxNumProcesses;
    }

    public void run() {
        for (int i = 0; i < maxNumProcesses; i++) {
            new Thread(new Process(tickets, i, cs)).start();
        }
    }

    private class Process implements Runnable {
        private volatile int[] tickets;
        private final int processID;
        private volatile CriticalSection cs;

        public Process(int[] tickets, int processID, CriticalSection cs) {
            this.tickets = tickets;
            this.processID = processID;
            this.cs = cs;
        }

        @Override
        public void run() {
            try {
                // p1: ncs
                // Will wait for an arbitrary amount of time and the ask for
                // access to the critical section.
                Thread.sleep((long) (Math.random() * 5000));

                // p2:  number[i] ← 1 + max(number)
                tickets[processID] = 1 + Arrays.stream(tickets).max().orElse(0);

                // p3:  for j from 1 to N
                for (int j = 0; j < tickets.length; j++) {

                    // p4:     if j ≠ i
                    if (j != processID) {

                        // p5:        await (number[j] = 0) or (number[i] < number[j])  or
                        //                 ((number[i] = number[j]) and (i < j))
                        while (!((tickets[j] == 0)
                                || (tickets[processID] < tickets[j])
                                || ((tickets[processID] == tickets[j])
                                && (processID < j)
                        ))) {
                            Thread.sleep(1000);
                        }
                    }
                }

                // p6:  critical section
                cs.occupy(String.format("Process %d", processID));
                cs.abandon();

                // p7:  number[i] ← 0
                tickets[processID] = 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
