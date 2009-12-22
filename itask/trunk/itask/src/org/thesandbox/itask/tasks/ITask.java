package org.thesandbox.itask.tasks;

import org.thesandbox.itask.ITaskApp;
import org.thesandbox.itask.ITaskPopUp;

/**
 * An ITask.
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

    public void execute() {
        ITaskPopUp popUp = new ITaskPopUp(this);
        popUp.setLocationRelativeTo(ITaskApp.getApplication().getMainFrame());

        ITaskApp.getApplication().show(popUp);
        if(popUp.getExitVal() != ITaskPopUp.CANCEL) {
            new Thread(this).start();
        }
    }

    @Override
    public String toString() {
        return taskName;
    }
}
