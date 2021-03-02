/*
 * Copyright (C) 2019 Ruslan Lopez Carro.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.toedter.components;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * A simple JPanel with a border and a title
 *
 * @author Ruslan Lopez Carro
 * @author Kai Toedter
 * @version $LastChangedRevision: 85 $
 * @version $LastChangedDate: 2006-04-28 13:50:52 +0200 (Fr, 28 Apr 2006) $
 */
public class JTitlePanel extends JPanel {

    private static final long serialVersionUID = 9104873267039717087L;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel label;
    private JPanel northPanel;
    private GradientPanel titlePanel;
    // End of variables declaration//GEN-END:variables

    private String title;
    private Icon icon;
    private JComponent content;
    private Border outerBorder;

    /**
     * Creates new form JTitlePanel
     */
    public JTitlePanel() {
        this("", null, new JPanel(), new LineBorder(Color.BLACK));
    }

    /**
     * Constructs a titled panel.
     *
     * @param title the title
     * @param icon icon of the inner label
     * @param content the JComponent that contains the content
     * @param outerBorder the outer border
     */
    public JTitlePanel(String title, Icon icon, JComponent content, Border outerBorder) {
        this.title = title;
        this.icon = icon;
        this.content = content;
        this.outerBorder = outerBorder;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        northPanel = new JPanel();
        titlePanel = new GradientPanel(Color.BLACK);
        label = new JLabel();

        setLayout(new BorderLayout());

        northPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        northPanel.setLayout(new BorderLayout());

        if(content != null){
            northPanel.add(content,BorderLayout.NORTH);
        }

        add(northPanel, BorderLayout.CENTER);

        int borderOffset = 2;
        if(icon == null) {
            borderOffset += 1;
        }
        titlePanel.setBorder(BorderFactory.createEmptyBorder(borderOffset, 4, borderOffset, 1));

        label.setForeground(Color.white);
        label.setIcon(icon);
        label.setText(title == null ? "" : title);
        titlePanel.add(label, BorderLayout.WEST);

        if (outerBorder == null) {
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        } else {
            setBorder(BorderFactory.createCompoundBorder(outerBorder,
                BorderFactory.createLineBorder(Color.GRAY)));
    }

    add(titlePanel, BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    public void setTitle(String label, Icon icon) {
        this.label.setText(label);
        this.label.setIcon(icon);
    }
}
