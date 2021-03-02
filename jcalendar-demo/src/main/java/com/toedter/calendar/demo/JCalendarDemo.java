/*
 *  JCalendarDemo.java - Demonstration of JCalendar Java Bean
 *  Copyright (C) 2004 Kai Toedter
 *  kai@toedter.com
 *  www.toedter.com
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package com.toedter.calendar.demo;

import com.toedter.calendar.DateVerifier;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JHourMinuteChooserWithCurrentTime;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.components.JSpinField;
import com.toedter.components.JTitlePanel;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A demonstration JFrame for the JCalendar bean. The demo can be started as
 * Java application.
 *
 * @author Kai Toedter
 * @version $LastChangedRevision: 103 $
 * @vubersion $LastChangedDate: 2006-06-04 14:57:02 +0200 (So, 04 Jun 2006) $
 */
public class JCalendarDemo extends JFrame implements PropertyChangeListener {

    private static final long serialVersionUID = 6739986412544494316L;

    private static Logger logger = Logger.getLogger(JCalendarDemo.class.getName());

    private JComponent[] beans;
    //private JPanel propertyPanel;
    private JPanel componentPanel;
    private JPanel calendarPanel;
    private DateVerifier dateVerifier = new DateChooserPanel.TestDateVerifier();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTitlePanel componentTitlePanel;
    private JMenuBar menuBar;
    private JPanel propertyPanel;
    private JSplitPane splitPane;
    private JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form JCalendarDemo
     *
     * @param title Frame title
     */
    public JCalendarDemo(String title) {
        super(title);
        // Set the JGoodies Plastic 3D look and feel
        initializeLookAndFeels();

        // initialize all beans to demo
        beans = new JComponent[7];
        beans[0] = new DateChooserPanel();
        beans[1] = new JCalendar();
        beans[2] = new JDayChooser();
        beans[3] = new JMonthChooser();
        beans[4] = new JYearChooser();
        beans[5] = new JSpinField();
        beans[6] = new JHourMinuteChooserWithCurrentTime();
//        beans[6].setName("JHourMinuteChooser");

        ((JSpinField) beans[5]).adjustWidthToMaximumValue();
        ((JYearChooser) beans[4]).setMaximum(((JSpinField) beans[5]).getMaximum());
        ((JYearChooser) beans[4]).adjustWidthToMaximumValue();
        initComponents();
        installBean(beans[0]);
    }

