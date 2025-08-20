package org.example.CriticalSectionProblem;

import java.util.Arrays;

public class BakeryAlgorithm {
    private volatile int[] tickets;
    private volatile boolean[] choosing;

    private volatile CriticalSection cs;
    private final int maxNumProcesses;

    public BakeryAlgorithm(int maxNumProcesses) {
        tickets = new int[maxNumProcesses];
        choosing = new boolean[maxNumProcesses];
        cs = new CriticalSection();
        this.maxNumProcesses = maxNumProcesses;
    }

    public void run() {
        for (int i = 0; i < maxNumProcesses; i++) {
            new Thread(new Process(tickets, i, cs, choosing)).start();
        }
    }

    private class Process implements Runnable {
        private volatile int[] tickets;
        private final int processID;
        private volatile CriticalSection cs;
        private volatile boolean[] choosing;

        public Process(int[] tickets, int processID, CriticalSection cs, boolean[] choosing) {
            this.tickets = tickets;
            this.processID = processID;
            this.cs = cs;
            this.choosing = choosing;
        }

        @Override
        public void run() {
            try {
                // p1: ncs
                // Will wait for an arbitrary amount of time and the ask for
                // access to the critical section.
                Thread.sleep((long) (Math.random() * 5000));

                // p2:  choosing[i] ← true
                choosing[processID] = true;

                // p3:  number[i] ← 1 + max(number)
                tickets[processID] = 1 + Arrays.stream(tickets).max().orElse(0);

                // p4:  choosing[i] ← false
                choosing[processID] = false;

                // p5:  for j from 1 to N
                for (int j = 0; j < tickets.length; j++) {

                    // p6:     if j ≠ i
                    if (j != processID) {

                        // p7:        await choosing[j] = false
                        while (choosing[j]) {
                            Thread.sleep(1000);
                        }

                        // p8:        await (number[j] = 0) or (number[i] < number[j]) or
                        //                ((number[i] = number[j]) and (i < j))
                        while (!((tickets[j] == 0)
                                || (tickets[processID] < tickets[j])
                                || ((tickets[processID] == tickets[j])
                                && (processID < j)
                        ))) {
                            Thread.sleep(1000);
                        }
                    }
                }

                // p9:  critical section
                cs.occupy(String.format("Process %d", processID));
                cs.abandon();

                // p10:  number[i] ← 0
                tickets[processID] = 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
