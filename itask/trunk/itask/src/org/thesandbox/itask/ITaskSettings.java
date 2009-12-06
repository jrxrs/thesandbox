package org.thesandbox.itask;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The iTask About box.
 *
 * Created by IntelliJ IDEA.
 *
 * @author jrxrs
 * @date 30-Nov-2009
 */
public class ITaskSettings extends JDialog
{
    private ResourceMap resourceMap;
    private ActionMap actionMap;

    // GUI bits
    private JFileChooser jfc;
    private JButton saveButton, cancelButton;
    private JTextField repPath;

    public ITaskSettings(Frame parent) {
        super(parent);

        resourceMap = ITaskApp.getApplication().getContext().getResourceMap(this.getClass());
        actionMap = ITaskApp.getApplication().getContext().getActionMap(this.getClass(), this);

        initComponents();
        getRootPane().setDefaultButton(saveButton);
    }

    @Action
    public void browsePath() {
        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File chosen = jfc.getSelectedFile();
            repPath.setText(chosen.getPath());
        }
    }

    @Action
    public void saveSettings() {
        writeSettings();
        dispose();
    }

    @Action
    public void cancelSettings() {
        dispose();
    }

    private void writeSettings() {
        ITaskProperties.getInstance().set(
                ITaskProperties.REP_PATH, repPath.getText(), false);
    }

    private void initComponents() {

        setIconImage(((ImageIcon)resourceMap.getIcon("window.icon")).getImage());

        jfc = new JFileChooser(System.getProperty("user.home"));
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setMultiSelectionEnabled(false);
        jfc.setDialogTitle(resourceMap.getString("browsePath.title"));

        saveButton = new JButton();
        cancelButton = new JButton();
        JLabel repPathLabel = new JLabel();
        JButton browsePath = new JButton();

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

        repPath = new JTextField(ITaskProperties.getInstance().get(ITaskProperties.REP_PATH));

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // Build form
        FormLayout layout = new FormLayout(
                "right:pref, 3dlu, 150px:grow, 3dlu, pref, 3dlu, pref", // cols (x)
                "p, 9dlu, p");                                          // rows (y)

        // Specify that columns 1 & 5 as well as 3 & 7 have equal widths.
        layout.setColumnGroups(new int[][]{{5, 7}});

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();

        CellConstraints cc = new CellConstraints();

        // Row 1
        builder.add(repPathLabel, cc.xy(1, 1));
        builder.add(repPath, cc.xyw(3, 1, 3));
        builder.add(browsePath, cc.xy(7, 1));

        // Row 3
        builder.add(saveButton, cc.xy(5, 3));
        builder.add(cancelButton, cc.xy(7, 3));

        c.add(builder.getPanel(), BorderLayout.CENTER);

//        setPreferredSize(new Dimension(500, 500));

        pack();
    }
}
