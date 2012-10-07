package questions;

import java.util.LinkedList;

/**
 * This is a copy of the MessageQueue question/class.
 * @see questions.MessageQueue
 */
public class CopyMessageQueue
{
    private final LinkedList<Object> queue;

    public CopyMessageQueue() {
        queue = new LinkedList<Object>();
    }

    public synchronized void add(Object o) {
        queue.add(o);

        notify();
    }

    /**
     * @return the first Object in the queue other null if the
     *         thread calling remove is interrupted.
     */
    public synchronized Object remove() {
        Object rv = null;
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ie) {
                return rv;
            }
        }

        rv = queue.remove();
        return rv;
    }
}
