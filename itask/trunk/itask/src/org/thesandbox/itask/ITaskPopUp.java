package org.thesandbox.itask;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.jdesktop.application.Action;

import org.jdesktop.application.ResourceMap;
import org.thesandbox.itask.tasks.ITask;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * A pop up dialog to be displayed before a task is executed.
 *
 * Created by IntelliJ IDEA.
 *
 * Date: 22-Dec-2009
 * @author jrxrs
 */
public class ITaskPopUp extends JDialog
{
    private static Logger logger = Logger.getLogger(ITaskPopUp.class.getCanonicalName());
    private ResourceMap resourceMap;
    private ActionMap actionMap;

    private ITask iTask;
    private int exitVal = ELAPSED;
    public static final int ELAPSED = 0;
    public static final int NOW = 1;
    public static final int CANCEL = 2;

    /* GUI Bits */
    private JLabel countdown;
    private int counter;

    private Timer timer;

    public ITaskPopUp(ITask iTask) {
        this.iTask = iTask;

        resourceMap = ITaskApp.getApplication().getContext().getResourceMap(this.getClass());
        actionMap = ITaskApp.getApplication().getContext().getActionMap(this.getClass(), this);

        try {
            counter = Integer.parseInt(
                    ITaskProperties.getInstance().get(ITaskProperties.TIMEOUT_PERIOD));
        } catch(NumberFormatException nfe) {
            logger.log(Level.WARNING, "Timeout value could not be parsed!", nfe);
        }

        initComponents();
        initTimer();
    }

    @Action
    public void execNow() {
        timer.cancel();
        timer.purge();
        exitVal = NOW;
        dispose();
    }

    @Action
    public void cancel() {
        timer.cancel();
        timer.purge();
        exitVal = CANCEL;
        dispose();
    }

    public int getExitVal() {
        return exitVal;
    }

    private void initComponents() {
        setUndecorated(true);
        setModal(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JButton execNowButton = new JButton();
        JButton cancelButton =  new JButton();
        JLabel summary = new JLabel();
        countdown = new JLabel(String.valueOf(counter));

        execNowButton.setAction(actionMap.get("execNow"));
        execNowButton.setName("execNowButton");

        cancelButton.setAction(actionMap.get("cancel"));
        cancelButton.setName("cancelButton");

        summary.setText(resourceMap.getString("summary.text")
                .replace("%", iTask.getTaskName()));

        JPanel border = new JPanel();
        border.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.RED, 5),
                iTask.getTaskName(), TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
        JPanel jp = new JPanel(new BorderLayout());

        JPanel sum = new JPanel(new FlowLayout());
        sum.add(summary);
        sum.add(countdown);

        FormLayout layout = new FormLayout(
                "pref, 10dlu, pref",// cols (x)
                "pref, 3dlu, pref");// rows (y)

        // Specify columns that have equal widths.
        layout.setColumnGroups(new int[][]{{1, 3}});

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();

        CellConstraints cc = new CellConstraints();

        /* Row 1 */
        builder.addSeparator("", cc.xyw(1, 1, 3));

        /* Row 3 */
        builder.add(execNowButton, cc.xy(1, 3));
        builder.add(cancelButton, cc.xy(3, 3));

        jp.add(sum, BorderLayout.CENTER);
        jp.add(builder.getPanel(), BorderLayout.SOUTH);

        border.add(jp);
        getContentPane().add(border);
    }

    private void initTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new CountdownTimerTask(), 1000, 1000);
    }

    private class CountdownTimerTask extends TimerTask {

        @Override
        public void run() {
            counter--;
            if(counter == 0) {
                dispose();
            } else {
                countdown.setText(String.valueOf(counter));
            }
        }
    }
}
