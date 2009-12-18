package org.thesandbox.itask;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.thesandbox.itask.tasks.Shutdown;
import org.thesandbox.itask.tasks.WindowsShutdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.BreakIterator;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main iTask application frame.
 *
 * Created by IntelliJ IDEA.
 *
 * Date: 30-Nov-2009
 * @author jrxrs
 */
public class ITaskView extends FrameView
{
    private static Logger logger = Logger.getLogger(ITaskView.class.getCanonicalName());

    private ResourceMap resourceMap;
    private ActionMap actionMap;

    // GUI bits
    private JPanel mainPanel;
    private JProgressBar progressBar;
    private JLabel statusAnimationLabel, statusMessageLabel;
    private JMenuItem rescanMenuItem;
    private JScrollPane jsp;

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
        COMPLETE("status.DownloadingComplete.text"),
        STALE("status.StaleDownloads.text");

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
    public void configTrig() {
        new Thread(new WindowsShutdown(Shutdown.Option.RESTART,
                                       Shutdown.Option.FORCE)).start();
    }

    @Action
    public void iplayer() {
        desktopBrowse(resourceMap.getString("iplayer.url"));
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
            case STALE:
                progressBar.setValue(50);
                break;
        }
//        resetScrollPanel();
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
            if(duration > 29) {
                duration *= 1000;
                rescanTimer.scheduleAtFixedRate(new TimerScan(), duration, duration);
            } else {
                logger.log(Level.INFO, "Rescan duration was less than 30 seconds" +
                        ", auto rescan disabled.");
            }
        } catch(NumberFormatException nfe) {
            logger.log(Level.WARNING, ITaskProperties.RESCAN_PERIOD +
                    " property was not a number.", nfe);
        }
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

    private void desktopBrowse(String uri) {
        if(Desktop.isDesktopSupported()) {
            try {
            Desktop.getDesktop().browse(new URI(uri));
            } catch(URISyntaxException urise) {
                logger.log(Level.WARNING, "Incorrect URI", urise);
            } catch(IOException ioe) {
                logger.log(Level.WARNING, "General IO Error", ioe);
            }
        }
    }

    private void resetScrollPanel() {
        JScrollBar verticalScrollBar = jsp.getVerticalScrollBar();
        JScrollBar horizontalScrollBar = jsp.getHorizontalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMinimum());
        horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());
    }

    /* Adapted from: http://www.geekyramblings.net/2005/06/30/wrap-jlabel-text/ */
    private void wrapLabelText(JLabel label, String text, int width) {
        FontMetrics fm = label.getFontMetrics(label.getFont());

        BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(text);

        StringBuffer trial = new StringBuffer();
        StringBuffer real = new StringBuffer("<html>");

        int start = boundary.first();
        for (int end = boundary.next(); end != BreakIterator.DONE;
             start = end, end = boundary.next()) {
            String word = text.substring(start, end);
            trial.append(word);
            int trialWidth = SwingUtilities.computeStringWidth(fm,
                    trial.toString());
            if (trialWidth > width) {
                trial = new StringBuffer(word);
                real.append("<br>");
            }
            real.append(word);
        }

        real.append("</html>");

        label.setText(real.toString());
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

        jsp = new JScrollPane(mainPanel);
        JScrollBar jsb = jsp.getVerticalScrollBar();
        jsb.setUnitIncrement(75);
        setComponent(jsp);
        setMenuBar(getITaskViewMenuBar());
        setStatusBar(statusPanel);
    }

    private JMenuBar getITaskViewMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu();
        JMenuItem exitMenuItem = new JMenuItem();
        rescanMenuItem = new JMenuItem();
        JMenuItem configTrigMenuItem = new JMenuItem();
        JMenuItem iPlayerWebsiteMenuItem = new JMenuItem();
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

        configTrigMenuItem.setAction(actionMap.get("configTrig"));
        configTrigMenuItem.setName("configTrigMenuItem");
        fileMenu.add(configTrigMenuItem);

        iPlayerWebsiteMenuItem.setAction(actionMap.get("iplayer"));
        iPlayerWebsiteMenuItem.setName("iPlayerWebsiteMenuItem");
        fileMenu.add(iPlayerWebsiteMenuItem);

        fileMenu.addSeparator();

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
    class BGRepScan extends SwingWorker<HashMap<String, IPlayerDownload>, IPlayerDownload>
    {
        private final HashMap<String, IPlayerDownload> downloads;

        public BGRepScan() {
            this.downloads = new HashMap<String, IPlayerDownload>();
        }

        @Override
        public HashMap<String, IPlayerDownload> doInBackground() {
            File repDir = new File(ITaskProperties.getInstance().get(ITaskProperties.REP_PATH));
            if(repDir.isDirectory()) {
                File[] folders = repDir.listFiles();
                ArrayList<File> temp = new ArrayList<File>(folders.length);

                /* Ignore cache directory */
                for(File f : folders) {
                    if(!f.getName().equalsIgnoreCase("cache"))
                        temp.add(f);
                }

                for(File f : temp) {
                    if(isCancelled())
                        break;
                    IPlayerDownload ipdl = IPlayerDownload.parse(f);
                    downloads.put(ipdl.getTitle(), ipdl);
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
            Status localStatus = Status.COMPLETE;
            Object[] titles = downloads.keySet().toArray();
            Arrays.sort(titles);
            for(Object title : titles) {
                IPlayerDownload ipdl = downloads.get((String)title);
                try {
                    builder.appendSeparator(ipdl.getTitle());
                    JLabel pic = new JLabel(new ImageIcon(ipdl.getIcon().getImage()
                            .getScaledInstance(320, -1, Image.SCALE_SMOOTH )));
                    builder.append(pic);
                    JLabel sum = new JLabel();
                    wrapLabelText(sum, ipdl.getSummary(), 325);
                    builder.append(sum);
                    JLabel icon = new JLabel();
                    final String url = ipdl.getUrl();
                    icon.addMouseListener(new MouseAdapter(){

                        @Override
                        public void mouseClicked(MouseEvent me) {
                            desktopBrowse(url);
                        }
                    });
                    if(ipdl.isComplete()) {
                        icon.setIcon(resourceMap.getIcon("complete.icon"));
                    } else {
                        if(ipdl.isStale()) {
                            localStatus = Status.STALE;
                            icon.setIcon(resourceMap.getIcon("stale.icon"));
                        } else {
                            localStatus = localStatus == Status.STALE ?
                                    Status.STALE : Status.DOWNLOADING;
                            icon.setIcon(resourceMap.getIcon("downloading.gif"));
                        }
                    }
                    builder.append(icon);
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
            }
            setStatus(localStatus);
            rescanMenuItem.setEnabled(true);
        }
    }
}
