package org.example.tpo.excercise2;

public class StaticGenericPriorityQueue<P extends Comparable<P>, V> implements PriorityQueue<P, V> {

    private static final int MAX = 10000;

    private final V[] values;
    private final P[] priorities;
    private int count;

    @SuppressWarnings("unchecked") // se agrega esta anotacion que evita que arroje advertencia cuando haces un nuevo new Object
    public StaticGenericPriorityQueue() {
        this.values = (V[]) new Object[MAX]; // Crear un array de tipo Objeto que sirve para cualquier tipo de clases
        this.priorities = (P[]) new Object[MAX]; // array para las prioridades simil a values
        this.count = 0;
    }

    @Override
    public V getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede obtener el primero de una cola vacía");
        }
        return values[0];
    }

    @Override
    public P getPriority() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede obtener la prioridad de una cola vacía");
        }
        return priorities[0];
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede eliminar el elemento de una cola vacía");
        }
        for (int i = 0; i < count - 1; i++) {
            this.values[i] = this.values[i + 1];
            this.priorities[i] = this.priorities[i + 1];
        }
        count--;
    }

    @Override
    public void add(V value, P priority) {
        if (this.count == MAX) {
            throw new RuntimeException("La cola está llena");
        }

        if (this.isEmpty()) {
            this.values[0] = value;
            this.priorities[0] = priority;
            this.count++;
            return;
        }

        if (this.priorities[count - 1].compareTo(priority) <= 0) {
            this.values[count] = value;
            this.priorities[count] = priority;
            this.count++;
            return;
        }

        if (this.priorities[0].compareTo(priority) > 0) {
            for (int i = this.count; i > 0; i--) {
                this.values[i] = this.values[i - 1];
                this.priorities[i] = this.priorities[i - 1];
            }
            this.values[0] = value;
            this.priorities[0] = priority;
            this.count++;
            return;
        }

        int index = -1;
        for (int i = 1; i < this.count; i++) {
            if (this.priorities[i - 1].compareTo(priority) <= 0 && this.priorities[i].compareTo(priority) > 0) {
                index = i;
                break;
            }
        }
        for (int i = this.count; i > index; i--) {
            this.values[i] = this.values[i - 1];
            this.priorities[i] = this.priorities[i - 1];
        }

        this.values[index] = value;
        this.priorities[index] = priority;
        this.count++;
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }
}
