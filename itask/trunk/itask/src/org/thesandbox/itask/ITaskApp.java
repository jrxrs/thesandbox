package org.thesandbox.itask;

import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 *
 * Created by IntelliJ IDEA.
 *
 * @author jrxrs
 * @date 30-Nov-2009
 */
public class ITaskApp extends SingleFrameApplication
{
    /**
     * At start up create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        show(new ITaskView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ITaskApp
     */
    public static ITaskApp getApplication() {
        return Application.getInstance(ITaskApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(ITaskApp.class, args);
    }
}
