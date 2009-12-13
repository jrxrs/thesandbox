package org.thesandbox.itask.tasks;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements a Windows shutdown.
 *
 * Created by IntelliJ IDEA.
 *
 * Date: 12-Dec-2009
 * @author jrxrs
 */
public class WindowsShutdown extends Shutdown
{
    private static Logger logger = Logger.getLogger(WindowsShutdown.class.getCanonicalName());

    private final String cmd = "shutdown.exe";
    private final String comment = " -c \"iTask Shutdown\"";
    private StringBuffer optString;

    public WindowsShutdown(Option... opts) {
        super("Windows Shutdown");
        optString = new StringBuffer();
        for(Option o : opts) {
            optString.append(o.getArg());
        }
    }

    public void run() {
        logger.log(Level.INFO, "Execute Task: " + getTaskName());
        try {
            Runtime rt = Runtime.getRuntime();
            logger.log(Level.INFO, "Command: " + cmd + optString.toString()  + comment);
            Process p = rt.exec(cmd + optString.toString()  + comment);

            try {
                p.waitFor();
            } catch(InterruptedException ie) {
                logger.log(Level.WARNING, "Shutdown Interrupted", ie);
            }

            System.exit(0);
        } catch(IOException ioe) {
            logger.log(Level.WARNING, "IO Error shutting down!", ioe);
        }
    }
}
