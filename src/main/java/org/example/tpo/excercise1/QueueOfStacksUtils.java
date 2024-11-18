package org.example.tpo.excercise1;

import org.example.adt.Stack;
import org.example.adt.StaticStack;
import org.example.utils.StackUtil;

public class QueueOfStacksUtils {

    public static int calculateTrace(QueueOfStacks qos) { // Complejidad O(n^2)
        int trace = 0; // La traza se define como la suma de los elementos de la diagonal principal --- O(1)
        QueueOfStacks copiedQueue = QueueOfStacksUtils.copy(qos); // Crear una copia de la cola original --- O(n^2)
        int index = 0; // --- O(1)

        while (!copiedQueue.isEmpty()) { // N iteraciones con complejidad O(n) = O(n^2)
            Stack stack = copiedQueue.remove(); // Obtener la pila en la posición actual  --- O(n)
            Stack copiedStack = StackUtil.copy(stack); // Copiar la pila para preservar el estado --- O(n)

            // Acceder al elemento en la posición `index` de la pila
            int currentSize = 0;
            while (!copiedStack.isEmpty()) { // N iteraciones con complejidad O(1) = O(n)
                int topElement = copiedStack.getTop(); // O(1)
                copiedStack.remove(); // O(1)

                // Si el índice coincide con la posición actual en la pila, sumamos a la traza
                if (currentSize == index) { // O(1)
                    trace += topElement; // O(1)
                    break; // No necesitamos seguir recorriendo esta pila
                }
                currentSize++; // O(1)
            }

            index++; // Mover al siguiente índice de la diagonal --- O(1)
        }

        return trace;
    }

    public static QueueOfStacks getTranspose(QueueOfStacks originalQos) { // Complejidad O(n^2)
        // Crear una copia de la cola original para preservar su estado
        QueueOfStacks copiedQueue = QueueOfStacksUtils.copy(originalQos); // Complejidad O(n^2)

        int matrixSize = QueueOfStacksUtils.size(copiedQueue); // Suponemos que la matriz es cuadrada --- O(n^2)

        // Crear una nueva QueueOfStacks para la transpuesta
        QueueOfStacks transposedQueue = new StaticQueueOfStacks(matrixSize); // Complejidad O(1), inicialización

        // Inicializar una matriz de pilas para formar las columnas transpuestas
        StaticStack[] transposedStacks = new StaticStack[matrixSize];
        for (int i = 0; i < matrixSize; i++) { // Iteración sobre matrixSize (n veces) --- O(n)
            transposedStacks[i] = new StaticStack(); // Complejidad O(1) para cada inicialización
        }

        // Llenar las pilas transpuestas
        while (!copiedQueue.isEmpty()) { // N iteraciones, donde N es el tamaño de la cola --- O(n) = O(n^2)
            Stack currentStack = copiedQueue.remove(); // Obtener y eliminar una pila de la cola --- O(n) debido al desplazamiento de elementos
            Stack copiedStack = StackUtil.copy(currentStack); // Copiar la pila actual --- O(n)

            for (int i = 0; i < matrixSize && !copiedStack.isEmpty(); i++) { // Iterar hasta `matrixSize` elementos --- O(n)
                int value = copiedStack.getTop(); // Obtener el tope de la pila --- O(1)
                transposedStacks[i].add(value); // Agregar el valor a la pila transpuesta --- O(1)
                copiedStack.remove(); // Remover el elemento del tope de la pila --- O(1)
            }
        }

        // Agregar las pilas transpuestas a la nueva cola
        for (int i = 0; i < matrixSize; i++) { // Iterar sobre el tamaño de la matriz --- O(n)
            transposedQueue.add(transposedStacks[i]); // Agregar cada pila a la cola transpuesta --- O(1) por adición
        }

        return transposedQueue;
    }


