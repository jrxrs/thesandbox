package org.thesandbox.itask.tasks;

/**
 * A ITask.
 *
 * Created by IntelliJ IDEA.
 *
 * Date: 12-Dec-2009
 * @author jrxrs
 */
public abstract class ITask implements Runnable
{
    private String taskName;

    protected ITask(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
