package org.example.tpo.excercise2;

public interface Queue<T> {
    T getFirst(); // Obtener el primer elemento de la cola
    void remove(); // Remover el primer elemento de la cola
    void add(T value); // Agregar un nuevo elemento a la cola
    boolean isEmpty(); // Verificar si la cola está vacía
}


