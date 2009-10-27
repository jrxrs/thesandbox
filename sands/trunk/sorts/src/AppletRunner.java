import java.awt.*;
import java.applet.*;
import java.net.URL;

// StandaloneApplet is an applet that runs either as
// an applet or a standalone application.  To run
// standalone, it provides a main method that creates
// a frame, then creates an instance of the applet and
// adds it to the frame.

public class AppletRunner
{
     public static void main(String args[])
     {
// Create the frame this applet will run in
          Frame appletFrame = new Frame("Sorting Applet");

// The frame needs a layout manager, use the GridLayout to maximize
// the applet size to the frame.
          appletFrame.setLayout(new GridLayout(1,0));

// Have to give the frame a size before it is visible
          appletFrame.resize(100, 100);

// Make the frame appear on the screen. You should make the frame appear
// before you call the applet's init method. On some Java implementations,
// some of the graphics information is not available until there is a frame.
// If your applet uses certain graphics functions like getGraphics() in the
// init method, it may fail unless there is a frame already created and
// showing.
          appletFrame.show();

// Create an instance of the applet
          Applet myApplet = new SortItem();

// Add the applet to the frame
          appletFrame.add(myApplet);

// Initialize and start the applet
          AppletStub as = new AppletStub() {

            public boolean isActive() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public URL getDocumentBase() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public URL getCodeBase() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public String getParameter(String name) {
                if(name.equals("alg")) {
                    return "SwapSort";
                } else return "FastQSort";
            }

            public AppletContext getAppletContext() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void appletResize(int width, int height) {
                
            }
        };
          myApplet.setStub(as);
          myApplet.init();
          myApplet.start();

     }
}
