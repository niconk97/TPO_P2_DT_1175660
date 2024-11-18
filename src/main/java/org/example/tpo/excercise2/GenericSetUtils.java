package org.example.tpo.excercise2;

public class GenericSetUtils {

    public static <E> Set<E> copy(Set<E> genericSet) {
        Set<E> aux = new StaticGenericSet<>();
        Set<E> aux2 = new StaticGenericSet<>();

        while (!genericSet.isEmpty()) {
            E element = genericSet.choose();
            aux.add(element);
            aux2.add(element);
            genericSet.remove(element);
        }

        while (!aux2.isEmpty()) {
            E element = aux2.choose();
            genericSet.add(element);
            aux2.remove(element);
        }

        return aux;
    }
}
