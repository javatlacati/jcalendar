package com.toedter.components;

import com.toedter.matchers.DirectImageLooksLikeMatcher;
import com.toedter.pageobject.components.GradientPanelPageObject;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;


public class GradientPanelTest {
    private GradientPanel gradientPanel;
    private JFrame frame;
    private GradientPanelPageObject pageObject;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("creating JFrame");
        frame = new JFrame("AJFrame");
        frame.setLayout(new BorderLayout());
    }

    @AfterEach
    public void tearDown(){
        frame.setVisible(false);
        frame.dispose();
        frame = null;
    }

    public void secondSetup(){
        frame.add(gradientPanel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
        pageObject = new GradientPanelPageObject("AJFrame");
        System.out.println("JFrame should be visible");
    }

    @Test
    void withoutColorSpecified() {
        gradientPanel = new GradientPanel();
        secondSetup();
        Color defaultBackgroundColor = new Color(99, 153, 255);
        BufferedImage bi = new BufferedImage(gradientPanel.getWidth(), gradientPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        gradientPanel.paint(g2);
        g2.dispose();
        MatcherAssert.assertThat(bi, DirectImageLooksLikeMatcher.looksLike((BufferedImage) pageObject.getComponentImage(),new Dimension(gradientPanel.getSize())));
    }

    @Test
    void someColorSpecified() {
        Random r = new Random();
        Color myColor = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        gradientPanel = new GradientPanel(myColor);
        secondSetup();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Dmensions:"+gradientPanel.getSize());
        BufferedImage bi = new BufferedImage(386, 363, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        gradientPanel.paint(g2);
        g2.dispose();
        Matcher<Object> matcher = DirectImageLooksLikeMatcher.looksLike((BufferedImage) pageObject.getComponentImage(),
                new Dimension(gradientPanel.getSize()));
        System.out.println("Matcher:"+matcher);
        MatcherAssert.assertThat("hazelnuts", 3, CoreMatchers.equalTo(3));
        MatcherAssert.<BufferedImage>assertThat(
                bi,
                matcher
        );
    }

    @Test
    void bothColorsSpecified() {
        Random r = new Random();
        Color myColor = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        Color myColor1 = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        gradientPanel = new GradientPanel(myColor, myColor1);
        secondSetup();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        assertEquals(myColor, pageObject.getBackgroundColor());
        BufferedImage bi = new BufferedImage(gradientPanel.getWidth(), gradientPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        gradientPanel.paint(g2);
        g2.dispose();
        MatcherAssert.assertThat(bi, DirectImageLooksLikeMatcher.looksLike((BufferedImage) pageObject.getComponentImage(),new Dimension(gradientPanel.getSize())));
    }

    @Test
    void bothColorsSpecifiedButNoOpacity() {
        Random r = new Random();
        Color myColor = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        Color myColor1 = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        gradientPanel = new GradientPanel(myColor, myColor1);
        gradientPanel.setOpaque(false);
        gradientPanel.setVisible(true);
        secondSetup();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedImage bi = new BufferedImage(gradientPanel.getWidth(), gradientPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        gradientPanel.paint(g2);
        g2.dispose();
        gradientPanel.setVisible(false);

        MatcherAssert.assertThat(bi,
                DirectImageLooksLikeMatcher.looksLike((BufferedImage) pageObject.getComponentImage(),
                new Dimension(gradientPanel.getSize())));
    }
}
