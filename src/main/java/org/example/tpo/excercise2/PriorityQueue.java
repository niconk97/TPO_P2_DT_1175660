package org.example.tpo.excercise2;

public interface PriorityQueue<P,V> {
    V getFirst(); // Obtener el primer elemento de la cola con prioridad
    P getPriority(); // Obtener la prioridad del primer elemento
    void remove(); // Remover el primer elemento
    void add(V value, P priority); // Agregar un valor con prioridad
    boolean isEmpty(); // Verificar si la cola está vacía
}

