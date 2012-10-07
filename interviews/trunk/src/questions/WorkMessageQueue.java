package questions;

import java.util.LinkedList;

public class WorkMessageQueue
{
    private final LinkedList<Object> queue;

    public WorkMessageQueue() {
        queue = new LinkedList<Object>();
    }

    public synchronized void add(Object o) {
        queue.add(o);

        notify();
    }

    public synchronized Object remove() {
        Object rv = null;
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

    public static void main(String[] args) {
        final WorkMessageQueue mq = new WorkMessageQueue();

        Thread producer = new Thread("producer1") {
            @Override
            public void run() {
                mq.add("producer1 put this here!");
            }
        };

        Thread producer2 = new Thread("producer2") {
            @Override
            public void run() {
                mq.add("producer2 put this here!");
            }
        };

        Thread consumer = new Thread("consumer") {
            @Override
            public void run() {
                for (;;) {
                    System.out.println(Thread.currentThread() + " got an Object: " + mq.remove());
                }
            }
        };
        consumer.start();

        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException ie) {
            System.err.println("Main thread was Interrupted");
            ie.printStackTrace();
        }

        producer2.start();
        producer.start();
    }
}
