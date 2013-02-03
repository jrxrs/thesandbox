package org.thesandbox.questions.locks;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSpinLock {

    private final AtomicInteger lockCount;
    private Thread currentOwner = null;

    public AtomicSpinLock(final AtomicInteger lockCount) {
        this.lockCount = lockCount;

        /* Reset the count */
        this.lockCount.set(0);
    }

    public final void lock() {
        if (currentOwner == Thread.currentThread()) {
            System.out.println(currentOwner.getName() + " attempted to lock this lock, however " +
                    "it already owns the lock so no action has been taken.");
            return;
        }

        while (!lockCount.compareAndSet(0, 1)) {
            /* Keep spinning on the lock until expected value is true */
        }

        currentOwner = Thread.currentThread();
        System.out.println("This lock is now held by " + currentOwner.getName() + ".");
    }

    public final void unlock() {
        if (currentOwner == Thread.currentThread()) {
            System.out.println(currentOwner.getName() + " has now unlocked this lock.");
            currentOwner = null;
            lockCount.compareAndSet(1, 0);
        }
    }

    public Thread getCurrentOwner() {
        return currentOwner;
    }

}
