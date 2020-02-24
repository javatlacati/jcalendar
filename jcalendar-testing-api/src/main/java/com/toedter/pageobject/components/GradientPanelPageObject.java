package com.toedter.pageobject.components;

import com.toedter.components.GradientPanel;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.operators.JFrameOperator;

import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GradientPanelPageObject {
    private ContainerOperator<GradientPanel> componentPanel;


    public GradientPanelPageObject(String title) {
        ContainerOperator<JFrame> containerFrame = new JFrameOperator(title);
        componentPanel = new ContainerOperator<>(containerFrame,3);
    }

    public Image getComponentImage() {
        BufferedImage bi = new BufferedImage(componentPanel.getWidth(),componentPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        componentPanel.paint(g2);
        g2.dispose();
        return componentPanel.createImage(componentPanel.getWidth(),componentPanel.getHeight());
    }
}
