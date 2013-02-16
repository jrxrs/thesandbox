package org.thesandbox.questions.pc;

import java.util.LinkedList;

/**
 * This exactly the same class as {@link MessageQueue}, although it uses a
 * specific/separate object as the monitor rather than the monitor of the
 * instance of MessageQueue, essentially the only difference between the two is
 * that this one solves the encapsulation problem mentioned before because the
 * lock object is private and therefore cannot be locked by any other threads.
 */
public class MessageQueue2<T> {

    private final LinkedList<T> queue;
    private final Object lock = new Object();

    public MessageQueue2() {
        queue = new LinkedList<T>();
    }

    public void add(T o) {
        synchronized (lock) {
            queue.add(o);

            lock.notify();
        }
    }

    public T remove() {
        T rv = null;
        synchronized (lock) {
            long start = System.currentTimeMillis();
            while (queue.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException ie) {
                    return rv;
                }
            }
            System.out.println(Thread.currentThread() + " waited for: " + (System.currentTimeMillis() - start));
            rv = queue.remove();
        }

        return rv;
    }

}