    public static QueueOfStacks addMatrices(QueueOfStacks q1, QueueOfStacks q2) { // Complejidad O(n^2)

        if (q1 == null || q2 == null || q1.isEmpty() || q2.isEmpty()) { // O(1)
            throw new IllegalArgumentException("Las matrices no pueden estar vacías.");
        }

        int sizeQ1 = QueueOfStacksUtils.size(q1); // O(n^2)
        int sizeQ2 = QueueOfStacksUtils.size(q2); // O(n^2)

        if (sizeQ1 != sizeQ2) { // O(1)
            throw new IllegalArgumentException("Las matrices deben ser de igual tamaño.");
        }

        QueueOfStacks copyQ1 = QueueOfStacksUtils.copy(q1); // Copia de la primera matriz --- O(n^2)
        QueueOfStacks copyQ2 = QueueOfStacksUtils.copy(q2); // Copia de la segunda matriz --- O(n^2)
        QueueOfStacks resultQueue = new StaticQueueOfStacks(sizeQ1); // O(n^2)

        while (!copyQ1.isEmpty() && !copyQ2.isEmpty()) { // O(n) iteraciones, donde n es el tamaño de las colas = O(n^2)
            Stack stack1 = copyQ1.remove(); // O(n)
            Stack stack2 = copyQ2.remove(); // O(n)
            StaticStack resultStack = new StaticStack(); // O(1)

            // Sumar los elementos de las pilas correspondientes
            while (!stack1.isEmpty() && !stack2.isEmpty()) { // O(n) iteraciones para cada pila
                int value1 = stack1.getTop(); // O(1)
                int value2 = stack2.getTop(); // O(1)
                resultStack.add(value1 + value2); // O(1)

                stack1.remove(); // O(1)
                stack2.remove(); // O(1)
            }

            resultQueue.add(resultStack); // Agregar la pila resultado a la cola --- O(1)
        }

        return resultQueue;
    }


    public static QueueOfStacks copy(QueueOfStacks originalQos) { // O(n^2)
        QueueOfStacks auxQueue = new StaticQueueOfStacks(QueueOfStacksUtils.size(originalQos)); // O(n^2)
        QueueOfStacks copyQueue = new StaticQueueOfStacks(QueueOfStacksUtils.size(originalQos)); // O(n^2)

        // Vaciar `originalQos` y llenar `auxQueue` y `copyQueue`
        while (!originalQos.isEmpty()) { // n veces O(n) = O(n^2)
            Stack stack = originalQos.remove(); // Obtener la pila del frente --- O(n)
            Stack stackCopy = StackUtil.copy(stack); // Crear una copia de la pila --- O(n)
            auxQueue.add(stack); // Restaurar la pila original en la cola auxiliar --- O(1)
            copyQueue.add(stackCopy); // Agregar la copia a la cola de copia --- O(1)
        }

        // Restaurar el estado de `originalQos` a partir de `auxQueue`
        while (!auxQueue.isEmpty()) { // n veces O(n) = O(n^2)
            originalQos.add(auxQueue.remove()); // O(n)
        }

        return copyQueue;
    }

    public static int size(QueueOfStacks qos) { // Complejidad O(n^2)
        if (qos == null || qos.isEmpty()) { // O(1)
            return 0; // Si la cola de pilas está vacía, el tamaño es 0 --- O(1)
        }

        QueueOfStacks copiedQueue = QueueOfStacksUtils.copy(qos); // Crear una copia para no modificar la original --- O(n^2)
        int totalSize = 0; // O(1)

        // Recorrer cada pila en la cola de pilas
        while (!copiedQueue.isEmpty()) { // O(n) iteraciones, donde n es el tamaño de la cola
            Stack stack = copiedQueue.remove(); // Obtener la pila actual --- O(n)
            totalSize += StackUtil.stackSize(stack); // Contar el número de elementos en la pila y sumarlo al total --- O(n)
        }

        return totalSize; // Retornar el tamaño total --- O(1)
    }



}
