package org.thesandbox.itask;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.thesandbox.itask.ITaskView.Status;
import org.thesandbox.itask.tasks.CloseITask;
import org.thesandbox.itask.tasks.ITask;
import org.thesandbox.itask.tasks.Shutdown;
import org.thesandbox.itask.tasks.Shutdown.Option;

/**
 * The iTask Configure Triggers dialog.
 * This class is a singleton, it is disposed of when closed however values
 * should persist between invocations in the same session - this allows tasks to
 * be configured and actioned instantly if desired.
 *
 * Created by IntelliJ IDEA.
 *
 * Date: 19-Dec-2009
 * @author jrxrs
 */
public class ITaskTriggers extends JDialog
{
    private static Logger logger = Logger.getLogger(ITaskTriggers.class.getCanonicalName());
    private static ITaskTriggers cInstance = null;

    private ResourceMap resourceMap;
    private ActionMap actionMap;

    private Vector<ITask> tasks;

    // GUI bits
    private Timer timerIn, timerAt;
    private JCheckBox timerInCheck, timerAtCheck;
    private JFormattedTextField timerInText, timerAtText;
    private JComboBox timerInCombo, timerAtCombo;
    private HashMap<CheckTrigger, JComboBox> tasksCombos;

    public static ITaskTriggers getInstance(Frame parent) {
        if(cInstance == null) {
            cInstance = new ITaskTriggers(parent);
        }
        return cInstance;
    }

    private ITaskTriggers(Frame parent) {
        super(parent);

        resourceMap = ITaskApp.getApplication().getContext().getResourceMap(this.getClass());
        actionMap = ITaskApp.getApplication().getContext().getActionMap(this.getClass(), this);

        timerIn = new Timer();
        timerAt = new Timer();

        initTasks();

        initComponents();
    }

