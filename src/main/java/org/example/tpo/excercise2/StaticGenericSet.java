package org.example.tpo.excercise2;

import java.util.Random;

public class StaticGenericSet<E> implements Set<E> {

    private static final int MAX = 10000;

    private final E[] array;
    private int count;
    private final Random random;

    @SuppressWarnings("unchecked") // se agrega esta anotacion que evita que arroje advertencia cuando haces un nuevo new Object
    public StaticGenericSet() {
        this.array = (E[]) new Object[MAX]; // Crear un array de tipo Objeto que sirve para cualquier tipo de clases
        this.count = 0;
        this.random = new Random();
    }

    @Override
    public void add(E element) {
        for (int i = 0; i < this.count; i++) {
            if (this.array[i].equals(element)) { // Verificar si el elemento ya está en el conjunto
                return;
            }
        }
        if (this.count == MAX) {
            throw new RuntimeException("El conjunto está lleno");
        }
        this.array[this.count] = element;
        this.count++;
    }

    @Override
    public E choose() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede elegir un elemento de un conjunto vacío");
        }
        int i = random.nextInt(count);
        return this.array[i];
    }

    @Override
    public void remove(E element) {
        for (int i = 0; i < this.count; i++) {
            if (this.array[i].equals(element)) {
                this.array[i] = this.array[this.count - 1]; // Reemplazar con el último elemento
                this.count--;
                return; // Salir después de remover el elemento
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }
}