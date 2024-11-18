package org.example.tpo.excercise3;

import org.example.adt.Queue;
import org.example.adt.StaticQueue;
import org.example.tpo.excercise3.Stack;
import org.example.tpo.excercise3.StaticGenericStack;
import org.example.utils.QueueUtil;

public class QueueOfQueueUtils {

    /*
     *  El metodo concatenate recibe n instancias de QueueOfQueue y genera una nueva instancia de QueueOfQueue
     *  con todos los elementos de las instancias anteriores manteniendo el orden en que se leyeron los valores
     *  de estas instancias.
     */

    public static QueueOfQueue concatenate(QueueOfQueue... queuesOfQueue) {
        QueueOfQueue result = new StaticQueueOfQueue();

        for (QueueOfQueue queueOfQueue : queuesOfQueue) {
            // Crear una copia de la QueueOfQueue actual para evitar modificarla
            QueueOfQueue copiedQueueOfQueue = QueueOfQueueUtils.copy(queueOfQueue);

            while (!copiedQueueOfQueue.isEmpty()) {
                Queue queue = copiedQueueOfQueue.getFirst();
                result.add(QueueUtil.copy(queue)); // Crear una copia para no modificar el original
                copiedQueueOfQueue.remove(); // Esto ahora solo afecta a la copia
            }
        }

        return result;
    }


    /*
     *  El metodo flat crea una instancia de Queue a partir de una QueueOfQueue con los mismos elementos.
     */

    public static Queue flat(QueueOfQueue queueOfQueue) {
        if (queueOfQueue == null || queueOfQueue.isEmpty()) {
            throw new IllegalArgumentException("La QueueOfQueue proporcionada no puede estar vacía");
        }

        Queue result = new StaticQueue(); // se podria preguntar antes si la queueOfQueue que recibo es estatica antes de asumirlo

        QueueOfQueue copiedQoq = copy(queueOfQueue); // Crear una copia para no modificar el original

        while (!copiedQoq.isEmpty()) {
            Queue queue = copiedQoq.getFirst();
            while (!queue.isEmpty()) {
                result.add(queue.getFirst());
                queue.remove();
            }
            copiedQoq.remove();
        }

        return result;
    }

    /*
     *  El metodo reverseWithDepth invierte una QueueOfQueue y también cada Queue dentro de ella.
     */

    public static QueueOfQueue reverseWithDepth(QueueOfQueue queueOfQueue) {
        if (queueOfQueue == null || queueOfQueue.isEmpty()) {
            throw new IllegalArgumentException("La QueueOfQueue no puede estar vacía");
        }

        // Primero, invertilos el orden de las colas de la QueueOfQueue
        QueueOfQueue revertedQueueOfQueue = revert(queueOfQueue);

        // Luego, invertimos los elementos de cada cola usando QueueUtil.revert
        QueueOfQueue resultQueueOfQueue = new StaticQueueOfQueue();

        while (!revertedQueueOfQueue.isEmpty()) {
            Queue originalQueue = revertedQueueOfQueue.getFirst();
            Queue revertedQueue = QueueUtil.revert(originalQueue); // Invertir cada cola
            resultQueueOfQueue.add(revertedQueue); // Agregar la cola invertida al resultado
            revertedQueueOfQueue.remove();
        }

        return resultQueueOfQueue;
    }

    // Metodo para invertir el orden de cada cola en una copia de la QueueOfQueue

    public static QueueOfQueue revert(QueueOfQueue queueOfQueue) {
        if (queueOfQueue == null || queueOfQueue.isEmpty()) {
            throw new IllegalArgumentException("La QueueOfQueue no puede estar vacía");
        }

        // Crear una copia de la QueueOfQueue para no modificar el original
        QueueOfQueue copiedQueueOfQueue = copy(queueOfQueue);
        QueueOfQueue reversedQoq = new StaticQueueOfQueue(); // Instancia para el resultado final

        // Usar una pila para almacenar y luego revertir el orden de las Queue
        Stack<Queue> tempStack = new StaticGenericStack<>();
        while (!copiedQueueOfQueue.isEmpty()) {
            Queue queue = copiedQueueOfQueue.getFirst(); // Obtener la Queue de la copia
            copiedQueueOfQueue.remove(); // Remover de la copia
            tempStack.add(queue); // Almacenar la Queue en la pila
        }

        // Volver a agregar las colas en orden inverso a la nueva instancia
        while (!tempStack.isEmpty()) {
            Queue queue = tempStack.getTop(); // Recuperar la Queue de la pila
            reversedQoq.add(queue); // Agregar a la nueva instancia en orden inverso
            tempStack.remove(); // Remover de la pila
        }

        return reversedQoq;
    }

    /*
     * Metodo auxiliar para copiar una QueueOfQueue sin modificar la original.
     */

    public static QueueOfQueue copy(QueueOfQueue original) {
    QueueOfQueue copy = new StaticQueueOfQueue();
    QueueOfQueue temp = new StaticQueueOfQueue();

    // Copiar elementos de la original a la copia y a una cola temporal para restaurar el estado
    while (!original.isEmpty()) {
        Queue originalQueue = original.getFirst();
        Queue queueCopy = QueueUtil.copy(originalQueue); // Realiza una copia de la Queue actual
        copy.add(queueCopy);
        temp.add(originalQueue);
        original.remove();
    }

    // Restaurar el estado de la original desde la cola temporal
    while (!temp.isEmpty()) {
        original.add(temp.getFirst());
        temp.remove();
    }

    return copy;
    }

}
