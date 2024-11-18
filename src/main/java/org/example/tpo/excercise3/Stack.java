package org.example.tpo.excercise3;

public interface Stack<E> {
    /**
     * Precondición: La pila no puede estar vacía
     * @return Obtener el elemento de la parte superior
     */
    E getTop(); // Obtener el elemento de la parte superior

    /**
     * Precondición: La pila no puede estar vacía
     */

    void remove(); // Remover el elemento de la parte superior
    void add(E element); // Agregar un elemento a la parte superior de la pila
    boolean isEmpty(); // Verificar si la pila está vacía
}

