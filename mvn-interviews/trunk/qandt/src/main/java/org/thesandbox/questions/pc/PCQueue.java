package org.thesandbox.questions.pc;

import java.util.LinkedList;
import java.util.List;

public class PCQueue<T> {

    final List<T> queue = new LinkedList<T>();

    public T take() {
        synchronized (queue) {
            while (queue.size() < 1) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return queue.remove(queue.size() - 1);
        }
    }

    public void put(T value) {
        synchronized (queue) {
            queue.add(value);
            queue.notifyAll();
        }
    }

}
