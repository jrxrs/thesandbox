package questions;

import java.util.LinkedList;

/**
 * You have a requirement for a thread safe queue object called MessageQueue.
 * Objects may be added to the end of the MessageQueue by any number of
 * threads via the add(Object o) method. Similarly, the MessageQueue will be
 * read by any number of threads by calling the remove() method, which will
 * return the Object at the front of the MessageQueue. The threads that are
 * reading from the queue should block if the queue is empty,
 * becoming empowered to process the information when it arrives.
 *
 * Write a suitable class.
 */
public class MessageQueue
{
    private final LinkedList<Object> queue;

    public MessageQueue() {
        queue = new LinkedList<Object>();
    }

    public synchronized void add(Object o) {
        queue.add(o);
        // Wake up anyone waiting for something to be put on the queue.
        notifyAll();
    }

    public synchronized Object remove() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ie) {
                return null;
            }
        }

        return queue.remove();
    }

    /**
     * Make a blocking Dequeue call so that we'll only return when the queue has
     * something on it, otherwise we'll wait until something is put on it.
     *
     * @return This will return null if the thread wait() call is interrupted.
     */
    public synchronized Object dequeue() {
        Object msg = null;
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Error return the client a null item
                return msg;
            }
        }
        msg = queue.remove();
        return msg;
    }
}
