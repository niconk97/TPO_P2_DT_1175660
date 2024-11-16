package org.example.tpo.excercise2;

public interface Set<T> {

    /**
     *
     * @param value es el valor a agrega, solo si no existe en el conjunto
     */
    void add(T value); // Agregar un valor al conjunto si no existe

    T choose(); // Elegir un elemento aleatorio del conjunto

    /**
     *
     * @param value es el elemento a borrar, y si no existe en la estructura, no se hace nada.
     * @return
     */
    void remove(T value); // Remover un valor del conjunto

    boolean isEmpty(); // Verificar si el conjunto está vacío
}