    @Action
    public void setTriggers() {
        boolean dispose = true;
        try {
            if(timerInCheck.isSelected()) {
                long i = 60000 * Integer.parseInt(timerInText.getText());
                if(i < 1 || i > 999 * 60000) {
                    JOptionPane.showMessageDialog(this,
                            resourceMap.getString("warning.mins.text1") + "\n" +
                            resourceMap.getString("warning.mins.text2"),
                            resourceMap.getString("warning.mins.title"),
                            JOptionPane.WARNING_MESSAGE);
                    dispose = false;
                } else {
                    timerIn.cancel();
                    timerIn.purge();
                    timerIn = new Timer();
                    timerIn.schedule(new TimerITask((ITask)timerInCombo.getSelectedItem()), i);
                }
            }
            if(timerAtCheck.isSelected()) {
                int hour = Integer.parseInt(timerAtText.getText().substring(0, 2));
                int min = Integer.parseInt(timerAtText.getText().substring(3, 5));
                if(hour > 23 || min > 59) {
                    JOptionPane.showMessageDialog(this,
                            resourceMap.getString("warning.hhmm.text1") + "\n" +
                            resourceMap.getString("warning.hhmm.text2") + "\n" +
                            resourceMap.getString("warning.hhmm.text3"),
                            resourceMap.getString("warning.hhmm.title"),
                            JOptionPane.WARNING_MESSAGE);
                    dispose = false;
                } else {
                    Calendar now = Calendar.getInstance();
                    Calendar then = Calendar.getInstance();
                    then.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH),
                            now.get(Calendar.DATE), hour, min, 0);
                    if(now.compareTo(then) >= 0) {
                        then.roll(Calendar.DATE, true);
                    }
                    timerAt.cancel();
                    timerAt.purge();
                    timerAt = new Timer();
                    timerAt.schedule(new TimerITask((ITask)timerAtCombo.getSelectedItem()), then.getTime());
                }
            }
        } catch(NumberFormatException nfe) {
            logger.log(Level.WARNING, "Could not parse from format!", nfe);
        }
        if(dispose) {
            dispose();
        }
    }

    @Action
    public void cancelAllTriggers() {
        timerInCheck.setSelected(false);
        timerAtCheck.setSelected(false);
        for(CheckTrigger ct : tasksCombos.keySet()) {
            ct.setSelected(false);
        }
        dispose();
    }

    private void initTasks() {
        tasks = new Vector<ITask>();
        tasks.add(new CloseITask());
        tasks.add(new Shutdown(Option.SHUTDOWN));
        tasks.add(new Shutdown(Option.RESTART));
        tasks.add(new Shutdown(Option.LOG_OFF));
    }

    private JComboBox getTaskCombo() {
        return new JComboBox(tasks);
    }

    private MaskFormatter createFormat(String s) {
        MaskFormatter format = null;
        try {
            format = new MaskFormatter(s);
        } catch(ParseException pe) {
            logger.log(Level.WARNING, "Bad format specified.", pe);
        }
        return format;
    }

    public void newStatusSet(Status status) {
        for(CheckTrigger ct : tasksCombos.keySet()) {
            if(status.equals(ct.getMyStatus()) && ct.isSelected()) {
                ((ITask)tasksCombos.get(ct).getSelectedItem()).execute();
            }
        }
    }

    private void initComponents() {

        setIconImage((resourceMap.getImageIcon("window.icon")).getImage());

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(resourceMap.getString("title"));
        setModal(true);
        setName("triggersDialog");

        JButton setTriggersButton = new JButton();
        JButton cancelAllButton = new JButton();
        timerInCheck = new JCheckBox();
        timerInText = new JFormattedTextField(NumberFormat.getIntegerInstance());
        timerInCombo = getTaskCombo();
        timerAtCheck = new JCheckBox();
        timerAtText = new JFormattedTextField(createFormat("##:##"));
        timerAtCombo = getTaskCombo();
        
        CheckTrigger[] checks = {
                new CheckTrigger(Status.COMPLETE, resourceMap.getString("complete.text")),
                new CheckTrigger(Status.DOWNLOADING, resourceMap.getString("downloading.text")),
                new CheckTrigger(Status.STALE, resourceMap.getString("stale.text"))
        };

        tasksCombos = new HashMap<CheckTrigger, JComboBox>();

        setTriggersButton.setAction(actionMap.get("setTriggers"));
        setTriggersButton.setName("setTriggersButton");

        cancelAllButton.setAction(actionMap.get("cancelAllTriggers"));
        cancelAllButton.setName("cancelAllButton");

        // Build form
        FormLayout layout = new FormLayout(
                "pref, 5dlu, pref, 5dlu, pref", // cols (x)
                "");// rows (y)

        // Specify columns that have equal widths.
        layout.setColumnGroups(new int[][]{{1, 3, 5}});

        DefaultFormBuilder builder = new DefaultFormBuilder(layout);
        builder.setDefaultDialogBorder();

        builder.appendTitle(resourceMap.getString("timer.in.text"));
        builder.nextLine();
        builder.append(timerInCheck);
        builder.append(timerInText);
        builder.append(timerInCombo);
        builder.nextLine();

        builder.appendTitle(resourceMap.getString("timer.at.text"));
        builder.nextLine();
        builder.append(timerAtCheck);
        builder.append(timerAtText);
        builder.append(timerAtCombo);
        builder.nextLine();
        
        for(CheckTrigger ct : checks) {
            JComboBox tc = getTaskCombo();
            tasksCombos.put(ct, tc);
            builder.appendTitle(ct.getTitle());
            builder.nextLine();
            builder.append(ct);
            builder.append(new JPanel());
            builder.append(tc);
            builder.nextLine();
        }
        
        // Last Row
        builder.append(new JPanel());
        builder.append(setTriggersButton);
        builder.append(cancelAllButton);

        getContentPane().add(builder.getPanel());

        pack();
    }
    
    private class CheckTrigger extends JCheckBox
    {
        private Status myStatus;
        private String title;
        
        public CheckTrigger(Status myStatus, String title) {
            super();
            this.myStatus = myStatus;
            this.title =  title;
        }

        public Status getMyStatus() {
            return myStatus;
        }

        public String getTitle() {
            return title;
        }
    }

    private class TimerITask extends TimerTask
    {
        private ITask iTask;

        public TimerITask(ITask iTask) {
            this.iTask = iTask;
        }

        @Override
        public void run() {
            iTask.execute();
        }
    }
}