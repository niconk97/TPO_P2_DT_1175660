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

    @Override
    public void add(Stack stack) {
        if (stack == null) {
            throw new IllegalArgumentException("La pila proporcionada no puede ser null");
        }
        if (this.count == matrixSize) {
            throw new RuntimeException("La cola de pilas está llena");
        }
        if (!(stack instanceof StaticStack)) {
            throw new IllegalArgumentException("La pila proporcionada debe ser una instancia de StaticStack");
        }

        // Verificar que la pila tenga exactamente n elementos
        if (StackUtil.stackSize(stack) != matrixSize) {
            throw new IllegalArgumentException("Cada pila debe tener exactamente " + matrixSize + " elementos para que la matriz sea cuadrada");
        }

        this.array[this.count] = (StaticStack) stack;
        this.count++;
    }

    @Override
    public Stack remove() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede eliminar una pila de una cola vacía");
        }
        Stack removedStack = this.array[0];
        for (int i = 0; i < this.count - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.count--;
        return removedStack;
    }

    @Override
    public Stack getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede obtener la primera pila de una cola vacía");
        }
        return this.array[0];
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }
}
