package org.example.tpo.excercise1;

import org.example.adt.Stack;
import org.example.adt.StaticStack;
import org.example.utils.StackUtil;

public class QueueOfStacksUtils {

    public static int calculateTrace(QueueOfStacks qos) {
        int trace = 0; // La traza se define como la suma de los elementos de la diagonal principal
        QueueOfStacks copiedQueue = QueueOfStacksUtils.copy(qos); // Crear una copia de la cola original
        int index = 0;

        while (!copiedQueue.isEmpty()) {
            Stack stack = copiedQueue.removeStack(); // Obtener la pila en la posición actual
            Stack copiedStack = StackUtil.copy(stack); // Copiar la pila para preservar el estado

            // Acceder al elemento en la posición `index` de la pila
            for (int i = 0; i <= index && !copiedStack.isEmpty(); i++) {
                if (i == index) {
                    trace += copiedStack.getTop(); // Agregar el elemento correspondiente a la traza
                }
                copiedStack.remove(); // Avanzar en la pila para alcanzar el índice deseado
            }

            index++; // Mover al siguiente índice de la diagonal
        }

        return trace;
    }


    public static QueueOfStacks getTranspose(QueueOfStacks queueOfStacks) {
        int maxSize = 0;
        QueueOfStacks tempQueue = new StaticQueueOfStacks();
        QueueOfStacks transposedQueue = new StaticQueueOfStacks();

        while (!queueOfStacks.isEmpty()) {
            Stack stack = queueOfStacks.removeStack();
            tempQueue.addStack(stack);
            //maxSize = Math.max(maxSize, stack.size()); // ver esto
        }

        for (int i = 0; i < maxSize; i++) {
            StaticStack newStack = new StaticStack();
            while (!tempQueue.isEmpty()) {
                Stack stack = tempQueue.removeStack();
                if (!stack.isEmpty()) {
                    newStack.add(stack.getTop());
                    stack.remove();
                }
                queueOfStacks.addStack(stack);
            }
            transposedQueue.addStack(newStack);
        }

        return transposedQueue;
    }

    public static QueueOfStacks addMatrices(QueueOfStacks q1, QueueOfStacks q2) {
        QueueOfStacks resultQueue = new StaticQueueOfStacks();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Stack stack1 = q1.removeStack();
            Stack stack2 = q2.removeStack();
            StaticStack resultStack = new StaticStack();

            while (!stack1.isEmpty() || !stack2.isEmpty()) {
                int value1 = stack1.isEmpty() ? 0 : stack1.getTop();
                int value2 = stack2.isEmpty() ? 0 : stack2.getTop();
                if (!stack1.isEmpty()) stack1.remove();
                if (!stack2.isEmpty()) stack2.remove();

                resultStack.add(value1 + value2);
            }
            resultQueue.addStack(resultStack);
        }

        return resultQueue;
    }

    public static QueueOfStacks copy(QueueOfStacks originalQos) {
        QueueOfStacks auxQueue = new StaticQueueOfStacks();
        QueueOfStacks copyQueue = new StaticQueueOfStacks();

        // Vaciar `originalQos` y llenar `auxQueue` y `copyQueue`
        while (!originalQos.isEmpty()) {
            Stack stack = originalQos.removeStack(); // Obtener la pila del frente
            Stack stackCopy = StackUtil.copy(stack); // Crear una copia de la pila
            auxQueue.addStack(stack); // Restaurar la pila original en la cola auxiliar
            copyQueue.addStack(stackCopy); // Agregar la copia a la cola de copia
        }

        // Restaurar el estado de `originalQos` a partir de `auxQueue`
        while (!auxQueue.isEmpty()) {
            originalQos.addStack(auxQueue.removeStack());
        }

        return copyQueue;
    }



}
