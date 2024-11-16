package org.example.tpo.excercise2;

public interface PriorityQueue<T> {
    T getFirst(); // Obtener el primer elemento de la cola con prioridad
    int getPriority(); // Obtener la prioridad del primer elemento
    void remove(); // Remover el primer elemento
    void add(T value, int priority); // Agregar un valor con prioridad
    boolean isEmpty(); // Verificar si la cola está vacía
}

