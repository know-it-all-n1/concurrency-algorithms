package org.example.CriticalSectionProblem;

public class CriticalSection {
    private boolean occupied;
    private String process;

    public CriticalSection() {
        occupied = false;
    }

    public void occupy(String process) throws MutualExclusionInfringement {
        if (occupied) throw new MutualExclusionInfringement();
        occupied = true;
        this.process = process;
        System.out.println(process + " is occupying the critical section");
    }

    public void abandon() {
        System.out.println(process + " is exiting the critical section");
        occupied = false;
    }
}
