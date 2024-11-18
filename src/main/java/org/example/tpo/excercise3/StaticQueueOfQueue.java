package org.example.tpo.excercise3;

import org.example.adt.Queue;
import org.example.adt.StaticQueue;
import org.example.adt.StaticStack;

public class StaticQueueOfQueue implements QueueOfQueue {

    private static final int MAX = 10000; // Capacidad máxima de la QueueOfQueue
    private StaticQueue[] array; // Array de colas
    private int count;

    public StaticQueueOfQueue() {
        this.array = new StaticQueue[MAX];
        this.count = 0;
    }

    @Override
    public Queue getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("La QueueOfQueue está vacía");
        }
        return this.array[0];
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede eliminar una cola de una QueueOfQueue vacía");
        }
        // Mover todas las colas hacia la izquierda para mantener el orden
        for (int i = 0; i < count - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        count--;
    }

    @Override
    public void add(Queue queue) {
        if (queue == null) {
            throw new IllegalArgumentException("La cola proporcionada no puede ser null");
        }
        if (this.count == MAX) {
            throw new RuntimeException("La QueueOfQueue está llena");
        }

        if (!(queue instanceof StaticQueue)) {
            throw new IllegalArgumentException("Se esperaba una instancia de StaticQueue");
        }

        this.array[this.count] = (StaticQueue) queue;
        this.count++;
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

}
