package org.example.tpo.excercise1;

import org.example.adt.StaticStack;
import org.example.adt.Stack;

public class StaticQueueOfStacks implements QueueOfStacks {

    private static final int MAX = 100; // Limite de elementos de tipo pila que usa la cola
    private final StaticStack[] array;
    private int count;

    public StaticQueueOfStacks() {
        this.array = new StaticStack[MAX];
        this.count = 0;
    }

    @Override
    public void addStack(Stack stack) {
        if (stack == null) {
            throw new IllegalArgumentException("La pila proporcionada no puede ser null");
        }
        if (this.count == MAX) {
            throw new RuntimeException("La cola de pilas está llena");
        }
        if (!(stack instanceof StaticStack)) {
            throw new IllegalArgumentException("La pila proporcionada debe ser una instancia de StaticStack");
        }
        this.array[this.count] = (StaticStack) stack;
        this.count++;
    }


    @Override
    public Stack removeStack() {
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
    public Stack peekStack() { // es basicamente un getFirst
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
