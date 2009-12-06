package org.thesandbox.itask;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;

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

    // GUI bits
    private JPanel mainPanel, statusPanel;
    private JMenuBar menuBar;
    private JProgressBar progressBar;
    private JLabel statusAnimationLabel, statusMessageLabel;
    private Status status = Status.NOPATH;

    private JDialog aboutBox;
    private JDialog settingsDialog;

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

        processProperties();

        initComponents();
        bgScan();
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
        if (settingsDialog == null) {
            JFrame mainFrame = ITaskApp.getApplication().getMainFrame();
            settingsDialog = new ITaskSettings(mainFrame);
            settingsDialog.setLocationRelativeTo(mainFrame);
        }
        ITaskApp.getApplication().show(settingsDialog);
    }

    private void processProperties() {
        if(ITaskProperties.NOT_SET.equals(ITaskProperties.getInstance()
                .get(ITaskProperties.REP_PATH, ITaskProperties.NOT_SET))) {
            status = Status.NOPATH;
        } else {
            status = Status.SCANNING;
        }
    }

    private void bgScan() {
        progressBar.setIndeterminate(true);
    }

    private void initComponents() {

        getFrame().setIconImage(((ImageIcon)resourceMap.getIcon("window.icon")).getImage());
        mainPanel = new JPanel();

        statusPanel = new JPanel();
        JSeparator statusPanelSeparator = new JSeparator();
        statusMessageLabel = new JLabel();
        statusAnimationLabel = new JLabel();
        progressBar = new JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N
        statusMessageLabel.setText(resourceMap.getString(status.getResource()));

        statusAnimationLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        GroupLayout statusPanelLayout = new GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
