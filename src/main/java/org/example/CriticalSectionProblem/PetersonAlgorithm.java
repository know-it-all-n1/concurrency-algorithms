package org.example.CriticalSectionProblem;

public class PetersonAlgorithm {
    private boolean wantp;
    private boolean wantq;
    private int last;
    private CriticalSection cs;

    public PetersonAlgorithm() {
        this.last = 1;
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
                    last = 1;

                    // await wantq = false or last = 2
                    while (wantq && last != 2) {
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
                    last = 2;

                    // await wantp = false or last = 1
                    while (wantp && last != 1) {
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
