package org.example.tpo.excercise2;

public interface Set<E> {

    /**
     *
     * @param element es el elemento a agregar, solo si no existe en el conjunto
     */
    void add(E element); // Agregar un elemento al conjunto si no existe

    E choose(); // Elegir un elemento aleatorio del conjunto

    /**
     *
     * @param element es el elemento a borrar, y si no existe en la estructura, no se hace nada.
     * @return
     */
    void remove(E element); // Remover un elemento del conjunto

    boolean isEmpty(); // Verificar si el conjunto está vacío
}
