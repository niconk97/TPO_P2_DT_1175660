package org.example.tpo.excercise2;

public class GenericSetUtils {

    public static <T> Set<T> copy(Set<T> genericSet) {
        Set<T> aux = new StaticGenericSet<>();
        Set<T> aux2 = new StaticGenericSet<>();

        while (!genericSet.isEmpty()) {
            T element = genericSet.choose();
            aux.add(element);
            aux2.add(element);
            genericSet.remove(element);
        }

        while (!aux2.isEmpty()) {
            T element = aux2.choose();
            genericSet.add(element);
            aux2.remove(element);
        }

        return aux;
    }
}