    /**
     * Installs the JGoodies Look & Feels, if available, in classpath.
     */
    public final void initializeLookAndFeels() {
        // if in classpath try to load JGoodies Plastic Look & Feel
        try {
            LookAndFeelInfo[] lnfs = UIManager.getInstalledLookAndFeels();
            boolean found = false;
            for (LookAndFeelInfo lnf : lnfs) {
                if (lnf.getName().equals("JGoodies Plastic 3D")) {
                    found = true;
                }
            }
            if (!found) {
                UIManager.installLookAndFeel("JGoodies Plastic 3D",
                        "com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
            }
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
//			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException t) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                logger.log(Level.INFO, "Problem loading look and feel", e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        propertyPanel = new JPanel();
        toolBar = new JToolBar();
        populateToolBar();
        splitPane = new JSplitPane();
        BasicSplitPaneDivider divider = ((BasicSplitPaneUI) splitPane.getUI()).getDivider();

        if (divider != null) {
            divider.setBorder(null);
        }
        URL componentIconURL = beans[0].getClass().getResource("images/" + beans[0].getName() + "Color16.gif");
        System.out.println("loading component icon:" + componentIconURL);
        ImageIcon componentIcon = new ImageIcon(componentIconURL);
        componentPanel = new JPanel();
        componentTitlePanel = new JTitlePanel("Component", componentIcon, componentPanel, BorderFactory.createEmptyBorder(4, 4, 0, 4));
        JScrollPane scrolledPropertyPanel = new JScrollPane();
        JTitlePanel propertyTitlePanel = new JTitlePanel("Properties", null, propertyPanel, BorderFactory.createEmptyBorder(4, 4, 4, 4))
        ;
        menuBar = new JMenuBar();
        // Menu for all beans to demo
        JMenu componentsMenu = new JMenu("Components")
        ;
        populateComponentsMenu(componentsMenu);
        // Menu for the look and feels (lnfs).
        JMenu lnfMenu = new JMenu();
        populateLookAndFeelMenu(lnfMenu);
        // the help menu
        JMenu helpMenu = new JMenu();
        JMenuItem aboutItem = helpMenu.add(new AboutAction(this));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setName("Components Toolbar"); // NOI18N
        getContentPane().add(toolBar, BorderLayout.NORTH);

        splitPane.setBorder(BorderFactory.createLineBorder(Color.gray));
        splitPane.setDividerSize(4);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(1.0);
        splitPane.setTopComponent(componentTitlePanel);

        scrolledPropertyPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrolledPropertyPanel.setViewportView(propertyTitlePanel);
        scrolledPropertyPanel.setViewportView(propertyTitlePanel);

        splitPane.setRightComponent(scrolledPropertyPanel);

        getContentPane().add(splitPane, BorderLayout.CENTER);

        componentsMenu.setMnemonic('C');
        componentsMenu.setText("Components");
        menuBar.add(componentsMenu);

        lnfMenu.setMnemonic('L');
        lnfMenu.setText("Look&Feel");
        menuBar.add(lnfMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");

        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        aboutItem.setMnemonic('A');
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Installs a demo bean.
     *
     * @param bean the demo bean
     */
    private void installBean(JComponent bean) {
        try {
            componentPanel.removeAll();
            componentPanel.add(bean);

            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), bean.getClass()
                    .getSuperclass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            propertyPanel.removeAll();

            GridBagLayout gridbag = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;

            propertyPanel.setLayout(gridbag);

            installBeanByType(propertyDescriptors, bean, gridbag);

            URL iconURL = bean.getClass().getResource("images/" + bean.getName() + "Color16.gif");
            ImageIcon icon = new ImageIcon(iconURL);

            componentTitlePanel.setTitle(bean.getName(), icon);
            bean.invalidate();
            propertyPanel.invalidate();
            componentPanel.invalidate();
            componentPanel.repaint();
        } catch (IntrospectionException e) {
            logger.log(Level.SEVERE, "Problem loading graphic component", e);
        }
    }

    private void installBeanByType(PropertyDescriptor[] propertyDescriptors, JComponent bean, GridBagLayout gridbag) {

        String[] types = new String[]{"class java.util.Locale", "boolean", "interface com.toedter.calendar.DateVerifier",
            "int", "class java.awt.Color", "class java.util.Date", "class java.lang.String"};

        for (String type : types) {
            for (PropertyDescriptor propertyDescriptor1 : propertyDescriptors) {
                if (propertyDescriptor1.getWriteMethod() != null) {
                    String propertyType = propertyDescriptor1.getPropertyType().toString();
                    final PropertyDescriptor propertyDescriptor = propertyDescriptor1;
                    final JComponent currentBean = bean;
                    final Method readMethod = propertyDescriptor.getReadMethod();
                    final Method writeMethod = propertyDescriptor.getWriteMethod();
                    if (propertyType.equals(type) && (((readMethod != null) && (writeMethod != null)) || ("class java.util.Locale"
                            .equals(propertyType)
                            || "interface com.toedter.calendar.DateVerifier".equals(propertyType)))) {
                        switch (propertyType) {
                            case "boolean": {
                                setBooleanProperty(readMethod, bean, writeMethod, currentBean, propertyDescriptor1, gridbag);
                                break;
                            }
                            case "int": {
                                setIntProperty(writeMethod, currentBean, readMethod, bean, propertyDescriptor1, gridbag);
                                break;
                            }
                            case "class java.lang.String": {
                                SetStringProperty(readMethod, bean, writeMethod, currentBean, propertyDescriptor1, gridbag);
                                break;
                            }
                            case "class java.util.Locale": {
                                setLocaleProperty(bean, propertyDescriptor1, gridbag);
                                break;
                            }
                            case "interface com.toedter.calendar.DateVerifier": {
                                setDateVerifierProperty(readMethod, bean, writeMethod, currentBean, propertyDescriptor1, gridbag);
                                break;
                            }
                            case "class java.util.Date": {
                                setDateProperty(readMethod, bean, writeMethod, currentBean, propertyDescriptor1, gridbag);
                                break;
                            }
                            case "class java.awt.Color": {
                                setColorProperty(readMethod, bean, writeMethod, currentBean, propertyDescriptor1, gridbag);
                                break;
                            }
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    private void setBooleanProperty(final Method readMethod, JComponent bean, final Method writeMethod, final JComponent currentBean, PropertyDescriptor propertyDescriptor1, GridBagLayout gridbag) {
        boolean isSelected = false;
        try {
            isSelected = ((Boolean) readMethod.invoke(bean, null));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.log(Level.SEVERE, "Problem getting value of checkbox", e);
        }
        final JCheckBox checkBox = new JCheckBox("", isSelected);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (checkBox.isSelected()) {
                        writeMethod.invoke(currentBean,
                                Boolean.TRUE);
                    } else {
                        writeMethod.invoke(currentBean,
                                Boolean.FALSE);
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    logger.log(Level.SEVERE, "Problem setting value of checkbox", e);
                }
            }
        });
        addProperty(propertyDescriptor1, checkBox, gridbag);
    }

    private void setIntProperty(final Method writeMethod, final JComponent currentBean, final Method readMethod, JComponent bean, PropertyDescriptor propertyDescriptor1, GridBagLayout gridbag) {
        JSpinField spinField = new JSpinField();
        spinField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    if (evt.getPropertyName().equals("value")) {
                        writeMethod.invoke(currentBean, evt
                                .getNewValue());
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    logger.log(Level.SEVERE, "Problem writing property of type int", e);
                }
            }
        });
        try {
            Integer integerObj = ((Integer) readMethod.invoke(bean, null));
            spinField.setValue(integerObj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.log(Level.SEVERE, "Problem reading property of type Integer", e);
        }
        addProperty(propertyDescriptor1, spinField, gridbag);
    }

    private void SetStringProperty(final Method readMethod, JComponent bean, final Method writeMethod, final JComponent currentBean, PropertyDescriptor propertyDescriptor1, GridBagLayout gridbag) {
        String string = "";
        try {
            string = ((String) readMethod.invoke(bean, null));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.log(Level.SEVERE, "Problem reading property of type String", e);
        }
        JTextField textField = new JTextField(string);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writeMethod.invoke(currentBean, e
                            .getActionCommand());
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                }
            }
        };
        textField.addActionListener(actionListener);
        addProperty(propertyDescriptor1, textField, gridbag);
        return;
    }

    private void setDateVerifierProperty(final Method readMethod, JComponent bean, final Method writeMethod, final JComponent currentBean, PropertyDescriptor propertyDescriptor1, GridBagLayout gridbag) {
        boolean isSelected = false;
        try {
            isSelected = readMethod.invoke(bean, null) != null;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.log(Level.SEVERE, "Problem reading JComponent bean", e);
        }
        final JCheckBox checkBox = new JCheckBox("", isSelected);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (checkBox.isSelected()) {
                        writeMethod.invoke(currentBean,
                                dateVerifier);
                    } else {
                        writeMethod.invoke(currentBean,
                                new Object[]{null});
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    logger.log(Level.SEVERE, "Problem setting date verifier", e);
                }
            }
        });
        addProperty(propertyDescriptor1, checkBox, gridbag);
    }

    private void setColorProperty(final Method readMethod, JComponent bean, final Method writeMethod, final JComponent currentBean, PropertyDescriptor propertyDescriptor1, GridBagLayout gridbag) {
        final JButton button = new JButton();
        try {
            final Color colorObj = ((Color) readMethod.invoke(bean, null));
            button.setText("...");
            button.setBackground(colorObj);
            
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color newColor = JColorChooser.showDialog(
                            JCalendarDemo.this, "Choose Color", colorObj);
                    button.setBackground(newColor);
                    
                    try {
                        writeMethod.invoke(currentBean,
                                newColor);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                        logger.log(Level.SEVERE, "Problem setting color", e);
                    }
                }
            };
            
            button.addActionListener(actionListener);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.log(Level.SEVERE, "Problem reading color", e);
        }
        addProperty(propertyDescriptor1, button, gridbag);
    }

    private void setDateProperty(final Method readMethod, JComponent bean, final Method writeMethod, final JComponent currentBean, PropertyDescriptor propertyDescriptor1, GridBagLayout gridbag) {
        Date date = null;
        try {
            date = ((Date) readMethod.invoke(bean, null));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.log(Level.SEVERE, "Problem reading property of type Date", e);
        }
        JDateChooser dateChooser = new JDateChooser(date);
        dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    if (evt.getPropertyName().equals("date")) {
                        writeMethod.invoke(currentBean, evt
                                .getNewValue());
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    logger.log(Level.SEVERE, "Problem writing property of type Date", e);
                }
            }
        });
        addProperty(propertyDescriptor1, dateChooser, gridbag);
    }

    private void setLocaleProperty(JComponent bean, PropertyDescriptor propertyDescriptor1, GridBagLayout gridbag) {
        JLocaleChooser localeChooser = new JLocaleChooser(bean);
        localeChooser.setPreferredSize(new Dimension(200, localeChooser
                .getPreferredSize().height));
        addProperty(propertyDescriptor1, localeChooser, gridbag);
    }

    private void addProperty(PropertyDescriptor propertyDescriptor, JComponent editor,
            GridBagLayout grid) {
        String text = propertyDescriptor.getDisplayName();
        final int textLength = text.length();
        StringBuilder newText = new StringBuilder(textLength);

        for (int i = 0; i < textLength; i++) {
            char c = text.charAt(i);

            if (((c >= 'A') && (c <= 'Z')) || (i == 0)) {
                if (i == 0) {
                    c += ('A' - 'a');
                }

                newText.append(' ').append(c);
            } else {
                newText.append(c);
            }
        }

        newText.append(": ");

        JLabel label = new JLabel(newText.toString(), null, JLabel.RIGHT);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        grid.setConstraints(label, c);
        propertyPanel.add(label);
        c.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(editor, c);
        propertyPanel.add(editor);

        JPanel blankLine = new JPanel() {
            private static final long serialVersionUID = 4514530330521503732L;

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(10, 2);
            }
        };
        grid.setConstraints(blankLine, c);
        propertyPanel.add(blankLine);
    }

    /**
     * The applet is a PropertyChangeListener for "locale" and "calendar".
     *
     * @param evt Description of the Parameter
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (calendarPanel != null && evt.getPropertyName().equals("calendar")) {
            // calendar = (Calendar) evt.getNewValue();
            // DateFormat df = DateFormat.getDateInstance(DateFormat.LONG,
            // jcalendar.getLocale());
            // dateField.setText(df.format(calendar.getTime()));
        }
    }

    private void populateToolBar() {
        toolBar.putClientProperty("jgoodies.headerStyle", "Both");

        for (JComponent bean1 : beans) {
            Icon icon;
            JButton button;
            try {
                final JComponent bean = bean1;
                String path = "images/" + bean.getName() + "Color16.gif";
                URL iconURL = bean.getClass().getResource(path);
                System.out.println("loading toolbar component icon:" + path);
                icon = new ImageIcon(iconURL);
                button = new JButton(icon);
                ActionListener actionListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        installBean(bean);
                    }
                };
                button.addActionListener(actionListener);
            } catch (Exception e) {
                System.err.println("JCalendarDemo.createToolBar(): " + e + "\n" + bean1);
                e.printStackTrace();
                button = new JButton(bean1.getName());
            }
            button.setFocusPainted(false);
            toolBar.add(button);
        }

    }

    private void populateComponentsMenu(JMenu componentsMenu) {
        for (JComponent bean1 : beans) {
            Icon icon;
            JMenuItem menuItem;
            URL iconURL = null;
            try {
                String path = "images/" + bean1.getName() + "Color16.gif";
                System.out.println("path:" + path);
                iconURL = bean1.getClass().getResource(path);
                icon = new ImageIcon(iconURL);
                menuItem = new JMenuItem(bean1.getName(), icon);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "JCalendarDemo.createMenuBar() for URL: " + iconURL, e);
                menuItem = new JMenuItem(bean1.getName());
            }
            componentsMenu.add(menuItem);
            final JComponent bean = bean1;
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    installBean(bean);
                }
            };
            menuItem.addActionListener(actionListener);
        }
    }

    private void populateLookAndFeelMenu(JMenu lnfMenu) {
        UIManager.LookAndFeelInfo[] lnfs = UIManager.getInstalledLookAndFeels();
        ButtonGroup lnfGroup = new ButtonGroup();
        for (LookAndFeelInfo lnf : lnfs) {
            if (!lnf.getName().equals("CDE/Motif")) {
                JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(lnf.getName());
                lnfMenu.add(rbmi);
                // preselect the current Look & feel
                rbmi.setSelected(UIManager.getLookAndFeel().getName().equals(lnf.getName()));
                // store lool & feel info as client property
                rbmi.putClientProperty("lnf name", lnf);
                // create and add the item listener
                rbmi.addItemListener(
                        // inlining
                        new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        JRadioButtonMenuItem rbmi2 = (JRadioButtonMenuItem) ie.getSource();

                        if (rbmi2.isSelected()) {
                            // get the stored look & feel info
                            UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) rbmi2
                                    .getClientProperty("lnf name");

                            try {
                                menuBar.putClientProperty("jgoodies.headerStyle", "Both");
                                UIManager.setLookAndFeel(info.getClassName());

                                // update the complete application's
                                // look & feel
                                SwingUtilities.updateComponentTreeUI(JCalendarDemo.this);
                                for (JComponent bean : beans) {
                                    SwingUtilities.updateComponentTreeUI(bean);
                                }
                                // set the split pane devider border to
                                // null
                                BasicSplitPaneDivider divider = ((BasicSplitPaneUI) splitPane
                                        .getUI()).getDivider();

                                if (divider != null) {
                                    divider.setBorder(null);
                                }
                            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                                logger.log(Level.SEVERE, "Unable to set UI ", e);
                            }
                        }
                    }
                });
                lnfGroup.add(rbmi);
            }
        }
    }

    /**
     * Creates a JFrame with a JCalendarDemo inside and can be used for testing.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {

        JCalendarDemo frame = new JCalendarDemo("JCalendar Demo");
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
    }

}
