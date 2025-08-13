package org.example.CriticalSectionProblem;

import org.example.CriticalSectionProblem.Utilities.SharableInteger;

public class PetersonAlgorithm {
    private boolean wantp;
    private boolean wantq;
    private SharableInteger last;
    private CriticalSection cs;

    public PetersonAlgorithm() {
        this.last = new SharableInteger(1);
        wantp = false;
        wantq = false;
        cs = new CriticalSection();
    }

    public void run() {
        Thread p = new Thread(new ProcessP());
        Thread q = new Thread(new ProcessQ());

        p.start();
        q.start();

        try {
            p.join();
            q.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private class ProcessP implements Runnable {
        @Override
        public void run() {
            // Instead of an infinite loop we will use a finite loop
            // for demonstration purposes
            // loop "forever"
            for (int i = 0; i < 5; i++) {
                try {
                    // non-critical section
                    System.out.println("Process P in the NCS");
                    Thread.sleep(1000);

                    // wantp <- true
                    wantp = true;

                    // last <- 1
                    last.setValue(1);

                    // await wantq = false or last = 2
                    while (wantq && last.getValue() != 2) {
                        Thread.sleep(500);
                    }

                    // critical section
                    cs.occupy("Process P");
                    cs.abandon();

                    // wantp <- false
                    wantp = false;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    private class ProcessQ implements Runnable {
        @Override
        public void run() {
            // Instead of an infinite loop we will use a finite loop
            // for demonstration purposes
            // loop "forever"
            for (int i = 0; i < 5; i++) {
                try {
                    // non-critical section
                    System.out.println("Process Q in the NCS");
                    Thread.sleep(1000);

                    // wantq <- true
                    wantq = true;

                    // last <- 1
                    last.setValue(2);

                    // await wantp = false or last = 1
                    while (wantp && last.getValue() != 1) {
                        Thread.sleep(500);
                    }

                    // critical section
                    cs.occupy("Process Q");
                    cs.abandon();

                    // wantp <- false
                    wantp = false;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
