package org.thesandbox.questions.pc;

import java.util.LinkedList;

/**
 * This approach is viable and will work however it's worth pointing out that it
 * doesn't encapsulate your queue very well, for instance by synchronizing the
 * methods themselves you are forcing the calling thread to lock the entire
 * object so that no other threads can execute other synchronized methods on the
 * same object concurrently, therefore if some other thread has already obtained
 * your objects monitor (lock) then any other threads attempting to execute
 * synchronized methods will be blocked.
 */
public class MessageQueue<T> {

    private final LinkedList<T> queue;

    public MessageQueue() {
        queue = new LinkedList<T>();
    }

    public synchronized void add(T o) {
        queue.add(o);

        notify();
    }

    public synchronized T remove() {
        T rv = null;
        long start = System.currentTimeMillis();
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ie) {
                return rv;
            }
        }
        System.out.println(Thread.currentThread() + " waited for: " + (System.currentTimeMillis() - start));
        rv = queue.remove();

        return rv;
    }

}
