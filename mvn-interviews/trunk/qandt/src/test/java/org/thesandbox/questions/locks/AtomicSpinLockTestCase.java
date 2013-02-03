package org.thesandbox.questions.locks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSpinLockTestCase {

    private final AtomicInteger ai = new AtomicInteger(42);

    private AtomicSpinLock atomicSpinLock;

    @Before
    public void setUp() throws Exception {
        atomicSpinLock = new AtomicSpinLock(ai);
    }

    @Test
    public void testLockAndUnlock() throws Exception {
        final Thread me = Thread.currentThread();
        me.setName("AtomicSpinLockTest1");

        /* Test that my AtomicInteger is reset correctly to 0 */
        Assert.assertEquals(0, ai.get());

        /* Check that I can lock the lock */
        atomicSpinLock.lock();

        Assert.assertSame(me, atomicSpinLock.getCurrentOwner());
        Assert.assertEquals(1, ai.get());

        /* Check that the lock is re-entrant with no state changes when attempted */
        atomicSpinLock.lock();

        Assert.assertSame(me, atomicSpinLock.getCurrentOwner());
        Assert.assertEquals(1, ai.get());

        /* Check that the lock can be unlocked */
        atomicSpinLock.unlock();

        Assert.assertEquals(0, ai.get());
        Assert.assertNull(atomicSpinLock.getCurrentOwner());
    }

    @Test
    public void testMultipleThreads() throws Exception {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final long start = System.currentTimeMillis();
                atomicSpinLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " waited " + (System.currentTimeMillis() - start) +
                            "ms to obtain the lock.");
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted while sleeping!");
                }
                atomicSpinLock.unlock();
            }
        };

        final int noThreads = 5;

        final Thread[] threads = new Thread[noThreads];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable, "TestThread" + i);
        }

        /* Start all the test threads */
        for (Thread thread : threads) {
            thread.start();
        }

        /* Force the JUnit thread to wait for all my test threads to die */
        for (Thread thread : threads) {
            thread.join();
        }

        Assert.assertEquals(0, ai.get());
    }

}
