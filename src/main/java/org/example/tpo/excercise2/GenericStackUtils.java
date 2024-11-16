package org.example.tpo.excercise2;

public class GenericStackUtils {

    public static <T> Stack<T> copy(Stack<T> originalStack) {
        Stack<T> aux = new StaticGenericStack<>();
        Stack<T> aux2 = new StaticGenericStack<>();

        while (!originalStack.isEmpty()) {
            T element = originalStack.getTop();
            aux.add(element);
            originalStack.remove();
        }

        while (!aux.isEmpty()) {
            T element = aux.getTop();
            originalStack.add(element);
            aux2.add(element);
            aux.remove();
        }

        return aux2; // La copia del `originalStack` en su orden original
    }

    public static <T> Stack<T> revert(Stack<T> stack) {
        Stack<T> copy = copy(stack); // Hacemos una copia del stack original
        Stack<T> reverse = new StaticGenericStack<>(); // Creamos un nuevo stack para el resultado

        // Invertir los elementos
        while (!copy.isEmpty()) {
            reverse.add(copy.getTop());
            copy.remove();
        }

        return reverse;
    }
}
