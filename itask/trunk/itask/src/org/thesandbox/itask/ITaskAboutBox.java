package org.thesandbox.itask;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;

import javax.swing.*;
import java.awt.*;

/**
 * The iTask About box.
 *
 * Created by IntelliJ IDEA.
 *
 * @author jrxrs
 * @date 30-Nov-2009
 */
public class ITaskAboutBox extends javax.swing.JDialog
{
    private ResourceMap resourceMap;
    private ActionMap actionMap;
    private JButton closeButton;
    
    public ITaskAboutBox(java.awt.Frame parent) {
        super(parent);

        resourceMap = ITaskApp.getApplication().getContext().getResourceMap(this.getClass());
        actionMap = ITaskApp.getApplication().getContext().getActionMap(this.getClass(), this);

        initComponents();
        getRootPane().setDefaultButton(closeButton);
    }

    @Action
    public void closeAboutBox() {
        dispose();
    }

    private void gbAdd(JComponent parent, GridBagLayout gridbag,
                       GridBagConstraints c, Component comp) {
         gridbag.setConstraints(comp, c);
         parent.add(comp);
     }


    private void initComponents() {

        closeButton = new JButton();
        JLabel appTitleLabel = new JLabel();
        JLabel versionLabel = new JLabel();
        JLabel appVersionLabel = new JLabel();
        JLabel vendorLabel = new JLabel();
        JLabel appVendorLabel = new JLabel();
        JLabel homepageLabel = new JLabel();
        JLabel appHomepageLabel = new JLabel();
        JLabel appDescLabel = new JLabel();
        JLabel imageLabel = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(resourceMap.getString("title")); // NOI18N
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);

        closeButton.setAction(actionMap.get("closeAboutBox")); // NOI18N
        closeButton.setName("closeButton"); // NOI18N

        appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | java.awt.Font.BOLD, appTitleLabel.getFont().getSize()+4));
        appTitleLabel.setText(resourceMap.getString("Application.title")); // NOI18N
        appTitleLabel.setName("appTitleLabel"); // NOI18N

        versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | java.awt.Font.BOLD));
        versionLabel.setText(resourceMap.getString("versionLabel.text")); // NOI18N
        versionLabel.setName("versionLabel"); // NOI18N

        appVersionLabel.setText(resourceMap.getString("Application.version")); // NOI18N
        appVersionLabel.setName("appVersionLabel"); // NOI18N

        vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | java.awt.Font.BOLD));
        vendorLabel.setText(resourceMap.getString("vendorLabel.text")); // NOI18N
        vendorLabel.setName("vendorLabel"); // NOI18N

        appVendorLabel.setText(resourceMap.getString("Application.vendor")); // NOI18N
        appVendorLabel.setName("appVendorLabel"); // NOI18N

        homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | java.awt.Font.BOLD));
        homepageLabel.setText(resourceMap.getString("homepageLabel.text")); // NOI18N
        homepageLabel.setName("homepageLabel"); // NOI18N

        appHomepageLabel.setText(resourceMap.getString("appHomepageLabel.text")); // NOI18N
        appHomepageLabel.setName("appHomepageLabel"); // NOI18N

        appDescLabel.setText(resourceMap.getString("appDescLabel.text")); // NOI18N
        appDescLabel.setName("appDescLabel"); // NOI18N

        imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon")); // NOI18N
        imageLabel.setName("imageLabel"); // NOI18N

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(new JPanel(), BorderLayout.NORTH);
        c.add(imageLabel, BorderLayout.WEST);

        JPanel d = new JPanel(new BorderLayout());
        JPanel titleAndDesc = new JPanel(new GridLayout(2, 1));
        titleAndDesc.add(appTitleLabel);
        titleAndDesc.add(appDescLabel);
        d.add(titleAndDesc, BorderLayout.NORTH);

        GridBagLayout gb = new GridBagLayout();
        JPanel e = new JPanel(gb);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
        gbAdd(e, gb, gbc, versionLabel);
        gbAdd(e, gb, gbc, appVersionLabel);

        gbc.gridwidth = GridBagConstraints.REMAINDER; //end row
        gbAdd(e, gb, gbc, new JPanel());

        gbc.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
        gbAdd(e, gb, gbc, vendorLabel);
        gbAdd(e, gb, gbc, appVendorLabel);

        gbc.gridwidth = GridBagConstraints.REMAINDER; //end row
        gbAdd(e, gb, gbc, new JPanel());

        gbc.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
        gbAdd(e, gb, gbc, homepageLabel);
        gbc.gridwidth = GridBagConstraints.REMAINDER; //end row
        gbAdd(e, gb, gbc, appHomepageLabel);

        d.add(e, BorderLayout.CENTER);

        JPanel f = new JPanel(new GridLayout(1, 4));
        f.add(new JPanel());
        f.add(new JPanel());
        f.add(new JPanel());
        f.add(closeButton);
        d.add(f, BorderLayout.SOUTH);

        c.add(d, BorderLayout.CENTER);
        c.add(new JPanel(), BorderLayout.EAST);
        c.add(new JPanel(), BorderLayout.SOUTH);

        setPreferredSize(new Dimension(450, 200));

        pack();
    }
}
