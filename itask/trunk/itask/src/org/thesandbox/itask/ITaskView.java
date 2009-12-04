package org.thesandbox.itask;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.*;

/**
 * The main iTask application frame.
 *
 * Created by IntelliJ IDEA.
 *
 * @author jrxrs
 * @date 30-Nov-2009
 */
public class ITaskView extends FrameView
{
    private ResourceMap resourceMap;
    private ActionMap actionMap;

    // Properties
    private Properties userProps;
    private static final String REP_PATH    = "repository";
    private static final String NOT_SET     = "null";

    // GUI bits
    private JPanel mainPanel, statusPanel;
    private JMenuBar menuBar;
    private JProgressBar progressBar;
    private JLabel statusAnimationLabel, statusMessageLabel;
    private Status status = Status.NOPATH;

    private JDialog aboutBox;

    /* Status enum for setting the App Status */
    public static enum Status {
        NOPATH("status.NoRepPath.text"),
        NODOWNLOADS("status.NoDownloads.text"),
        SCANNING("status.Scanning.text"),
        DOWNLOADING("status.Downloading.text"),
        COMPLETE("status.DownloadingComplete.text");

        private String resource;

        private Status(String resource) {
            this.resource = resource;
        }

        public String getResource() {
            return resource;
        }
    }

    public ITaskView(SingleFrameApplication app) {
        super(app);

        resourceMap = getResourceMap();
        actionMap = app.getContext().getActionMap(ITaskView.class, this);

        loadUserProperties();

        initComponents();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ITaskApp.getApplication().getMainFrame();
            aboutBox = new ITaskAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ITaskApp.getApplication().show(aboutBox);
    }

    @Action
    public void showSettings() {
        System.out.println("Showing settings!");
    }

    private void writeTheseProps(File f, Properties p) throws Exception {
        FileOutputStream fos = new FileOutputStream(f);
        String username = System.getProperty("user.name");
        p.storeToXML(fos, resourceMap.getString("Application.title")
                + " Settings for " + username);
        fos.close();
    }

    private File newDefaultProps(File propsFile) throws Exception {
        Properties freshProperties = new Properties();
        freshProperties.put(REP_PATH, NOT_SET);
        writeTheseProps(propsFile, freshProperties);
        return propsFile;
    }

    private File rinse(File props) throws Exception {
        if(props.exists() && props.isFile())
            return props;
        else if(props.createNewFile())
            return newDefaultProps(props);
        else return null;
    }

    private File propsExist() throws Exception {
        String propsFileName = "iTaskSettings.xml";
        String home = System.getProperty("user.home");
        File homeDir = new File(home);
        if(homeDir.exists() && homeDir.isDirectory()) {
            File iTaskHome = new File(homeDir, ".".concat(resourceMap.getString("Application.title")));
            File props = new File(iTaskHome, propsFileName);
            if(iTaskHome.exists() && iTaskHome.isDirectory()) {
                return rinse(props);
            } else {
                if(iTaskHome.mkdir() && props.createNewFile())
                    return newDefaultProps(props);
                else return null;
            }
        } else { //Could not fine user.home - try current directory
            String curDir = System.getProperty("user.dir");
            File props = new File(curDir, propsFileName);
            return rinse(props);
        }
    }

    private void loadUserProperties() {
        try {
            File props = propsExist();
            if(props != null) {
                System.out.println("props file found at " + props.getPath());
                FileInputStream fis = new FileInputStream(props);
                userProps = new Properties();
                userProps.loadFromXML(fis);
                fis.close();
                if(NOT_SET.equals(userProps.getProperty(REP_PATH, NOT_SET))) {
                    status = Status.NOPATH;
                } else {
                    status = Status.SCANNING;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {

        getFrame().setIconImage(((ImageIcon)resourceMap.getIcon("mainFrame.icon")).getImage());
        mainPanel = new JPanel();

        statusPanel = new JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new JLabel();
        statusAnimationLabel = new JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N
        statusMessageLabel.setText(resourceMap.getString(status.getResource()));

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(getITaskViewMenuBar());
        setStatusBar(statusPanel);
    }

    private JMenuBar getITaskViewMenuBar() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu();
        JMenuItem exitMenuItem = new JMenuItem();
        JMenu optionsMenu = new JMenu();
        JMenuItem settingsMenuItem = new JMenuItem();
        JMenu helpMenu = new JMenu();
        JMenuItem aboutMenuItem = new JMenuItem();

        menuBar.setName("menuBar"); // NOI18N

        // File menu
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        // Options menu
        optionsMenu.setText(resourceMap.getString("optionsMenu.text")); // NOI18N
        optionsMenu.setName("optionsMenu"); // NOI18N

        settingsMenuItem.setAction(actionMap.get("showSettings")); // NOI18N
        settingsMenuItem.setName("settingsMenuItem"); // NOI18N
        optionsMenu.add(settingsMenuItem);

        menuBar.add(optionsMenu);

        // Help menu
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        return menuBar;
    }
}
