package org.example.tpo.excercise1;

import org.example.adt.StaticStack;
import org.example.adt.Stack;
import org.example.utils.StackUtil;

    public class StaticQueueOfStacks implements QueueOfStacks {

    private final int matrixSize; // Define el tamaño de la matriz (n x n)
    private final StaticStack[] array;
    private int count;

    public StaticQueueOfStacks(int matrixSize) {
        if (matrixSize <= 0) {
            throw new IllegalArgumentException("El tamaño n debe ser mayor a 0");
        }
        this.matrixSize = matrixSize; // Tamaño de la matriz cuadrada
        this.array = new StaticStack[matrixSize];
        this.count = 0;
    }

    /**
     * Agrega una pila a la cola de pilas.
     * Complejidad: O(n), donde n es el número de elementos en la pila proporcionada.
     */
    @Override
    public void add(Stack stack) {
        if (stack == null) { // O(1)
            throw new IllegalArgumentException("La pila proporcionada no puede ser null");
        }
        if (this.count == matrixSize) { // O(1)
            throw new RuntimeException("La cola de pilas está llena");
        }
        if (!(stack instanceof StaticStack)) { // O(1)
            throw new IllegalArgumentException("La pila proporcionada debe ser una instancia de StaticStack");
        }

        // Verificar que la pila tenga exactamente n elementos
        if (StackUtil.stackSize(stack) != matrixSize) { // O(n) - Complejidad lineal, se verifica elemento a elemento y se cuentan.
            throw new IllegalArgumentException("Cada pila debe tener exactamente " + matrixSize + " elementos para que la matriz sea cuadrada");
        }

        this.array[this.count] = (StaticStack) stack; // O(1)
        this.count++; // O(1)
    }

    /**
     * Elimina la primera pila de la cola.
     * Complejidad: O(n), donde n es el número de elementos en la cola.
     */
    @Override
    public Stack remove() { // O(n)
        if (isEmpty()) { // O(1)
            throw new RuntimeException("No se puede eliminar una pila de una cola vacía");
        }
        Stack removedStack = this.array[0];
        for (int i = 0; i < this.count - 1; i++) { // O(n), se realizan desplazamientos de pilas y depende de la cantidad que haya.
            this.array[i] = this.array[i + 1]; // O(1)
        }
        this.count--; // O(1)
        return removedStack;
    }

    /**
     * Obtiene la primera pila de la cola sin eliminarla.
     * Complejidad: O(1)
     */
    @Override
    public Stack getFirst() { // O(1)
        if (isEmpty()) { // O(1)
            throw new RuntimeException("No se puede obtener la primera pila de una cola vacía");
        }
        return this.array[0]; // O(1)
    }

    /**
     * Verifica si la cola de pilas está vacía.
     * Complejidad: O(1)
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0; // O(1)
    }

    /**
     * Devuelve el tamaño actual de la cola de pilas.
     * Complejidad: O(1)
     */
    @Override
    public int size() {
        return this.count;
    } // O(1)
}
