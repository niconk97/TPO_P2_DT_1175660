package org.example.tpo.excercise2;

public class GenericStackUtils {

    public static <E> Stack<E> copy(Stack<E> originalStack) {
        Stack<E> aux = new StaticGenericStack<>();
        Stack<E> aux2 = new StaticGenericStack<>();

        while (!originalStack.isEmpty()) {
            E element = originalStack.getTop();
            aux.add(element);
            originalStack.remove();
        }

        while (!aux.isEmpty()) {
            E element = aux.getTop();
            originalStack.add(element);
            aux2.add(element);
            aux.remove();
        }

        return aux2; // La copia del `originalStack` en su orden original
    }

    public static <E> Stack<E> revert(Stack<E> stack) {
        Stack<E> copy = copy(stack); // Hacemos una copia del stack original
        Stack<E> reverse = new StaticGenericStack<>(); // Creamos un nuevo stack para el resultado

        // Invertir los elementos
        while (!copy.isEmpty()) {
            reverse.add(copy.getTop());
            copy.remove();
        }

        return reverse;
    }
}
