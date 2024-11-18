package org.example.tpo.excercise3;

public class StaticGenericStack<E> implements Stack<E> {

    private static final int MAX = 10000;
    private final E[] array;
    private int count;

    @SuppressWarnings("unchecked") // se agrega esta anotacion que evita que arroje advertencia cuando haces un nuevo new Object
    public StaticGenericStack() {
        this.array = (E[]) new Object[MAX]; // Crear un array de tipo Objeto que sirve para cualquier tipo de clases
        this.count = 0;
    }

    @Override
    public E getTop() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede obtener el tope de una pila vacía");
        }
        return array[this.count - 1];
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new RuntimeException("No se puede remover el tope de una pila vacía");
        }
        count--;
    }

    @Override
    public void add(E element) {
        if (this.count == MAX) {
            throw new RuntimeException("La pila está llena");
        }
        this.array[this.count] = element;
        this.count++;
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }
}

