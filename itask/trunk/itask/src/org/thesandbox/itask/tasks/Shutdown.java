package org.thesandbox.itask.tasks;

/**
 * A Shutdown ITask. This class this be extended with platform specific
 * implementations that spawn process to shutdown/logoff a system.
 *
 * Created by IntelliJ IDEA.
 *
 * Date: 12-Dec-2009
 * @author jrxrs
 */
public abstract class Shutdown extends ITask
{
    /* Status enum for setting the App Status */
    public enum Option {
        LOG_OFF(" -l"),
        SHUTDOWN(" -s"),
        RESTART(" -r"),
        FORCE(" -f");

        private String arg;

        private Option(String arg) {
            this.arg = arg;
        }

        public String getArg() {
            return arg;
        }
    }

    public Shutdown(String taskName) {
        super(taskName);
    }
}
