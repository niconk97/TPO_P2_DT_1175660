package org.example.tpo.excercise1;

import org.example.adt.Stack;

public interface QueueOfStacks {

    /**
     * Agrega una pila a la cola de pilas.
     *
     * @param stack La pila a agregar.
     *
     * Precondiciones:
     * - La pila proporcionada no debe ser null.
     * - La cola de pilas no debe estar llena si se usa una implementación estática con capacidad máxima.
     */
    void add(Stack stack);

    /**
     * Remueve y devuelve la pila al frente de la cola de pilas.
     *
     * @return La pila al frente de la cola.
     * @throws RuntimeException si la cola está vacía.
     */
    Stack remove();

    /**
     * Devuelve, sin remover, la pila al frente de la cola de pilas.
     *
     * @return La pila al frente de la cola.
     * @throws RuntimeException si la cola está vacía.
     */
    Stack getFirst();

    /**
     * Indica si la cola de pilas está vacía.
     *
     * @return true si está vacía, false de lo contrario.
     */
    boolean isEmpty();
}
