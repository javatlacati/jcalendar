/*
 * Copyright (C) 2020 RuslanLopez.
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

import com.toedter.pageobject.components.JLocaleChooserPageObject;
import java.awt.BorderLayout;
import java.util.Locale;
import javax.swing.JFrame;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author RuslanLopez
 */
public class JLocaleChooserTest {
    private JLocaleChooser jLocaleChooser;
    private JFrame frame;
    private JLocaleChooserPageObject pageObject;
    private final Locale englishLocale = Locale.US;

  @BeforeEach
  public void setUp() throws Exception {
    System.out.println("Setting up English locale");
    Locale.setDefault(englishLocale);
    System.out.println("creating JFrame");
    frame = new JFrame("AJFrame");
    frame.setLayout(new BorderLayout());
  }

    @AfterEach
    public void tearDown() {
        frame.setVisible(false);
        frame.dispose();
        frame = null;
    }

    public void secondSetup() {
        frame.add(jLocaleChooser);
        frame.setSize(400, 400);
        frame.setVisible(true);
        pageObject = new JLocaleChooserPageObject("AJFrame");
        System.out.println("JFrame should be visible");
    }

    @Test
    public void valueShouldBeDefault() {
        jLocaleChooser = new JLocaleChooser();
        secondSetup();
//        try {
//            Thread.sleep(1000 * 12);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        assertEquals(englishLocale.getDisplayName(), pageObject.getValue());
    }
}
