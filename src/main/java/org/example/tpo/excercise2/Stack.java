package org.example.tpo.excercise2;

public interface Stack<T> {
    /**
     * Precondición: La pila no puede estar vacía
     * @return Obtener el elemento de la parte superior
     */
    T getTop(); // Obtener el elemento de la parte superior

    /**
     * Precondición: La pila no puede estar vacía
     */

    void remove(); // Remover el elemento de la parte superior
    void add(T value); // Agregar un elemento a la parte superior de la pila
    boolean isEmpty(); // Verificar si la pila está vacía
}

