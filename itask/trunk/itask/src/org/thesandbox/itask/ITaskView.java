package org.thesandbox.itask;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private JPanel mainPanel;
    private JProgressBar progressBar;
    private JLabel statusAnimationLabel, statusMessageLabel;
    private JMenuItem rescanMenuItem;

    private Status status = Status.NO_PATH;
    private int dlCount;

    private JDialog aboutBox;

    private Timer rescanTimer;

    /* Status enum for setting the App Status */
    public static enum Status {
        NO_PATH("status.NoRepPath.text"),
        NO_DOWNLOADS("status.NoDownloads.text"),
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

        dlCount = 0;

        processProperties();

        initComponents();
        if(status == Status.SCANNING)
            bgScan();
        startTimer();
    }

    @Action
    public void rescan() {
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

    /**
     * Create a new Setting dialog to allow the user to view and/or alter their
     * settings. 
     */
    @Action
    public void showSettings() {
        JFrame mainFrame = ITaskApp.getApplication().getMainFrame();
        ITaskSettings settingsDialog = new ITaskSettings(mainFrame);
        settingsDialog.setLocationRelativeTo(mainFrame);

        /* Need to catch a save on settings and make sure we reconfigure the
         * timer and rescan the directory structure after we exit on safe.
         * Maybe we could prompt the user to tell us if they wish to rescan? */

        ITaskApp.getApplication().show(settingsDialog);
    }

    private void processProperties() {
        if(ITaskProperties.NOT_SET.equals(ITaskProperties.getInstance()
                .get(ITaskProperties.REP_PATH, ITaskProperties.NOT_SET))) {
            status = Status.NO_PATH;
        } else {
            status = Status.SCANNING;
        }
    }

    public void setStatus(Status newStatus) {
        status = newStatus;
        statusMessageLabel.setText(resourceMap.getString(status.getResource()));
        switch(status) {
            case COMPLETE:
                progressBar.setValue(0);
                break;
            case NO_DOWNLOADS:
                progressBar.setIndeterminate(false);
                break;
            case DOWNLOADING:
                progressBar.setIndeterminate(true);
                break;
        }
    }

    private void bgScan() {
        setStatus(Status.SCANNING);
        progressBar.setIndeterminate(true);
        rescanMenuItem.setEnabled(false);
        BGRepScan task = new BGRepScan();
        task.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if("progress".equals(evt.getPropertyName())) {
                    progressBar.setIndeterminate(false);
                    progressBar.setValue((Integer)evt.getNewValue());
                }
            }
        });

        task.execute();
    }

    private void startTimer() {
        rescanTimer = new Timer();
        try {
            int duration = Integer.parseInt(ITaskProperties.getInstance()
                    .get(ITaskProperties.RESCAN_PERIOD));
            if(duration > 30) {
                duration *= 1000;
                rescanTimer.scheduleAtFixedRate(new TimerScan(), duration, duration);
            }
        } catch(NumberFormatException nfe) { }
    }

    private void reconfigureTimer() {
        rescanTimer.cancel();
        rescanTimer.purge();
        startTimer();
    }

    private class TimerScan extends TimerTask {

        @Override
        public void run() {
            bgScan();
        }
    }

    private void initComponents() {

        getFrame().setIconImage(((ImageIcon)resourceMap.getIcon("window.icon")).getImage());
        getFrame().setPreferredSize(new Dimension(750, 500));
        mainPanel = new JPanel();

        JPanel statusPanel = new JPanel();
        JSeparator statusPanelSeparator = new JSeparator();
        statusMessageLabel = new JLabel();
        statusAnimationLabel = new JLabel();
        progressBar = new JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setBorder(BorderFactory.createTitledBorder(
                resourceMap.getString("mainPanel.border.text")));
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(new JLabel(resourceMap.getIcon("downloading.gif")));

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N
        statusMessageLabel.setText(resourceMap.getString(status.getResource()));

        statusAnimationLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N
        statusAnimationLabel.setText(String.valueOf(dlCount));

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

        setComponent(new JScrollPane(mainPanel));
        setMenuBar(getITaskViewMenuBar());
        setStatusBar(statusPanel);
    }

    private JMenuBar getITaskViewMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu();
        JMenuItem exitMenuItem = new JMenuItem();
        rescanMenuItem = new JMenuItem();
        JMenu optionsMenu = new JMenu();
        JMenuItem settingsMenuItem = new JMenuItem();
        JMenu helpMenu = new JMenu();
        JMenuItem aboutMenuItem = new JMenuItem();

        menuBar.setName("menuBar"); // NOI18N

        // File menu
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        rescanMenuItem.setAction(actionMap.get("rescan"));
        rescanMenuItem.setName("rescanMenuItem");
        fileMenu.add(rescanMenuItem);

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

    /**
     * Inner class to handle scanning the iPlayer Repository for files.
     */
    class BGRepScan extends SwingWorker<List<IPlayerDownload>, IPlayerDownload>
    {
        private final List<IPlayerDownload> downloads;
        private Status localStatus = Status.COMPLETE;

        public BGRepScan() {
            this.downloads = new ArrayList<IPlayerDownload>();
        }

        @Override
        public List<IPlayerDownload> doInBackground() {
            File repDir = new File(ITaskProperties.getInstance().get(ITaskProperties.REP_PATH));
            if(repDir.isDirectory()) {
                File[] folders = repDir.listFiles();
                ArrayList<File> temp = new ArrayList<File>(folders.length);

                /* Get rid of the cache directory */
                for(File f : folders) {
                    if(!f.getName().equalsIgnoreCase("cache"))
                        temp.add(f);
                }

                for(File f : temp) {
                    if(isCancelled())
                        break;
                    IPlayerDownload ipdl = IPlayerDownload.parse(f);
                    downloads.add(ipdl);
                    publish(ipdl);
                    setProgress((downloads.size() / temp.size()) * 100);
                }
            }
            return downloads;
        }

        @Override
        protected void process(List<IPlayerDownload> chunks) {
            for(IPlayerDownload ipdl : chunks) {
                statusAnimationLabel.setText(String.valueOf(dlCount++));
            }
        }

        @Override
        public void done() {
            FormLayout layout = new FormLayout("pref, 10dlu, pref:grow, 5dlu, pref", "");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            for(IPlayerDownload ipdl : downloads) {
                try {
                    builder.appendSeparator(ipdl.getTitle());
                    JLabel pic = new JLabel(new ImageIcon(ipdl.getIcon().getImage()
                            .getScaledInstance(320, -1, Image.SCALE_SMOOTH )));
                    builder.append(pic);
                    builder.append(ipdl.getTitle());
                    if(!ipdl.isComplete())
                        builder.append(new JLabel(resourceMap.getIcon("downloading.gif")));
                    builder.nextLine();
                } catch(NullPointerException npe) {
                    //ignore
                }
            }
            mainPanel.removeAll();
            mainPanel.add(builder.getPanel());
            statusAnimationLabel.setText(String.valueOf(downloads.size()));
            dlCount = 0;
            if(downloads.size() == 0) {
                localStatus = Status.NO_DOWNLOADS;
            } else {
                for(IPlayerDownload i : downloads) {
                    if(!i.isComplete())
                        localStatus = Status.DOWNLOADING;
                }
            }
            setStatus(localStatus);
            rescanMenuItem.setEnabled(true);
        }
    }
}
