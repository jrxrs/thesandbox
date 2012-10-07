package questions;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * You have a requirement for a thread safe queue object called MessageQueue.
 * Objects may be added to the end of the MessageQueue by any number of
 * threads via the add(Object o) method. Similarly, the MessageQueue will be
 * read by any number of threads by calling the remove()method, which will
 * return the Object at the front of the MessageQueue. The threads that are
 * reading from the queue should block if the queue is empty,
 * becoming empowered to process the information when it arrives.
 *
 * Write a suitable class.
 */
public class CheatsMessageQueue
{
    private final LinkedBlockingQueue<Object> queue;

    public CheatsMessageQueue() {
        queue = new LinkedBlockingQueue<Object>();
    }

    public void add(Object o) throws InterruptedException {
        queue.put(o);
    }

    public Object remove() throws InterruptedException {
        return queue.take();
    }
}
