package org.example.tpo.excercise3;

import org.example.adt.Queue;

public interface QueueOfQueue {
    Queue getFirst();
    void remove();
    void add(Queue queue);
    boolean isEmpty();
}
