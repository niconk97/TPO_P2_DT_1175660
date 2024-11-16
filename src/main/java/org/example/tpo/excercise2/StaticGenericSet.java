package org.example.tpo.excercise2;

import java.util.Random;

public class StaticGenericSet<T> implements Set<T> {

    private static final int MAX = 10000;

    private final T[] array;
    private int count;
    private final Random random;

    @SuppressWarnings("unchecked") // se agrega esta anotacion que evita que arroje advertencia cuando haces un nuevo new Object
    public StaticGenericSet() {
        this.array = (T[]) new Object[MAX]; // Crear un array de tipo Objeto que sirve para cualquier tipo de clases
        this.count = 0;
        this.random = new Random();
    }

    @Override
    public void add(T value) {
        for (int i = 0; i < this.count; i++) {
            if (this.array[i].equals(value)) { // Verificar si el valor ya está en el conjunto
                return;
            }
        }
        if (this.count == MAX) {
            throw new RuntimeException("El conjunto está lleno");
        }
        this.array[this.count] = value;
        this.count++;
    }

    @Override
    public T choose() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede elegir un elemento de un conjunto vacío");
        }
        int i = random.nextInt(count);
        return this.array[i];
    }

    @Override
    public void remove(T value) {
        for (int i = 0; i < this.count; i++) {
            if (this.array[i].equals(value)) {
                this.array[i] = this.array[this.count - 1]; // Reemplazar con el último elemento
                this.count--;
                return; // Salir después de remover el valor
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }
}