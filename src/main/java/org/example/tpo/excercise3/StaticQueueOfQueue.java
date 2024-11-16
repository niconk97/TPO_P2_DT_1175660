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

    @Override
    public QueueOfQueue concatenate(QueueOfQueue... queuesOfQueue) {
        StaticQueueOfQueue result = new StaticQueueOfQueue();

        // Agregar todas las colas de la instancia actual al resultado
        for (int i = 0; i < this.count; i++) {
            result.add(this.array[i]);
        }

        // Agregar las colas de cada QueueOfQueue pasada como parámetro
        for (QueueOfQueue queueOfQueue : queuesOfQueue) {
            if (queueOfQueue instanceof StaticQueueOfQueue) {
                StaticQueueOfQueue staticQueueOfQueue = (StaticQueueOfQueue) queueOfQueue;
                for (int i = 0; i < staticQueueOfQueue.count; i++) {
                    result.add(staticQueueOfQueue.array[i]);
                }
            } else {
                throw new IllegalArgumentException("Se esperaba una instancia de StaticQueueOfQueue");
            }
        }

        return result;
    }

    @Override
    public Queue flat() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("La QueueOfQueue no puede estar vacía");
        }

        StaticQueue result = new StaticQueue();
        StaticQueueOfQueue copiedQoq = new StaticQueueOfQueue();

        // Copiar la instancia interna de QueueOfQueue para no modificarla
        for (int i = 0; i < this.count; i++) {
            StaticQueue originalQueue = this.array[i];
            StaticQueue queueCopy = new StaticQueue();

            // Copiar los elementos de la cola actual
            while (!originalQueue.isEmpty()) {
                int value = originalQueue.getFirst();
                result.add(value); // Agregar a la cola de resultados
                queueCopy.add(value); // Copiar en la cola temporal
                originalQueue.remove(); // Remover el elemento de la cola original
            }

            // Restaurar la cola original y agregarla a la copia de QueueOfQueue
            copiedQoq.add(queueCopy);
        }

        // Restaurar el estado original de la QueueOfQueue
        this.array = copiedQoq.array;
        this.count = copiedQoq.count;

        return result;
    }

    @Override
    public QueueOfQueue reverseWithDepth() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("La QueueOfQueue no puede estar vacía");
        }

        // Crear una copia de la instancia original para no modificarla
        StaticQueueOfQueue copiedQoq = new StaticQueueOfQueue();
        for (int i = 0; i < this.count; i++) {
            StaticQueue originalQueue = this.array[i];
            StaticQueue queueCopy = new StaticQueue();

            // Usar una pila auxiliar para invertir la cola
            StaticStack stack = new StaticStack();
            StaticQueue tempQueue = new StaticQueue();

            while (!originalQueue.isEmpty()) {
                int value = originalQueue.getFirst();
                stack.add(value); // Agregar a la pila para invertir el orden
                queueCopy.add(value); // Mantener una copia para restaurar después
                originalQueue.remove();
            }

            // Restaurar la cola original
            while (!queueCopy.isEmpty()) {
                originalQueue.add(queueCopy.getFirst());
                queueCopy.remove();
            }

            // Crear una cola invertida utilizando la pila
            StaticQueue reversedQueue = new StaticQueue();
            while (!stack.isEmpty()) {
                reversedQueue.add(stack.getTop());
                stack.remove();
            }

            copiedQoq.add(reversedQueue);
        }

        // Invertir la QueueOfQueue copiada
        StaticQueueOfQueue reversedQoq = new StaticQueueOfQueue();
        for (int i = copiedQoq.count - 1; i >= 0; i--) {
            reversedQoq.add(copiedQoq.array[i]);
        }

        return reversedQoq;
    }

}
