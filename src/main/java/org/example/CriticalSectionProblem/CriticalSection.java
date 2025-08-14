package org.example.CriticalSectionProblem;

public class CriticalSection {
    private boolean occupied;

    public CriticalSection() {
        occupied = false;
    }

    public synchronized void occupy(String process) throws MutualExclusionInfringement {
        if (occupied) throw new MutualExclusionInfringement();
        occupied = true;
        System.out.println(process + " is occupying the critical section");
    }

    public synchronized void abandon() {
        occupied = false;
    }
}
