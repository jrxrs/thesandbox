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
    private boolean warning;

    protected ITask(String taskName) {
        this.taskName = taskName;
        warning = false;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void execute() {
        if(!warning) {
            warning = true;
            ITaskPopUp popUp = new ITaskPopUp(this);
            popUp.setLocationRelativeTo(ITaskApp.getApplication().getMainFrame());

            ITaskApp.getApplication().show(popUp);
            if(popUp.getExitVal() != ITaskPopUp.CANCEL) {
                new Thread(this).start();
            } else {
                warning = false;
            }
        }
    }

    @Override
    public String toString() {
        return taskName;
    }
}
