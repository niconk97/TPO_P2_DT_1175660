package org.example.tpo.excercise2;

public class StaticGenericQueue<T> implements Queue<T> {

    private static final int MAX = 10000;
    private final T[] array;
    private int count;

    @SuppressWarnings("unchecked") // se agrega esta anotacion que evita que arroje advertencia cuando haces un nuevo new Object
    public StaticGenericQueue() {
        this.array = (T[]) new Object[MAX]; // Crear un array de tipo Objeto que sirve para cualquier tipo de clases
        this.count = 0;
    }

    @Override
    public T getFirst() {
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
    public void add(T value) {
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
