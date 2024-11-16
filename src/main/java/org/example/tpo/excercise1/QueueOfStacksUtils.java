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
            Stack stack = copiedQueue.remove(); // Obtener la pila en la posición actual
            Stack copiedStack = StackUtil.copy(stack); // Copiar la pila para preservar el estado

            // Acceder al elemento en la posición `index` de la pila
            int currentSize = 0;
            while (!copiedStack.isEmpty()) {
                int topElement = copiedStack.getTop();
                copiedStack.remove();

                // Si el índice coincide con la posición actual en la pila, sumamos a la traza
                if (currentSize == index) {
                    trace += topElement;
                    break; // No necesitamos seguir recorriendo esta pila
                }
                currentSize++;
            }

            index++; // Mover al siguiente índice de la diagonal
        }

        return trace;
    }

    public static QueueOfStacks getTranspose(QueueOfStacks originalQos) {
        // Crear una copia de la cola original para preservar su estado
        QueueOfStacks copiedQueue = QueueOfStacksUtils.copy(originalQos);
        int matrixSize = copiedQueue.size(); // Suponemos que la matriz es cuadrada

        // Crear una nueva QueueOfStacks para la transpuesta
        QueueOfStacks transposedQueue = new StaticQueueOfStacks(matrixSize); // Ajusta según tu implementación dinámica si es necesario

        // Inicializar una matriz de pilas para formar las columnas transpuestas
        StaticStack[] transposedStacks = new StaticStack[matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            transposedStacks[i] = new StaticStack();
        }

        // Llenar las pilas transpuestas
        while (!copiedQueue.isEmpty()) {
            Stack currentStack = copiedQueue.remove();
            Stack copiedStack = StackUtil.copy(currentStack);

            for (int i = 0; i < matrixSize && !copiedStack.isEmpty(); i++) {
                int value = copiedStack.getTop();
                transposedStacks[i].add(value);
                copiedStack.remove();
            }
        }

        // Agregar las pilas transpuestas a la nueva cola
        for (int i = 0; i < matrixSize; i++) {
            transposedQueue.add(transposedStacks[i]);
        }

        return transposedQueue;
    }

    public static QueueOfStacks addMatrices(QueueOfStacks q1, QueueOfStacks q2) {

        if (q1 == null || q2 == null || q1.isEmpty() || q2.isEmpty()) {
            throw new IllegalArgumentException("Las matrices no pueden estar vacías.");
        }

        if (q1.size() != q2.size()) {
            throw new IllegalArgumentException("Las matrices deben ser de igual tamaño.");
        }

        QueueOfStacks copyQ1 = QueueOfStacksUtils.copy(q1);
        QueueOfStacks copyQ2 = QueueOfStacksUtils.copy(q2);
        QueueOfStacks resultQueue = new StaticQueueOfStacks(copyQ1.size());

        while (!copyQ1.isEmpty() && !copyQ2.isEmpty()) {
            Stack stack1 = copyQ1.remove();
            Stack stack2 = copyQ2.remove();
            StaticStack resultStack = new StaticStack();

            // Sumar los elementos de las pilas correspondientes
            while (!stack1.isEmpty() && !stack2.isEmpty()) {
                int value1 = stack1.getTop();
                int value2 = stack2.getTop();
                resultStack.add(value1 + value2);

                stack1.remove();
                stack2.remove();
            }

            resultQueue.add(resultStack);
        }

        return resultQueue;
    }

    public static QueueOfStacks copy(QueueOfStacks originalQos) {
        QueueOfStacks auxQueue = new StaticQueueOfStacks(originalQos.size());
        QueueOfStacks copyQueue = new StaticQueueOfStacks(originalQos.size());

        // Vaciar `originalQos` y llenar `auxQueue` y `copyQueue`
        while (!originalQos.isEmpty()) {
            Stack stack = originalQos.remove(); // Obtener la pila del frente
            Stack stackCopy = StackUtil.copy(stack); // Crear una copia de la pila
            auxQueue.add(stack); // Restaurar la pila original en la cola auxiliar
            copyQueue.add(stackCopy); // Agregar la copia a la cola de copia
        }

        // Restaurar el estado de `originalQos` a partir de `auxQueue`
        while (!auxQueue.isEmpty()) {
            originalQos.add(auxQueue.remove());
        }

        return copyQueue;
    }

}
