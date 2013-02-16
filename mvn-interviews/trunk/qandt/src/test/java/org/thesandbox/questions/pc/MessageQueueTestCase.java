package org.thesandbox.questions.pc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageQueueTestCase {

    private static final int NO_PRODUCERS = 100;

    private volatile boolean testRunning = false;

    private MessageQueue<String> mq;

    @Before
    public void setUp() throws Exception {
        mq = new MessageQueue<String>();
    }

    @Test
    public void testAddThenRemove() throws Exception {
        final String s = "String_1";
        mq.add(s);

        Assert.assertSame(s, mq.remove());
    }

    @Test
    public void testInterruptRemover() throws Exception {
        final Thread testThread = Thread.currentThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testThread.interrupt();
            }
        }).start();

        Assert.assertNull(mq.remove());
    }

    @Test
    public void testMultiThreadedExample() throws Exception {
        final AtomicInteger counter = new AtomicInteger(NO_PRODUCERS);

        testRunning = true;

        final Thread consumer = new Thread("Consumer") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " thread starting.");
                while (testRunning) {
                    System.out.println(Thread.currentThread() + " got an Object: " + mq.remove());
                    if (counter.decrementAndGet() == 0) {
                        testRunning = false;
                    }
                }
                System.out.println(Thread.currentThread() + " thread terminating.");
            }
        };
        consumer.start();

        Random rand = new Random();

        for (int i = 0; i < NO_PRODUCERS; i++) {
            if (i >= 50 && i < 75 && i % 2 == 0) {
                Thread.sleep(rand.nextInt(100));
            }
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    mq.add(Thread.currentThread().getName() + " put this here!");
                }
            }, "Producer" + (i + 1));
            t.start();
            t.join();
        }

        /* Wait for the consuming thread to die */
        consumer.join();
    }

}
