package org.thesandbox.itask;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;

/**
 * The iTask About box.
 *
 * Created by IntelliJ IDEA.
 *
 * Date: 30-Nov-2009
 * @author jrxrs
 */
public class ITaskSettings extends JDialog
{
    private ResourceMap resourceMap;
    private ActionMap actionMap;

    // GUI bits
    private JFileChooser jfc;
    private JButton saveButton;
    private JTextField repPath;
    private JFormattedTextField rescanDuration, stalePeriod;

    public int disposeAction;
    public static final int SAVE    = 1;
    public static final int CANCEL  = 0;

    public ITaskSettings(Frame parent) {
        super(parent);

        resourceMap = ITaskApp.getApplication().getContext().getResourceMap(this.getClass());
        actionMap = ITaskApp.getApplication().getContext().getActionMap(this.getClass(), this);
        disposeAction = CANCEL;

        initComponents();
        getRootPane().setDefaultButton(saveButton);
    }

    @Action
    public void browsePath() {
        String t = ITaskProperties.getInstance().get(ITaskProperties.REP_PATH);
        if(!ITaskProperties.NOT_SET.equals(t)) {
            jfc.setCurrentDirectory(new File(t));
        }
        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File chosen = jfc.getSelectedFile();
            repPath.setText(chosen.getPath());
        }
    }

    @Action
    public void saveSettings() {
        writeSettings();
        disposeAction = SAVE;
        dispose();
    }

    @Action
    public void cancelSettings() {
        disposeAction = CANCEL;
        dispose();
    }

    public int getDisposeAction() {
        return disposeAction;
    }

    private void writeSettings() {
        ITaskProperties.getInstance().set(
                ITaskProperties.REP_PATH, repPath.getText(), false);
        ITaskProperties.getInstance().set(
                ITaskProperties.RESCAN_PERIOD, rescanDuration.getText(), false);
        ITaskProperties.getInstance().set(
                ITaskProperties.STALE_PERIOD, stalePeriod.getText(), true);
    }

    private void initComponents() {

        setIconImage((resourceMap.getImageIcon("window.icon")).getImage());

        jfc = new JFileChooser(System.getProperty("user.home"));
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setMultiSelectionEnabled(false);
        jfc.setDialogTitle(resourceMap.getString("browsePath.title"));

        saveButton = new JButton();
        JButton cancelButton = new JButton();
        JLabel repPathLabel = new JLabel();
        JButton browsePath = new JButton();
        JLabel rescanDurationLabel = new JLabel();
        JLabel stalePeriodLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(resourceMap.getString("title"));
        setModal(true);
        setName("settingsDialog");

        saveButton.setAction(actionMap.get("saveSettings"));
        saveButton.setName("saveButton");

        cancelButton.setAction(actionMap.get("cancelSettings"));
        cancelButton.setName("cancelSettings");

        browsePath.setAction(actionMap.get("browsePath"));
        browsePath.setName("browsePath");

        repPathLabel.setFont(repPathLabel.getFont().deriveFont(repPathLabel.getFont().getStyle() | java.awt.Font.BOLD));
        repPathLabel.setText(resourceMap.getString("repPathLabel.text"));
        repPathLabel.setName("repPathLabel");

        rescanDurationLabel.setFont(rescanDurationLabel.getFont().deriveFont(rescanDurationLabel.getFont().getStyle() | java.awt.Font.BOLD));
        rescanDurationLabel.setText(resourceMap.getString("rescanDurationLabel.text"));
        rescanDurationLabel.setName("rescanDurationLabel");

        String seconds = resourceMap.getString("secondsLabel.text");

        stalePeriodLabel.setFont(stalePeriodLabel.getFont().deriveFont(stalePeriodLabel.getFont().getStyle() | java.awt.Font.BOLD));
        stalePeriodLabel.setText(resourceMap.getString("stalePeriodLabel.text"));
        stalePeriodLabel.setName("stalePeriodLabel");

        repPath = new JTextField(ITaskProperties.getInstance().get(ITaskProperties.REP_PATH));
        rescanDuration = new JFormattedTextField(NumberFormat.getIntegerInstance());
        rescanDuration.setText(ITaskProperties.getInstance().get(ITaskProperties.RESCAN_PERIOD));
        stalePeriod = new JFormattedTextField(NumberFormat.getIntegerInstance());
        stalePeriod.setText(ITaskProperties.getInstance().get(ITaskProperties.STALE_PERIOD));

        Container c = getContentPane();

        // Build form
        FormLayout layout = new FormLayout(
                "right:pref, 3dlu, 30dlu, 3dlu, pref, 3dlu, pref:grow, 3dlu, pref, 3dlu, pref", // cols (x)
                "pref, 5dlu, pref, 5dlu, pref, 5dlu, pref, 5dlu, pref, 5dlu, pref");            // rows (y)

        // Specify columns that have equal widths.
        layout.setColumnGroups(new int[][]{{9, 11}});

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();

        CellConstraints cc = new CellConstraints();

        // Row 1
        builder.add(repPathLabel, cc.xy(1, 1));
        builder.add(repPath, cc.xyw(3, 1, 7));
        builder.add(browsePath, cc.xy(11, 1));

        // Row 3
        builder.addSeparator("", cc.xyw(1, 3, 11));

        // Row 5
        builder.add(rescanDurationLabel, cc.xy(1, 5));
        builder.add(rescanDuration, cc.xy(3, 5));
        builder.addLabel(seconds, cc.xy(5, 5));

        // Row 7
        builder.add(stalePeriodLabel, cc.xy(1, 7));
        builder.add(stalePeriod, cc.xy(3, 7));
        builder.addLabel(seconds, cc.xy(5, 7));

        // Row 9
        builder.addSeparator("", cc.xyw(1, 9, 11));

        // Row 11
        builder.add(saveButton, cc.xy(9, 11));
        builder.add(cancelButton, cc.xy(11, 11));

        c.add(builder.getPanel());

        pack();
    }
}
