package org.example.CriticalSectionProblem;

/**
 * Implementation of the Dekker algorithm with demonstration purposes<br>
 *
 * <b>Note:</b> await statements in the original design of the algorithm have been
 * implemented as while loops.
 */
public class DekkerAlgorithm {
    private boolean wantp;
    private boolean wantq;
    private Turn turn;
    private CriticalSection cs;

    public DekkerAlgorithm() {
        this.turn = new Turn(1);
        wantp = false;
        wantq = false;
        cs = new CriticalSection();
    }

    public void run() {
        Thread p = new Thread(new ProcessP());
        Thread q = new Thread(new ProcessQ());

        p.start();
        q.start();
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

                    // while wantq
                    while (wantq) {

                        // if turn = 2
                        if (turn.getTurn() == 2) {

                            // wantp <- false
                            wantp = false;

                            // await turn = 1
                            while (turn.getTurn() != 1) {
                                Thread.sleep(500);
                            }

                            // wantp <- true
                            wantp = true;
                        }
                    }

                    // critical section
                    cs.occupy("Process P");
                    cs.abandon();

                    // turn <- 2
                    turn.setTurn(2);

                    // wantp <- false
                    wantp = false;
                } catch (InterruptedException e) {
                    // IGNORED
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

                    // while wantq
                    while (wantp) {

                        // if turn = 1
                        if (turn.getTurn() == 1) {

                            // wantq <- false
                            wantq = false;

                            // await turn = 1
                            while (turn.getTurn() != 2) {
                                Thread.sleep(500);
                            }

                            // wantq <- true
                            wantq = true;
                        }
                    }

                    // critical section
                    cs.occupy("Process Q");
                    cs.abandon();

                    // turn <- 1
                    turn.setTurn(1);

                    // wantq <- false
                    wantq = false;
                } catch (InterruptedException e) {
                    // IGNORED
                }

            }
        }
    }

    private class Turn {
        private int turn;

        public Turn(int turn) {
            this.turn = turn;
        }

        public synchronized int getTurn() {
            return turn;
        }

        public synchronized void setTurn(int turn) {
            this.turn = turn;
        }
    }
}
