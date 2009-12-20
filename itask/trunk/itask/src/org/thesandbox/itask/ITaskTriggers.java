package org.thesandbox.itask;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

import org.thesandbox.itask.ITaskView.Status;
import org.thesandbox.itask.tasks.CloseITask;
import org.thesandbox.itask.tasks.ITask;
import org.thesandbox.itask.tasks.Shutdown;
import org.thesandbox.itask.tasks.Shutdown.Option;

/**
 * The iTask About box.
 *
 * Created by IntelliJ IDEA.
 *
 * Date: 19-Dec-2009
 * @author jrxrs
 */
public class ITaskTriggers extends JDialog
{
    private ResourceMap resourceMap;
    private ActionMap actionMap;

    private Vector<ITask> tasks;

    // GUI bits
    private JCheckBox timerIn, timerAt;
    private JFormattedTextField timerInJFTF, timerAtJFTF;
    private JComboBox timerInCB, timerAtCB;
    private HashMap<CheckTrigger, JComboBox> tasksCombos;

    public ITaskTriggers(Frame parent) {
        super(parent);

        resourceMap = ITaskApp.getApplication().getContext().getResourceMap(this.getClass());
        actionMap = ITaskApp.getApplication().getContext().getActionMap(this.getClass(), this);

        initTasks();

        initComponents();
    }

    @Action
    public void setTriggers() {

    }

    @Action
    public void cancelAllTriggers() {

    }

    private void initTasks() {
        tasks = new Vector<ITask>();
        tasks.add(new Shutdown(Option.SHUTDOWN));
        tasks.add(new Shutdown(Option.RESTART));
        tasks.add(new Shutdown(Option.LOG_OFF));
        tasks.add(new CloseITask());
    }

    private JComboBox getTaskCombo() {
        return new JComboBox(tasks);
    }

    private void initComponents() {

        setIconImage((resourceMap.getImageIcon("window.icon")).getImage());

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(resourceMap.getString("title"));
        setModal(true);
        setName("triggersDialog");

        JButton setTriggersButton = new JButton();
        JButton cancelAllButton = new JButton();
        timerIn = new JCheckBox();
        timerInJFTF = new JFormattedTextField();
        timerInCB = getTaskCombo();
        timerAt = new JCheckBox();
        timerAtJFTF = new JFormattedTextField();
        timerAtCB = getTaskCombo();
        
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

        Container c = getContentPane();

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
        builder.append(timerIn);
        builder.append(timerInJFTF);
        builder.append(timerInCB);
        builder.nextLine();

        builder.appendTitle(resourceMap.getString("timer.at.text"));
        builder.nextLine();
        builder.append(timerAt);
        builder.append(timerAtJFTF);
        builder.append(timerAtCB);
        builder.nextLine();
        
        for(CheckTrigger tt : checks) {
            JComboBox tc = getTaskCombo();
            tasksCombos.put(tt, tc);
            builder.appendTitle(tt.getTitle());
            builder.nextLine();
            builder.append(tt);
            builder.append(new JPanel());
            builder.append(tc);
            builder.nextLine();
        }
        
        // Last Row
        builder.append(new JPanel());
        builder.append(setTriggersButton);
        builder.append(cancelAllButton);

        c.add(builder.getPanel());

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
}