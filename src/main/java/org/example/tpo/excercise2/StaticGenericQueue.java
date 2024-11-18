package org.example.tpo.excercise2;

public class StaticGenericQueue<E> implements Queue<E> {

    private static final int MAX = 10000;
    private final E[] array;
    private int count;

    @SuppressWarnings("unchecked") // se agrega esta anotacion que evita que arroje advertencia cuando haces un nuevo new Object
    public StaticGenericQueue() {
        this.array = (E[]) new Object[MAX]; // Crear un array de tipo Objeto que sirve para cualquier tipo de clases
        this.count = 0;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede obtener el primero de una cola vacía");
        }
        return array[0];
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede remover el primero de una cola vacía");
        }
        for (int i = 0; i < count - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        count--;
    }

    @Override
    public void add(E value) {
        if (this.count == MAX) {
            throw new RuntimeException("La cola está llena");
        }
        this.array[this.count] = value;
        this.count++;
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }
}
