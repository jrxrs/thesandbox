package org.thesandbox.itask.tasks;

import org.thesandbox.itask.ITaskApp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Task that closes the iTask Application.
 *
 * Created by IntelliJ IDEA.
 *
 * Date: 20-Dec-2009
 * @author jrxrs
 */
public class CloseITask extends ITask
{
    private static Logger logger = Logger.getLogger(CloseITask.class.getCanonicalName());

    public CloseITask() {
        super("Close iTask");
    }

    public void run() {
        logger.log(Level.INFO, "Execute Task: " + getTaskName());
        ITaskApp.getApplication().exit();
    }
}
