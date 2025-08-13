# concurrency-algorithms

## What is this?

This is a project to study and try the most common concurrency algorithms.
I took a course in Advanced Programming and Concurrency in my sophomore year,
which I didn't like that much 😅, but I was really interested in the topic, so
I've decided to continue studying it by my own.

As main resource I am using the book: _Principles of Concurrent and Distributed
Programming, Second Edition_, by **M. Ben-Ari**.

**_Note_:** The main problem with concurrent programming is synchronisation
and communication between concurrent processes. The book focuses in the critical
section problem, which is one of
the main problems in concurrent programming. Therefore, the algorithms are
focused on solving the critical section problem.

## Algorithms

### Dekker Algorithm

Is the first algorithm shown in the book that solves the Critical Section
problem, complying with the 3 correctness specifications:

- Mutual exclusion
- Freedom from deadlock
- Freedom from starvation